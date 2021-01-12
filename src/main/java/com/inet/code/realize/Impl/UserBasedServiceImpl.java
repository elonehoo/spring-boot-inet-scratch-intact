package com.inet.code.realize.Impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.PageUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.extra.mail.MailUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.inet.code.entity.assist.dto.AssistLikeDomain;
import com.inet.code.entity.assist.po.Assist;
import com.inet.code.entity.attention.dto.AttentionFocusDomain;
import com.inet.code.entity.attention.po.Attention;
import com.inet.code.entity.cipher.dto.CipherAmendDomain;
import com.inet.code.entity.cipher.po.Cipher;
import com.inet.code.entity.editor.po.Editor;
import com.inet.code.entity.label.vo.LabelBaseView;
import com.inet.code.entity.portrait.po.Portrait;
import com.inet.code.entity.portrait.vo.PortraitBuddhaView;
import com.inet.code.entity.power.po.Power;
import com.inet.code.entity.production.dto.ProductionInsertDomain;
import com.inet.code.entity.production.dto.ProductionInsertUploadDomain;
import com.inet.code.entity.production.dto.ProductionSaveDomain;
import com.inet.code.entity.production.dto.ProductionSaveUploadDomain;
import com.inet.code.entity.production.po.Production;
import com.inet.code.entity.production.vo.ProductionUserLikeFiveView;
import com.inet.code.entity.production.vo.ProductionUsersView;
import com.inet.code.entity.production.vo.ProductionView;
import com.inet.code.entity.role.dto.RoleProfileDomain;
import com.inet.code.entity.tool.PageToll;
import com.inet.code.entity.user.dto.UserAmendDomain;
import com.inet.code.entity.user.dto.UserBaseDomain;
import com.inet.code.entity.user.dto.UserRegisterDomain;
import com.inet.code.entity.user.po.User;
import com.inet.code.entity.user.vo.UserFanView;
import com.inet.code.entity.user.vo.UserFiveLikeView;
import com.inet.code.entity.user.vo.UserSearchView;
import com.inet.code.realize.UserBasedService;
import com.inet.code.service.*;
import com.inet.code.utils.BeanUtil;
import com.inet.code.utils.FromMailUtil;
import com.inet.code.utils.Result;
import org.apache.logging.log4j.message.ReusableMessage;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 用户模块的业务操作
 *
 * @author HCY
 * @since 2020/12/11 下午 06:24
*/
@Service
public class UserBasedServiceImpl implements UserBasedService {

    @Resource
    private UserService userService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private PortraitService portraitService;

    @Resource
    private PowerService powerService;

    @Resource
    private RoleService roleService;

    @Resource
    private CipherService cipherService;

    @Resource
    private AttentionService attentionService;

    @Resource
    private ProductionService productionService;

    @Resource
    private AssistService assistService;

    @Resource
    private LabelService labelService;

    @Resource
    private EditorService editorService;

    /**
     * 通过邮箱发送验证码
     *
     * @author HCY
     * @since 2020/12/12 下午 03:08
     * @param email: 邮箱
     * @param path: URL路径
     * @return com.inet.code.utils.Result
     */
    @Override
    public Result getVerification(String email, String path) {
        //判断邮箱是否合法
        if (!Validator.isEmail(email)){
            return new Result().result401("邮箱不合法，无法进行发送验证码",path);
        }
        //判断邮箱是否重复
        Result emailRepeat = this.getEmailRepeat(email, path);
        if (!emailRepeat.getStatus().equals(Result.STATUS_OK_200)){
            return emailRepeat;
        }
        //产生验证码
        String code = RandomUtil.randomString(5);
        //设置邮箱的内容
        MailUtil.send(
                FromMailUtil.getMail()
                , email
                , "博语编程社区验证码"
                , "注册验证码为:" + code + ",有效时长为5分钟"
                , false);
        //将验证码存入Redis
        redisTemplate.opsForValue().set(
                email
                ,code
                ,60 * 5
                , TimeUnit.SECONDS);
        //递交返回值
        return new Result().result200("验证码发送成功",path);
    }


