package com.inet.code.controller.adminstrator;

import com.inet.code.entity.label.dto.LabelAmendDoMain;
import com.inet.code.entity.label.dto.LabelAppendDoMain;
import com.inet.code.entity.portrait.dto.PortraitIncreaseDomain;
import com.inet.code.entity.slideshow.dto.SlideshowAmendDomain;
import com.inet.code.entity.slideshow.dto.SlideshowIncreaseDomain;
import com.inet.code.entity.type.dto.TypeAmendDoMain;
import com.inet.code.entity.type.dto.TypeAppendDoMain;
import com.inet.code.entity.user.dto.UserModifyDTO;
import com.inet.code.entity.user.dto.UserRegisterDTO;
import com.inet.code.realize.AdminBasedService;
import com.inet.code.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 管理员的基础操作
 *
 * @author HCY
 * @since 2020/12/11 下午 06:14
*/
@RestController
@CrossOrigin
@RequestMapping("/admin")
@Api(tags = {"管理员的基本操作模块"},description = "管理模块")
public class AdminBasedController {

    @Resource
    private AdminBasedService adminBasedService;

    /**
     * 新增类别操作
     *
     * @author HCY
     * @since 2020/12/11 下午 09:22
     * @param labelAppendDoMain:  新增标签的名称 含有 标签的名字
     * @return com.inet.code.utils.Result
    */
    @ApiOperation("新增标签操作")
    @PostMapping("/appendLabel")
    @RequiresRoles(value = {"admin"})
    public Result postAppendLabel(@RequestBody LabelAppendDoMain labelAppendDoMain){
        return adminBasedService.getAppendLabel(labelAppendDoMain,"/scratch/admin/appendLabel");
    }

    /**
     * 修改标签的修改
     *
     * @author HCY
     * @since 2020/12/12 上午 09:47
     * @param labelAmendDoMain: 修改的标签 含有 标签的uuid,标签的名字
     * @return com.inet.code.utils.Result
    */
    @ApiOperation("修改标签操作")
    @PutMapping("/amendLabel")
    @RequiresRoles(value = {"admin"})
    public Result putAmendLabel(@RequestBody LabelAmendDoMain labelAmendDoMain){
        return adminBasedService.getAmendLabel(labelAmendDoMain,"/scratch/admin/amendLabel");
    }

    /**
     * 删除前操作,查看该标签拥有多少项目
     *
     * @author HCY
     * @since 2020/12/12 下午 01:40
     * @param labelUuid: 想要删除的标签
     * @return com.inet.code.utils.Result
    */
    @ApiOperation("删除前操作,查看该标签拥有多少项目")
    @ApiImplicitParams({
            @ApiImplicitParam(name="labelUuid",value="标签的uuid",dataType="String", paramType = "query"),
    })
    @GetMapping("/removeInquireLabel")
    @RequiresRoles(value = {"admin"})
    public Result getRemoveInquireLabel(@RequestParam(value = "labelUuid",defaultValue = "") String labelUuid){
        return adminBasedService.getRemoveInquireLabel(
                labelUuid
                ,"/scratch/admin/removeInquireLabel");
    }

    /**
     * 删除操作,删除属于该类别的所有项目的这个标签
     *
     * @author HCY
     * @since 2020/12/12 下午 03:05
     * @param labelUuid: 删除的标签序号
     * @return com.inet.code.utils.Result
    */
    @ApiOperation("删除操作,删除属于该类别的所有项目的这个标签")
    @ApiImplicitParams({
            @ApiImplicitParam(name="labelUuid",value="标签的uuid",dataType="String", paramType = "query"),
    })
    @DeleteMapping("/removeLabel")
    @RequiresRoles(value = {"admin"})
    public Result deleteRemoveLabel(@RequestParam(value = "labelUuid",defaultValue = "") String labelUuid){
        return adminBasedService.getRemoveLabel(
                 labelUuid
                ,"/scratch/admin/removeInquireLabel");
    }

    /**
     * 新增类别的操作
     *
     * @author HCY
     * @since 2020/12/12 上午 09:23
     * @param typeAppendDoMain: 新增类别的实体类 含有 类别的名称
     * @return com.inet.code.utils.Result
    */
    @ApiOperation("新增类别操作")
    @PostMapping("/appendType")
    @RequiresRoles(value = {"admin"})
    public Result postAppendType(@RequestBody TypeAppendDoMain typeAppendDoMain){
        return adminBasedService.getAppendType(typeAppendDoMain,"/scratch/admin/appendType");
    }

