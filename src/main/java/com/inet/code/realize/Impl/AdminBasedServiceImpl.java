package com.inet.code.realize.Impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.extra.pinyin.PinyinUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.inet.code.entity.cipher.po.Cipher;
import com.inet.code.entity.label.dto.LabelAmendDoMain;
import com.inet.code.entity.label.dto.LabelAppendDoMain;
import com.inet.code.entity.portrait.dto.PortraitIncreaseDomain;
import com.inet.code.entity.portrait.po.Portrait;
import com.inet.code.entity.power.po.Power;
import com.inet.code.entity.production.vo.ProductionUserLikeFiveView;
import com.inet.code.entity.role.po.Role;
import com.inet.code.entity.slideshow.dto.SlideshowAmendDomain;
import com.inet.code.entity.slideshow.dto.SlideshowIncreaseDomain;
import com.inet.code.entity.slideshow.po.Slideshow;
import com.inet.code.entity.type.dto.TypeAmendDoMain;
import com.inet.code.entity.type.dto.TypeAppendDoMain;
import com.inet.code.entity.label.po.Label;
import com.inet.code.entity.type.po.Type;
import com.inet.code.entity.user.dto.UserRegisterDTO;
import com.inet.code.entity.user.po.User;
import com.inet.code.entity.user.vo.UserFiveLikeView;
import com.inet.code.realize.AdminBasedService;
import com.inet.code.service.*;
import com.inet.code.utils.BeanUtil;
import com.inet.code.utils.DateUtils;
import com.inet.code.utils.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理员的业务控制层
 *
 * @author HCY
 * @since 2020/12/11 下午 06:22
*/
@Service
public class AdminBasedServiceImpl implements AdminBasedService {

    @Resource
    private LabelService labelService;

    @Resource
    private TypeService typeService;

    @Resource
    private EditorService editorService;

    @Resource
    private SlideshowService slideshowService;

    @Resource
    private PortraitService portraitService;

    @Resource
    private UserService userService;

    @Resource
    private ProductionService productionService;

    @Resource
    private CipherService cipherService;

    @Resource
    private RoleService roleService;

    @Resource
    private PowerService powerService;

    /**
     * 标签的添加
     *
     * @author HCY
     * @since 2020/12/11 下午 09:10
     * @param labelAppendDoMain: 标签的基础属性 内涵 标签名字
     * @param path: URL路径
     * @return com.inet.code.utils.Result
     */
    @Override
    public Result getAppendLabel(LabelAppendDoMain labelAppendDoMain, String path) {
        //进行实体类的转换
        Label label = BeanUtil.copy(labelAppendDoMain , Label.class);
        //进行存储操作
        if (labelService.save(label)){
            return new Result().result200(label.getLabelName() + "标签,新增成功",path);
        }
        return new Result().result500(label.getLabelName() + "标签,新增成功",path);
    }

    /**
     * 类别的添加
     *
     * @author HCY
     * @since 2020/12/11 下午 09:32
     * @param typeAppendDoMain: 类别的基础属性, 内涵 类被名称
     * @param path: URL路径
     * @return com.inet.code.utils.Result
     */
    @Override
    public Result getAppendType(TypeAppendDoMain typeAppendDoMain, String path) {
        //进行实体类的转换
        Type type = BeanUtil.copy(typeAppendDoMain,Type.class);
        //进行存储操作
        if (typeService.save(type)){
            return new Result().result200(type.getTypeName() + "标签,新增成功",path);
        }
        return new Result().result500(type.getTypeName() + "标签,新增成功",path);
    }