    /**
     * 完成验证码之后的注册请求
     *
     * @author HCY
     * @since 2020/12/12 下午 04:00
     * @param userRegisterDomain: 用户注册的实体类 , 含有 用户的邮箱 和 验证码 和密码
     * @param path: URL路径
     * @return com.inet.code.utils.Result
     */
    @Override
    public Result getRegister(UserRegisterDomain userRegisterDomain, String path) {
        //判断邮箱是否合法
        if (!Validator.isEmail(userRegisterDomain.getEmail())) {
            return new Result().result403("邮箱不合法,无法进行注册", path);
        }
        //判断邮箱是否重复
        Result emailRepeat = this.getEmailRepeat(userRegisterDomain.getEmail(), path);
        if (!emailRepeat.getStatus().equals(Result.STATUS_OK_200)){
            return emailRepeat;
        }
        //判断验证码是否正确
        String verification = redisTemplate.opsForValue().get(userRegisterDomain.getEmail()) == null ? "" : (String) redisTemplate.opsForValue().get(userRegisterDomain.getEmail());
        if (!userRegisterDomain.getCode().equals(verification)){
            return new Result().result403("验证码输入错误",path);
        }
        //判断密码是否合法
        if (! Validator.isGeneral(userRegisterDomain.getPassword())) {
            return new Result().result401("密码格式不正确，需要包含大写字母，小写字母，数字",path);
        }
        //随机产生头像
        Portrait portrait = portraitService.getRandomImagesUrl();
        //进行用户的注册
        //存储
        userService.save(new User(
                BeanUtil.copy(portrait,PortraitBuddhaView.class).getPortraitSrc()
                , userRegisterDomain.getEmail()
                , userRegisterDomain.getEmail()
                ,true
                ,new Date()
                ,"",
                ""
        ));
        //存储
        cipherService.save(new Cipher(
                userRegisterDomain.getEmail()
                , DigestUtil.md5Hex(userRegisterDomain.getPassword())));
        //设置权限为member
        RoleProfileDomain roleProfileDomain = BeanUtil.copy(
                  roleService.getRoleName("member")
                , RoleProfileDomain.class);
        powerService.save(new Power(userRegisterDomain.getEmail(),roleProfileDomain.getRoleUuid()));
        //删除redis中的验证码
        redisTemplate.delete(userRegisterDomain.getEmail());
        //设置返回值
        return new Result().result200("注册成功",path);
    }

    /**
     * 修改密码
     *
     * @author HCY
     * @since 2020/12/12 下午 08:26
     * @param token: 令牌
     * @param cipherAmendDomain: 修改密码的实体类 含有 旧密码,新密码
     * @param path: URL路径
     * @return com.inet.code.utils.Result
     */
    @Override
    public Result getAmendCipher(String token, CipherAmendDomain cipherAmendDomain, String path) {
        //从token中获取用户
        UserBaseDomain userBaseDomain = (UserBaseDomain) redisTemplate.opsForValue().get(token);
        //获取旧密码
        Cipher cipher = cipherService.getByEmail(userBaseDomain.getUserEmail());
        //判断旧密码是否相同
        if (!cipher.getCipherPassword().equals(DigestUtil.md5Hex(cipherAmendDomain.getOldPassword()))){
            return new Result().result403("旧密码不正确",path);
        }
        //判断新密码是否合法
        if (! Validator.isGeneral(cipherAmendDomain.getCipherPassword())) {
            return new Result().result401("新密码格式不正确，需要包含大写字母，小写字母，数字",path);
        }
        //进行密码的修改
        cipher.setCipherPassword(DigestUtil.md5Hex(cipherAmendDomain.getCipherPassword()));
        //进行修改
        if (cipherService.updateById(cipher)){
            return new Result().result200("密码修改成功",path);
        }
        return new Result().result500("密码修改失败",path);
    }

