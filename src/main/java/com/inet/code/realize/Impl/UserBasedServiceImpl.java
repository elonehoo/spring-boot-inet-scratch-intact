package com.inet.code.realize.Impl;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import com.inet.code.entity.attention.dto.AttentionFocusDomain;
import com.inet.code.entity.attention.po.Attention;
import com.inet.code.entity.cipher.dto.CipherAmendDomain;
import com.inet.code.entity.cipher.po.Cipher;
import com.inet.code.entity.portrait.po.Portrait;
import com.inet.code.entity.portrait.vo.PortraitBuddhaView;
import com.inet.code.entity.power.po.Power;
import com.inet.code.entity.role.dto.RoleProfileDomain;
import com.inet.code.entity.user.dto.UserAmendDomain;
import com.inet.code.entity.user.dto.UserBaseDomain;
import com.inet.code.entity.user.dto.UserRegisterDomain;
import com.inet.code.entity.user.po.User;
import com.inet.code.realize.UserBasedService;
import com.inet.code.service.*;
import com.inet.code.utils.CloneUtil;
import com.inet.code.utils.FromMailUtil;
import com.inet.code.utils.Result;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
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
                this.getMail()
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
                CloneUtil.clone(portrait,PortraitBuddhaView.class).getPortraitSrc()
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
        RoleProfileDomain roleProfileDomain = CloneUtil.clone(
                  roleService.getRoleName("member")
                , RoleProfileDomain.class);
        powerService.save(new Power(userRegisterDomain.getEmail(),roleProfileDomain.getRoleName()));
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
        if (!cipher.getCipherPassword().equals(DigestUtil.md5Hex(cipherAmendDomain.getCipherPassword()))){
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
                    ,CloneUtil.clone(user,UserBaseDomain.class)
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
            if (attentionService.save(CloneUtil.clone(attentionFocusDomain , Attention.class))) {
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
     * 设置邮箱的配置文件
     * @author HCY
     * @since 2020-11-16
     * @return MailAccount
     */
    private MailAccount getMail() {
        MailAccount account = new MailAccount();
        account.setHost(FromMailUtil.HOST);
        account.setPort(FromMailUtil.PORT);
        account.setAuth(true);
        account.setFrom(FromMailUtil.FROM);
        account.setUser(FromMailUtil.USER);
        account.setPass(FromMailUtil.PASS);
        account.setSslEnable(FromMailUtil.SSLENABLE);
        return account;
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