    /**
     * 标签的修改
     *
     * @author HCY
     * @since 2020/12/12 上午 09:48
     * @param labelAmendDoMain: 修改标签的实体类
     * @param path: URL路径
     * @return com.inet.code.utils.Result
     */
    @Override
    public Result getAmendLabel(LabelAmendDoMain labelAmendDoMain, String path) {
        //通过主键获取到标签的实体类
        Label label = labelService.getById(labelAmendDoMain.getLabelUuid());
        //判断label是否存在
        if (label == null){
            return new Result().result500("尚未找到需要修改的标签",path);
        }
        //存储修改之前的标签名称
        String labelName = label.getLabelName();
        //进行修改
        if (labelService.updateById(label.setLabelName(labelAmendDoMain.getLabelName()))) {
            return new Result().result200(
                    "修改成功,成功将" + labelName + "修改成为" + labelAmendDoMain.getLabelName()
                    ,path);
        }
        return new Result().result200(
                "修改失败,未将" + labelName + "修改成为" + labelAmendDoMain.getLabelName()
                ,path);
    }


    /**
     * 通过标签的uuid查询到有多少个项目文件属于这个标签
     *
     * @author HCY
     * @since 2020/12/12 上午 10:31
     * @param labelUuid: 需要删除的标签的名称
     * @param path: URL路径
     * @return com.inet.code.utils.Result
     */
    @Override
    public Result getRemoveInquireLabel(String labelUuid, String path) {
        //通过主键查询到类别的名字
        Label label = labelService.getById(labelUuid);
        //判断标签是否存在
        if (label == null){
            return new Result().result500("尚未找到需要修改的标签",path);
        }
        //查询又多少条记录是属于这个类别的
        Integer count = editorService.getByLabelUuidCount(labelUuid);
        //返回提示
        return new Result().result200(
                label.getLabelName() + "的标签有" + count + "个项目，您确认需要删除吗？"
                ,path);
    }

    /**
     * 删除操作,会删除属于该类别的所有项目的该标签
     *
     * @author HCY
     * @since 2020/12/12 下午 01:45
     * @param labelUuid: 删除标签的序号
     * @param path: URL路径
     * @return com.inet.code.utils.Result
     */
    @Override
    public Result getRemoveLabel(String labelUuid, String path) {
        //判断是否有这个标签(UUID是否可以使用)
        if (labelService.getById(labelUuid) == null){
            return new Result().result404("需要删除的标签没有找到",path);
        }
        //进行删除操作
        if (labelService.removeById(labelUuid)){
            if (editorService.removeByLabelUuid(labelUuid)){
                return new Result().result200("删除成功",path);
            }
        }
        return new Result().result500("删除失败",path);
    }

    /**
     * 类别的修改操作
     *
     * @author HCY
     * @since 2020/12/12 下午 02:55
     * @param typeAmendDoMain: 修改类别的实体类 含有 类别的uuid 和 类别的名字
     * @param path: URL 路径
     * @return com.inet.code.utils.Result
     */
    @Override
    public Result getAmendType(TypeAmendDoMain typeAmendDoMain, String path) {
        //查找到这个类别
        Type type = typeService.getById(typeAmendDoMain.getTypeUuid());
        //判断这个类别是否存在
        if (type == null){
            return new Result().result404("尚未找到该类别",path);
        }
        //存储名字
        String typeName = type.getTypeName();
        //存在则修改
        if (typeService.updateById(type.setTypeName(typeAmendDoMain.getTypeName()))) {
            return new Result().result200(
                    "成功将" + typeName + "修改成" + typeAmendDoMain.getTypeName()
                    ,path);
        }
        return new Result().result500(
                "未能将" + typeName + "修改成" + typeAmendDoMain.getTypeName()
                ,path);
    }

    /**
     * 分页，查看轮播图(可以通过状态)
     *
     * @author HCY
     * @since 2020/12/15 10:44 上午
     * @param current: 页数
     * @param total: 条目数
     * @param isShow: 图片的状态
     * @param path: URL路径
     * @return com.inet.code.utils.Result
     */
    @Override
    public Result getSlideshowPagination(Integer current, Integer total, Boolean isShow, String path) {
        //设置页数和条目数
        Page<Slideshow> slideshowPage = new Page<>(current,total);
        //设置分页的条件
        QueryWrapper<Slideshow> queryWrapper = new QueryWrapper<>();
        //如果有状态
        if (isShow != null){
            queryWrapper.eq("slideshow_is_show",isShow);
        }
        //进行分页操作
        IPage<Slideshow> slideshowIPage = slideshowService.page(slideshowPage, queryWrapper);
        return new Result().result200(slideshowIPage,path);
    }