    /**
     * 用户修改自己的个人信息
     *
     * @author HCY
     * @since 2020/12/12 下午 09:03
     * @param token : 令牌
     * @param userAmendDomain: 修改的用户的实体类 , 包含 : 头像,名字,性别,生日,地址,个性签名
     * @param path: URL路径
     * @return com.inet.code.utils.Result
     */
    @Override
    public Result getAmendUser(String token,UserAmendDomain userAmendDomain, String path) {
        //从token中获取用户
        UserBaseDomain userBaseDomain = (UserBaseDomain) redisTemplate.opsForValue().get(token);
        //通过用户的邮箱查找到用户
        User user = userService.getEmailRepeat(userBaseDomain.getUserEmail());
        //如果修改的信息不为空,则修改,如果为空,则不修改
        //判断用户名
        if (!StrUtil.isBlank(userAmendDomain.getUserName())){
            user.setUserName(userAmendDomain.getUserName());
        }
        //判断头像
        if (!StrUtil.isBlank(userAmendDomain.getUserBuddha())){
            user.setUserBuddha(userAmendDomain.getUserBuddha());
        }
        //性别如果不相同
        if (!user.getUserSex().equals(userAmendDomain.getUserSex())){
            user.setUserSex(userAmendDomain.getUserSex());
        }
        //生日如果不相同
        if (user.getUserBirthday() != userAmendDomain.getUserBirthday()){
            user.setUserBirthday(userAmendDomain.getUserBirthday());
        }
        //地址不为空
        if (!StrUtil.isBlank(userAmendDomain.getUserCity())){
            user.setUserCity(userAmendDomain.getUserCity());
        }
        //个性签名不为空
        if (!StrUtil.isBlank(userAmendDomain.getUserSignature())){
            user.setUserSignature(userAmendDomain.getUserSignature());
        }
        //进行修改
        if (userService.updateById(user)) {
            redisTemplate.delete(token);
            redisTemplate.opsForValue().set(
                     token
                    , BeanUtil.copy(user,UserBaseDomain.class)
                    ,7
                    , TimeUnit.DAYS);
            return new Result().result200("用户信息更新成功",path);
        }
        return new Result().result500("用户信息更新失败",path);
    }

    /**
     * 关注或者取消关注
     *
     * @author HCY
     * @since 2020/12/12 下午 09:43
     * @param token: 令牌
     * @param focusEmail: 关注或者取消关注的用户邮箱
     * @param path: URL路径
     * @return com.inet.code.utils.Result
     */
    @Override
    public Result getFocus(String token, String focusEmail, String path) {
        //从token中获取用户
        UserBaseDomain userBaseDomain = (UserBaseDomain) redisTemplate.opsForValue().get(token);
        //判断用户或者关注的用户是否存在
        Result focusOperations = this.focusOperations(userBaseDomain,focusEmail,path);
        if (!focusOperations.getStatus().equals(Result.STATUS_OK_200)){
            return focusOperations;
        }
        //进行关注或者取消关注的操作
        Attention attention = attentionService.getByUserFocus(
                 userBaseDomain.getUserEmail()
                ,focusEmail);
        //判断是否已经关注了
        if (attention == null){
            //关注操作
            AttentionFocusDomain attentionFocusDomain = new AttentionFocusDomain(
                     userBaseDomain.getUserEmail()
                    ,focusEmail);
            if (attentionService.save(BeanUtil.copy(attentionFocusDomain , Attention.class))) {
                return new Result().result200("关注成功！",path);
            }else {
                return new Result().result500("关注失败！",path);
            }
        }else {
            //取消关注操作
            if (attentionService.removeById(attention.getAttentionUuid())) {
                return new Result().result200("取消关注成功",path);
            }else {
                return new Result().result500("取消关注失败",path);
            }
        }
    }

    /**
     * 查看关注自己的用户
     *
     * @author HCY
     * @since 2020/12/13 下午 02:50
     * @param token: 令牌
     * @param pages: 页数
     * @param path: URL路径
     * @return com.inet.code.utils.Result
     */
    @Override
    public Result getCheckFocus(String token, Integer pages, String path) {
        //通过token获取用户的基本信息
        UserBaseDomain userBaseDomain = (UserBaseDomain) redisTemplate.opsForValue().get(token);
        //获取到关注的用户
        List<UserFanView> userFanViews = userService.getCheckFocus(userBaseDomain.getUserEmail(),pages);
        //计算出总页数
        Integer totalPages = userService.getCheckFocusTotal(userBaseDomain.getUserEmail());
        int total = totalPages % 5 != 0 ? (totalPages / 5 + 1) : (totalPages / 5);
        //创建Page对象
        PageToll<UserFanView> userFanViewPageToll =
                new PageToll<>(pages,20,total,userFanViews);
        return new Result().result200(userFanViewPageToll,path);
    }