    /**
     * 修改类别的操作
     *
     * @author HCY
     * @since 2020/12/12 下午 02:59
     * @param typeAmendDoMain: 修改类别的实体类 含有 类别的uuid 和 类别的名字
     * @return com.inet.code.utils.Result
    */
    @ApiOperation("修改类别的操作")
    @PutMapping("/amendType")
    @RequiresRoles(value = {"admin"})
    public Result putAmendType(@RequestBody TypeAmendDoMain typeAmendDoMain){
        return adminBasedService.getAmendType(typeAmendDoMain,"/scratch/admin/appendType");
    }

    /**
     * 分页查看轮播图
     *
     * @author HCY
     * @since 2020/12/15 12:33 下午
     * @param current: 页数
     * @param isShow: 状态，发布状态还是未发布状态
     * @param total: 条目数
     * @return com.inet.code.utils.Result
    */
    @ApiOperation("轮播图展示操作")
    @ApiImplicitParams({
            @ApiImplicitParam(name="current",value="页数",dataType="Integer", paramType = "query",defaultValue = "1",example = "0"),
            @ApiImplicitParam(name="isShow",value="状态，发布状态还是未发布状态",dataType="Boolean", paramType = "query"),
            @ApiImplicitParam(name="total",value="条目数",dataType="Integer", paramType = "query",defaultValue = "10",example = "0"),
    })
    @GetMapping("/showSlideshow")
    @RequiresRoles(value = {"admin"})
    public Result getShowSlideshow(@RequestParam(value = "current",defaultValue = "1") Integer current,
                                   @RequestParam(value = "isShow",defaultValue = "") Boolean isShow,
                                   @RequestParam(value = "total",defaultValue = "10") Integer total){
        return adminBasedService.getSlideshowPagination(
                  current
                , total
                , isShow
                , "/scratch/based/showSlideshow");
    }

    /**
     * 增加轮播图
     *
     * @author HCY
     * @since 2020/12/16 10:31 上午
     * @param slideshowIncreaseDomain: 新增轮播图的实体类 ， 含有 URL地址 和 显示状态
     * @return com.inet.code.utils.Result
    */
    @ApiOperation("增加轮播图")
    @PostMapping("/increaseSlideshow")
    @RequiresRoles(value = {"admin"})
    public Result postIncreaseSlideshow(@RequestBody SlideshowIncreaseDomain slideshowIncreaseDomain){
        return adminBasedService.getIncreaseSlideshow(
                 slideshowIncreaseDomain
                ,"/scratch/based/increaseSlideshow");
    }

    /**
     * 修改轮播图
     *
     * @author HCY
     * @since 2020/12/16 下午 09:57
     * @param slideshowAmendDomain: 修改轮播图的实体类 含有 uuid 轮播图的URL 轮播图的展示状态
     * @return com.inet.code.utils.Result
    */
    @ApiOperation("修改轮播图的地址或者状态")
    @PutMapping("/amendSlideshow")
    @RequiresRoles(value = {"admin"})
    public Result putAmendSlideshow(@RequestBody SlideshowAmendDomain slideshowAmendDomain){
        return adminBasedService.getAmendSlideshow(slideshowAmendDomain,"/scratch/based/increaseSlideshow");
    }

    /**
     * 删除轮播图
     *
     * @author HCY
     * @since 2020/12/17 下午 06:23
     * @param slideshowUuid: 轮播图的uuid
     * @return com.inet.code.utils.Result
    */
    @ApiOperation("删除轮播图")
    @ApiImplicitParams({
            @ApiImplicitParam(name="slideshowUuid",value="轮播图的uuid",dataType="String", paramType = "query",defaultValue = "",example = "0"),
    })
    @DeleteMapping("/removeSlideshow")
    @RequiresRoles(value = {"admin"})
    public Result deleteRemoveSlideshow(@RequestParam(value = "slideshowUuid",defaultValue = "") String slideshowUuid){
        return adminBasedService.getRemoveSlideshow(
                 slideshowUuid
                ,"/scratch/based/removeSlideshow");
    }

    /**
     * 分页查看默认头像
     *
     * @author HCY
     * @since 2020/12/21 1:12 下午
     * @param current: 页数
     * @param size: 条目数
     * @return com.inet.code.utils.Result
    */
    @ApiOperation("分页查看默认头像")
    @ApiImplicitParams({
            @ApiImplicitParam(name="current",value="页数",dataType="String", paramType = "query",defaultValue = "1",example = "0"),
            @ApiImplicitParam(name="size",value="条目数",dataType="String", paramType = "query",defaultValue = "10",example = "0"),
    })
    @GetMapping("/showPortrait")
    @RequiresRoles(value = {"admin"})
    public Result getShowPortrait(@RequestParam(value = "current",defaultValue = "1") Integer current,
                                  @RequestParam(value = "size",defaultValue = "10") Integer size){
        return adminBasedService.getShowPortrait(
                 current
                ,size
                ,"/scratch/based/removeSlideshow");
    }