    /**
     * 新增轮播图
     *
     * @author HCY
     * @since 2020/12/16 9:52 上午
     * @param slideshowIncreaseDomain: 轮播图新增的实体类 含有 轮播图的URL 和 轮播图的展示状态
     * @param path: URL路径
     * @return com.inet.code.utils.Result
     */
    @Override
    public Result getIncreaseSlideshow(SlideshowIncreaseDomain slideshowIncreaseDomain, String path) {
        //将实体类进行转变
        Slideshow slideshow = BeanUtil.copy(slideshowIncreaseDomain, Slideshow.class);
        //进行存储
        if (slideshowService.save(slideshow)) {
            return new Result().result200("新增轮播图成功",path);
        }
        return new Result().result500("新增轮播图失败",path);
    }

    /**
     * 修改轮播图
     *
     * @author HCY
     * @since 2020/12/16 下午 03:40
     * @param slideshowAmendDomain: 修改轮播图的实体类
     * @param path: URL路径
     * @return com.inet.code.utils.Result
     */
    @Override
    public Result getAmendSlideshow(SlideshowAmendDomain slideshowAmendDomain, String path) {
        //通过轮播图的uuid查找轮播图的实体类
        Slideshow slideshow = slideshowService.getById(slideshowAmendDomain.getSlideshowUuid());
        //判断轮播图是否找到
        if (slideshow == null){
            return  new Result().result404("未找到轮播图",path);
        }
        //判断是否需要修改图片的URL路径
        if (!StrUtil.isBlank(slideshowAmendDomain.getSlideshowUrl())){
            slideshow.setSlideshowUrl(slideshowAmendDomain.getSlideshowUrl());
        }
        //判断是否需要修改轮播图的状态
        if (slideshowAmendDomain.getSlideshowIsShow() != null){
            slideshow.setSlideshowIsShow(slideshowAmendDomain.getSlideshowIsShow());
        }
        //进行修改
        if (slideshowService.updateById(slideshow)) {
            return new Result().result200("修改轮播图成功",path);
        }
        return new Result().result500("修改轮播图失败",path);
    }

    /**
     * 删除轮播图的序号
     *
     * @author HCY
     * @since 2020/12/17 下午 06:53
     * @param slideshowUuid: 删除轮播图的序号
     * @param path:URL路径
     * @return com.inet.code.utils.Result
     */
    @Override
    public Result getRemoveSlideshow(String slideshowUuid, String path) {
        if (slideshowService.removeById(slideshowUuid)) {
            return new Result().result200("删除轮播图成功",path);
        }else {
            return new Result().result500("删除轮播图失败",path);
        }
    }

    /**
     * 分页查看默认头像
     *
     * @author HCY
     * @since 2020/12/21 1:27 下午
     * @param current: 页数
     * @param size: 条目数
     * @param path: URL路径
     * @return com.inet.code.utils.Result
     */
    @Override
    public Result getShowPortrait(Integer current, Integer size, String path) {
        //设置分页的条件
        Page<Portrait> paging = new Page<>(current,size);
        //设置分页的条件
        return new Result().result200(
                 portraitService.page(paging)
                ,path);
    }

    /**
     * 新增默认头像
     *
     * @author HCY
     * @since 2020/12/21 2:07 下午
     * @param portraitIncreaseDomain: 新增默认头像
     * @param path: URL路径
     * @return com.inet.code.utils.Result
     */
    @Override
    public Result getIncreasePortrait(PortraitIncreaseDomain portraitIncreaseDomain, String path) {
        //将头像进行copy
        Portrait portrait = BeanUtil.copy(portraitIncreaseDomain, Portrait.class);
        //进行存储
        if (portraitService.save(portrait)) {
            return new Result().result200("新增默认头像成功",path);
        }
        return new Result().result500("新增默认头像失败",path);
    }