    /**
     * 查看关注自己的人
     *
     * @author HCY
     * @since 2020/12/13 下午 09:22
     * @param token: 令牌
     * @param pages: 页数
     * @param path: URL路径
     * @return com.inet.code.utils.Result
     */
    @Override
    public Result getCheckFans(String token, Integer pages, String path) {
        //通过token获取用户的基本信息
        UserBaseDomain userBaseDomain = (UserBaseDomain) redisTemplate.opsForValue().get(token);
        //获取到关注我的用户
        List<UserFanView> userFanViews = userService.getCheckFans(userBaseDomain.getUserEmail(), pages);
        //计算出总页数
        Integer totalPages = userService.getCheckFansTotal(userBaseDomain.getUserEmail());
        int total = totalPages % 5 != 0 ? (totalPages / 5 + 1) : (totalPages / 5);
        //创建Page对象
        PageToll<UserFanView> userFanViewPageToll =
                new PageToll<>(pages,20,total,userFanViews);
        return new Result().result200(userFanViewPageToll,path);
    }

    /**
     * 新增用户的项目
     *
     * @author HCY
     * @since 2020/12/21 6:16 下午
     * @param token: 令牌
     * @param productionInsertDomain: 新增项目的实体类
     * @param path: URL路径
     * @return com.inet.code.utils.Result
     */
    @Override
    public Result getInsertProduction(String token, ProductionInsertDomain productionInsertDomain, String path) {
        //通过token获取用户的信息
        UserBaseDomain userBaseDomain = (UserBaseDomain) redisTemplate.opsForValue().get(token);
        //进行项目的拷贝
        Production production = BeanUtil.copy(productionInsertDomain, Production.class);
        //通过宣传图和项目路径判断是否有重复上传
        if (!StrUtil.isBlank(productionService.getByEntity(production))){
            return new Result().result401("不要重复上传哦！",path);
        }
        //设置用户的邮箱信息
        production.setProductionUserEmail(userBaseDomain.getUserEmail());
        //设置为上传操作
        production.setProductionIssue(true);
        //进行存储
        if (!productionService.save(production)) {
            return new Result().result500("保存失败",path);
        }
        String productionId = productionService.getByEntity(production);
        //设置存储的类型
        List<Editor> editors = new ArrayList<>();
        for (String editorLabelUuid : productionInsertDomain.getEditorLabelUuid()){
            editors.add(new Editor()
                    .setEditorProductionUuid(productionId)
                    .setEditorLabelUuid(editorLabelUuid)
            );
        }
        if (editorService.saveBatch(editors)) {
            return new Result().result200("保存成功",path);
        }
        return new Result().result500("保存失败",path);
    }

    /**
     * 保存用户的项目
     *
     * @author HCY
     * @since 2020/12/24 9:27 上午
     * @param token: 令牌
     * @param productionSaveDomain: 保存项目的实体类
     * @param path: URL路径
     * @return com.inet.code.utils.Result
     */
    @Override
    public Result getSaveProduction(String token, ProductionSaveDomain productionSaveDomain, String path) {
        //通过token获取到用户的信息
        UserBaseDomain userBaseDomain = (UserBaseDomain) redisTemplate.opsForValue().get(token);
        //进行项目实体类的拷贝
        Production production = BeanUtil.copy(productionSaveDomain, Production.class);
        //设置用户的上传邮箱
        production.setProductionUserEmail(userBaseDomain.getUserEmail());
        //设置成为保存
        production.setProductionIssue(false);
        //设置名字
        production.setProductionName(DateUtil.now());
        //存储
        if (productionService.save(production)) {
            return new Result().result200("保存项目成功",path);
        }
        return new Result().result500("保存项目失败",path);
    }

    /**
     * 分页查看用户的项目
     *
     * @author HCY
     * @since 2020/12/24 5:57 下午
     * @param token: 令牌
     * @param current: 页数
     * @param size: 条目数
     * @param issue: 状态
     * @param path: URL路径
     * @return com.inet.code.utils.Result
    */
    @Override
    public Result getListProduction(String token, Integer current, Integer size, Boolean issue, String path) {
        //通过token获取用户信息
        UserBaseDomain userBaseDomain = (UserBaseDomain) redisTemplate.opsForValue().get(token);
        //进行分页操作
        return new Result().result200(
                productionService.getPageView(
                         new Page<>(current,size)
                        ,userBaseDomain.getUserEmail()
                        ,issue)
                ,path
        );
    }