    /**
     * 新增默认头像
     *
     * @author HCY
     * @since 2020/12/21 4:35 下午
     * @param portraitIncreaseDomain: 新增默认头像的实体类
     * @return com.inet.code.utils.Result
    */
    @ApiOperation("新增默认头像")
    @PostMapping("/increasePortrait")
    @RequiresRoles(value = {"admin"})
    public Result postIncreasePortrait(@RequestBody PortraitIncreaseDomain portraitIncreaseDomain){
        return adminBasedService.getIncreasePortrait(
                 portraitIncreaseDomain
                ,"/scratch/based/removeSlideshow");
    }

    /**
     * 查看从今日前的一周的每日的新增人数
     *
     * @author HCY
     * @since 2020/12/24 10:02 下午
     * @return com.inet.code.utils.Result
    */
    @ApiOperation("查看从今日开始的前一周每日的新增人数")
    @GetMapping("/listNewUser")
    @RequiresRoles(value = {"admin"})
    public Result getListNewUser(){
        return adminBasedService.getListNewUser("/scratch/based/listNewUser");
    }

    /**
     * 近七天的上传数据
     *
     * @author HCY
     * @since 2020/12/26 5:25 下午
     * @param issue: 状态
     * @return com.inet.code.utils.Result
    */
    @ApiOperation("近七天上传的项目的数据量")
    @ApiImplicitParams({
            @ApiImplicitParam(name="issue",value="状态",dataType="Boolean", paramType = "query",defaultValue = "1",example = "0"),
    })
    @GetMapping("/listNewProduction")
    @RequiresRoles(value = {"admin"})
    public Result getListNewProduction(@RequestParam(value = "issue",defaultValue = "") Boolean issue){
        return adminBasedService.getListNewProduction(
                  issue
                , "/scratch/based/listNewProduction");
    }

    /**
     * 查看点赞数最高的五个项目
     *
     * @author HCY
     * @since 2020/12/30 上午10:06
     * @return com.inet.code.utils.Result
    */
    @ApiOperation("查看点赞数最高的五个项目")
    @GetMapping("/listFiveProduction")
    @RequiresRoles(value = {"admin"})
    public Result getListFiveProduction(){
        return adminBasedService.getListFiveProduction("/scratch/based/listFiveProduction");
    }

    /**
     * 查看点赞数最高的五个用户
     *
     * @author HCY
     * @since 2020/12/30 上午10:50
     * @return com.inet.code.utils.Result
    */
    @ApiOperation("查看点赞数最高的五个用户")
    @GetMapping("/listFiveUsers")
    @RequiresRoles(value = {"admin"})
    public Result getListFiveUsers(){
        return adminBasedService.getListFiveUsers("/scratch/based/listFiveUsers");
    }

    /**
     * 注册用户
     * @author HCY
     * @since 2021/1/8 下午1:27
     * @param userRegisterDTO: 管理员注册操作
     * @return com.inet.code.utils.Result
    */
    @ApiOperation("注册用户")
    @PostMapping("/registered")
    @RequiresRoles(value = {"admin"})
    public Result postRegistered(@RequestBody UserRegisterDTO userRegisterDTO){
        return adminBasedService.getSaveRegistered(userRegisterDTO,"/scratch/based/registered");
    }

    /**
     * 分页查看普通用户
     *
     * @author HCY
     * @since 2021/1/8 下午2:54
     * @param current: 页数
     * @param size: 条目数
     * @return com.inet.code.utils.Result
    */
    @ApiOperation("分页查看普通用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name="current",value="页数",dataType="Integer", paramType = "query",defaultValue = "1",example = "0"),
            @ApiImplicitParam(name="size",value="条目数",dataType="Integer", paramType = "query",defaultValue = "10",example = "0"),
    })
    @GetMapping("/listUsers")
    @RequiresRoles(value = {"admin"})
    public Result getListUsers(@RequestParam(value = "current",defaultValue = "") Integer current,
                               @RequestParam(value = "size",defaultValue = "") Integer size){
        return adminBasedService.getListUsers(current,size,"/scratch/based/listUsers");
    }

    /**
     * 修改用户的密码
     * @author HCY
     * @since 2021/1/8 7:45 下午
     * @param userModifyDTO: 修改密码的实体类
     * @return com.inet.code.utils.Result
    */
    @ApiOperation("修改用户的密码")
    @PutMapping("/modifyUser")
    @RequiresRoles(value = {"admin"})
    public Result putModifyUser(@RequestBody UserModifyDTO userModifyDTO){
        return adminBasedService.getModifyUser(userModifyDTO,"/scratch/based/modifyUser");
    }

    @DeleteMapping("/remove")
    public Result deleteRemove(@RequestParam(value = "userId",defaultValue = "") String userId){
        return adminBasedService.removeUser(userId,"/scratch/based/remove");
    }

}