    /**
     * 查看从今日开始前七天的数据量
     *
     * @author HCY
     * @since 2020/12/24 9:50 下午
     * @param path: URL路径
     * @return com.inet.code.utils.Result
     */
    @Override
    public Result getListNewUser(String path) {
        //获取当前的日期的前七日日期
        String[] days = DateUtils.getBeforeSevenDay();
        //创建返回值的条件
        Map<String, Integer> map = new HashMap<>(days.length);
        for (String day : days) {
            Integer count = userService.getNewUsers(day);
            map.put(day,count);
        }
        return new Result().result200(map,path);
    }

    /**
     * 查看从今日开始前七天的项目新增数目
     * @author HCY
     * @since 2020/12/25 6:13 下午
     * @param issue: 发布状态
     * @param path:
     * @return com.inet.code.utils.Result
    */
    @Override
    public Result getListNewProduction(Boolean issue, String path) {
        //获取前今日的日期
        String[] days = DateUtils.getBeforeSevenDay();
        Map<String, Integer> map = productionService.getListNewProduction(issue,days);
        return new Result().result200(map,path);
    }

    /**
     * 查看点赞数最高的五个项目
     *
     * @author HCY
     * @since 2020/12/30 上午8:59
     * @param path: URL路径
     * @return com.inet.code.utils.Result
     */
    @Override
    public Result getListFiveProduction(String path) {
        return new Result().result200(
                 productionService.getListFiveProduction()
                ,path);
    }

    /**
     * 查看点赞数最高的五个用户
     *
     * @author HCY
     * @since 2020/12/30 上午10:54
     * @param path: URL路径
     * @return com.inet.code.utils.Result
     */
    @Override
    public Result getListFiveUsers(String path) {
        return new Result().result200(
                 userService.getListFiveUsers()
                ,path);
    }

    /**
     * 管理员注册
     *
     * @author HCY
     * @since 2021/1/8 下午12:48
     * @param userRegisterDTO: 管理员注册的实体类
     * @param path: URL路径
     * @return com.inet.code.utils.Result
     */
    @Override
    public Result getSaveRegistered(UserRegisterDTO userRegisterDTO, String path) {
        String userName = userRegisterDTO.getUserName();
        String userEmail = PinyinUtil.getPinyin(userName, "");
        //判断假邮箱是否重复
        if (userService.getByEmail(userEmail) != null) {
            userEmail += userService.count() + 1;
        }
        //创建用户的实体类
        User user = new User();
        //设置用户的昵称
        user.setUserName(userName);
        //随机产生头像
        user.setUserBuddha(portraitService.getRandomImagesUrl().getPortraitSrc());
        //设置用户的假邮箱
        user.setUserEmail(userEmail);
        //判断是否注册成功
        if (userService.save(user)) {
            //创建密码的实体类
            Cipher cipher = new Cipher();
            //设置账号
            cipher.setCipherEmail(userEmail);
            //设置密码
            cipher.setCipherPassword(DigestUtil.md5Hex(userEmail));
            //密码存储
            if (cipherService.save(cipher)) {
                Power power = new Power();
                power.setPowerEmail(userEmail);
                power.setPowerRole(roleService.getRoleName("member").getRoleUuid());
                if (powerService.save(power)) {
                    return new Result().result200("注册成功，账号名字为：" + userEmail,path);
                }
            }
        }
        return new Result().result500("注册失败",path);
    }

    /**
     * 分页查看用户
     * @author HCY
     * @since 2021/1/8 下午2:19
     * @param current: 页数
     * @param size: 条目数
     * @param path: URL路径
     * @return com.inet.code.utils.Result
     */
    @Override
    public Result getListUsers(Integer current, Integer size, String path) {
        return new Result().result200(userService.getPage(new Page<>(current,size)),path);
    }

}