    /**
     * 修改保存的项目
     *
     * @author HCY
     * @since 2020/12/27 下午7:26
     * @param token: 令牌
     * @param productionSaveUploadDomain: 修改保存的项目的实体类
     * @param path: URL路径
     * @return com.inet.code.utils.Result
     */
    @Override
    public Result getPutSaveProduction(String token, ProductionSaveUploadDomain productionSaveUploadDomain, String path) {
        //从token中获取到用户的基本数据
        UserBaseDomain userBaseDomain = (UserBaseDomain) redisTemplate.opsForValue().get(token);
        //获取到上传的项目的实体类
        Production production = productionService.getById(productionSaveUploadDomain.getProductionUuid());
        //判断作品是否属于该用户
        if (!userBaseDomain.getUserEmail().equals(production.getProductionUserEmail())){
            return new Result().result401("该项目您无权更改哦！",path);
        }
        //修改封面
        production.setProductionCover(productionSaveUploadDomain.getProductionCover());
        //修改URL
        production.setProductionUrl(productionSaveUploadDomain.getProductionUrl());
        //进行修改
        if (productionService.updateById(production)) {
            return new Result().result200("上传成功",path);
        }
        return new Result().result500("上传失败",path);
    }

    /**
     * 修改上传的项目
     *
     * @author HCY
     * @since 2020/12/27 下午8:02
     * @param token: 令牌
     * @param productionInsertUploadDomain:修改上传的项目的实体类
     * @param path: URL路径
     * @return com.inet.code.utils.Result
     */
    @Override
    public Result getUploadInsertProduction(String token, ProductionInsertUploadDomain productionInsertUploadDomain, String path) {
        //从token中获取到用户的基本数据
        UserBaseDomain userBaseDomain = (UserBaseDomain) redisTemplate.opsForValue().get(token);
        //获取到项目
        Production production = productionService.getById(productionInsertUploadDomain.getProductionUuid());
        //判断是否属于该用户
        if (!userBaseDomain.getUserEmail().equals(production.getProductionUserEmail())){
            return new Result().result401("该项目您无法修改哦！",path);
        }
        //修改封面
        production.setProductionCover(productionInsertUploadDomain.getProductionCover());
        //修改名字
        production.setProductionName(productionInsertUploadDomain.getProductionName());
        //修改项目的地址
        production.setProductionUrl(productionInsertUploadDomain.getProductionUrl());
        //修改作品的说明
        production.setProductionRemark(productionInsertUploadDomain.getProductionRemark());
        //修改作品的操作说明
        production.setProductionOi(productionInsertUploadDomain.getProductionOi());
        //修改作品的类型
        production.setProductionTypeUuid(productionInsertUploadDomain.getProductionTypeUuid());
        //作品是否允许改编
        production.setProductionRecompose(productionInsertUploadDomain.getProductionRecompose());
        //进行修改
        if (!productionService.updateById(production)){
            return new Result().result200("上传失败",path);
        }
        //删除类别
        editorService.remove(new QueryWrapper<Editor>().eq("editor_production_uuid",production.getProductionUuid()));
        //设置存储的类型
        List<Editor> editors = new ArrayList<>();
        for (String editorLabelUuid : productionInsertUploadDomain.getEditorLabelUuid()){
            editors.add(new Editor()
                    .setEditorProductionUuid(production.getProductionUuid())
                    .setEditorLabelUuid(editorLabelUuid)
            );
        }
        //存储集合
        if (editorService.saveBatch(editors)) {
            return new Result().result200("保存成功",path);
        }
        return new Result().result500("保存失败",path);
    }

    /**
     * 点赞，如果已经点赞了，则取消点赞
     *
     * @author HCY
     * @since 2020/12/28 10:42 下午
     * @param token: 令牌
     * @param assistLikeDomain: 点赞项目的实体类
     * @param path：URL路径
     * @return com.inet.code.utils.Result
     */
    @Override
    public Result getLikeProduction(String token, AssistLikeDomain assistLikeDomain,String path) {
        //从token中获取到用户的基本数据
        UserBaseDomain userBaseDomain = (UserBaseDomain) redisTemplate.opsForValue().get(token);
        //通过项目的序号判断项目是否存在
        if (assistService.getById(assistLikeDomain.getAssistProductionId()) == null){
            return new Result().result404("项目为找到",path);
        }
        //判断是点赞还是取消点赞
        Assist judge = assistService.getByProductionIdAndUserEmail(assistLikeDomain.getAssistProductionId(),userBaseDomain.getUserEmail());
        if (judge == null){
            //拷贝实体类
            Assist assist = BeanUtil.copy(assistLikeDomain, Assist.class);
            //设置点赞的用户邮箱
            assist.setAssistUserEmail(userBaseDomain.getUserEmail());
            //进行存储
            if (assistService.save(assist)) {
                return new Result().result200("点赞成功",path);
            }
            return new Result().result500("点赞失败",path);
        }else {
            if (assistService.removeById(judge.getAssistUuid())) {
                return new Result().result200("取消点赞成功",path);
            }
            return new Result().result500("取消点赞失败",path);
        }
    }

    /**
     * 访客项目，在访客模式下可以查看十个点赞数目多的项目
     *
     * @author HCY
     * @since 2020/12/30 下午3:04
     * @param path: URL路径
     * @return com.inet.code.utils.Result
    */
    @Override
    public Result getListTenProduction(String path) {
        return new Result().result200(productionService.getListTenProduction(),path);
    }

    /**
     * 访客项目，在访客模式下可以查看十个点赞数目多的用户
     *
     * @author HCY
     * @since 2020/12/31 上午9:01
     * @param path: URL路径
     * @return com.inet.code.utils.Result
    */
    @Override
    public Result getListTenUser(String path) {
        return new Result().result200(userService.getListTenUser(),path);
    }

    /**
     * 通过项目的序号查询到项目的实体类
     *
     * @author HCY
     * @since 2020/12/31 上午8:49
     * @param productionId: 项目的序号(UUID)
     * @param path: URL路径
     * @return com.inet.code.utils.Result
     */
    @Override
    public Result getViewProduction(String productionId, String path) {
        return new Result().result200(
                 productionService.getViewProduction(productionId)
                                  .setLabelLists(labelService.getListLabel(productionId))
                ,path);
    }

    /**
     * 通过项目的名字进行搜索
     *
     * @author HCY
     * @since 2021/1/12 6:58 PM
     * @param productionName: 项目的名字
     * @param path: URL路径
     * @return com.inet.code.utils.Result
     */
    @Override
    public Result getSearchProduction(String productionName, String path) {
        return new Result().result200(
                 productionService.getSearchProduction(productionName)
                ,path);
    }

    /**
     * 通过用户的名字进行搜索
     *
     * @author HCY
     * @since 2021/1/12 9:01 PM
     * @param userName: 用户名
     * @param path: URL路径
     * @return com.inet.code.utils.Result
    */
    @Override
    public Result getSearchUser(String userName, String path) {
        return new Result().result200(userService.getSearchUser(userName),path);
    }

    /**
     * 判断用户或者关注的用户是否存在
     * 判断需要关注的用户是否存在
     * 判断是否正在关注自己
     * 如果都正确则输出null
     *
     * @author HCY
     * @since 2020/12/12 下午 10:00
     * @param userBaseDomain: 用户的基本信息
     * @param focusEmail: 关注或者取消关注的用户邮箱
     * @param path: URL路径
     * @return com.inet.code.utils.Result
    */
    private Result focusOperations(UserBaseDomain userBaseDomain, String focusEmail, String path) {
        //判断关注用户存在
        if (userService.getEmailRepeat(focusEmail) == null){
            return new Result().result404("关注的用户不存在，请刷新页面在进行关注",path);
        }
        //判断是否正在关注自己
        if (userBaseDomain.getUserEmail().equals(focusEmail)){
            return new Result().result401("您一直在关注自己了哦！", path);
        }
        return new Result().result200("成功",path);
    }



    /**
     * 判断邮箱是否重复
     *
     * @author HCY
     * @since 2020/12/12 下午 03:12
     * @param email: 邮箱
     * @param path: URL路径
     * @return com.inet.code.utils.Result
    */
    private Result getEmailRepeat(String email, String path) {
        //判断邮箱是否产生了重复
        if (userService.getEmailRepeat(email) != null){
            return new Result().result403("邮箱产生了重复",path);
        }
        return new Result().result200("邮箱未产生重复",path);
    }
}
