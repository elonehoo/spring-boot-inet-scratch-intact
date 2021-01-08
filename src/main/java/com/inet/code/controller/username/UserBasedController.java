package com.inet.code.controller.username;

import com.inet.code.entity.assist.dto.AssistLikeDomain;
import com.inet.code.entity.cipher.dto.CipherAmendDomain;
import com.inet.code.entity.production.dto.ProductionInsertDomain;
import com.inet.code.entity.production.dto.ProductionInsertUploadDomain;
import com.inet.code.entity.production.dto.ProductionSaveDomain;
import com.inet.code.entity.production.dto.ProductionSaveUploadDomain;
import com.inet.code.entity.user.dto.UserAmendDomain;
import com.inet.code.entity.user.dto.UserRegisterDomain;
import com.inet.code.realize.UserBasedService;
import com.inet.code.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.ProtectionDomain;

/**
 * 用户的基本操作
 *
 * @author HCY
 * @since 2020/12/11 下午 06:16
*/
@RestController
@CrossOrigin
@RequestMapping("/user")
@Api(tags = {"用户的基本操作模块"},description = "用户模块")
public class UserBasedController {

    @Resource
    private UserBasedService userBasedService;


    /**
     * 通过邮箱发送验证码
     *
     * @author HCY
     * @since 2020/12/12 下午 03:27
     * @param email: 邮箱
     * @return com.inet.code.utils.Result
    */
    @ApiOperation("通过邮箱发送验证码(注册的前提操作)，不需要token，不能使用")
    @ApiImplicitParams({
            @ApiImplicitParam(name="email",value="邮箱",dataType="String", paramType = "query"),
    })
    @GetMapping("/verification")
    public Result getVerification(@RequestParam(value = "email",defaultValue = "") String email){
        return userBasedService.getVerification(
                email
                ,"/scratch/user/appendType");
    }

    /**
     * 完成验证码之后的注册请求
     *
     * @author HCY
     * @since 2020/12/12 下午 08:20
     * @param userRegisterDomain: 注册的实体类 含有 邮箱,验证码,密码
     * @return com.inet.code.utils.Result
    */
    @ApiOperation("完成验证码之后的注册操作,不需要token，不能使用")
    @PostMapping("/register")
    public Result getRegister(@RequestBody UserRegisterDomain userRegisterDomain){
        return userBasedService.getRegister(userRegisterDomain,"/scratch/user/register");
    }



    /**
     * 修改密码
     *
     * @author HCY
     * @since 2020/12/12 下午 08:25
     * @param token: 令牌
     * @param cipherAmendDomain: 修改密码的实体类 含有 旧密码,新密码
     * @return com.inet.code.utils.Result
    */
    @ApiOperation("修改密码,输入旧密码和新密码")
    @PutMapping("/amendCipher")
    @RequiresRoles(value = {"member"})
    public Result putAmendCipher(@RequestHeader(value = "Token",defaultValue = "") String token,
                                 @RequestBody CipherAmendDomain cipherAmendDomain){
        return userBasedService.getAmendCipher(token,cipherAmendDomain,"/scratch/user/amendCipher");
    }

    /**
     * 用户修改自己的个人信息
     *
     * @author HCY
     * @since 2020/12/12 下午 08:54
     * @param token: 令牌
     * @param userAmendDomain: 修改的用户的实体类 , 包含 : 头像,名字,性别,生日,地址,个性签名
     * @return com.inet.code.utils.Result
    */
    @ApiOperation("用户修改自己的个人信息")
    @PutMapping("/amendUser")
    @RequiresRoles(value = {"member"})
    public Result putAmendUser(@RequestHeader(value = "Token",defaultValue = "") String token,
                               @RequestBody UserAmendDomain userAmendDomain){
        return userBasedService.getAmendUser(token,userAmendDomain,"/scratch/user/amendUser");
    }

    /**
     * 关注某一个用户,如果已经关注过了,则取消关注
     *
     * @author HCY
     * @since 2020/12/12 下午 09:46
     * @param token: 令牌
     * @param focusEmail: 关注或者取消关注的用户邮箱
     * @return com.inet.code.utils.Result
    */
    @ApiOperation("关注某一个用户,如果已经关注过了,则取消关注")
    @ApiImplicitParams({
            @ApiImplicitParam(name="focusEmail",value="关注的邮箱",dataType="String", paramType = "query"),
    })
    @GetMapping("/focus")
    @RequiresRoles(value = {"member"})
    public Result getFocus(@RequestHeader(value = "Token",defaultValue = "") String token,
                           @RequestParam(value = "focusEmail",defaultValue = "") String focusEmail){
        return userBasedService.getFocus(
                 token
                ,focusEmail
                ,"/scratch/user/focus");
    }

    /**
     * 查看自己的关注的人,如果为true则为双向关注
     *
     * @author HCY
     * @since 2020/12/13 下午 08:04
     * @param token: 令牌
     * @param pages: 页数
     * @return com.inet.code.utils.Result
    */
    @ApiOperation("查看自己的关注的人,如果为true则为双向关注")
    @ApiImplicitParams({
            @ApiImplicitParam(name="pages",value="页数",dataType="String", paramType = "query",defaultValue = "1",example = "0"),
    })
    @GetMapping("/checkFocus")
    @RequiresRoles(value = {"member"})
    public Result getCheckFan(@RequestHeader(value = "Token",defaultValue = "") String token,
                              @RequestParam(value = "pages",defaultValue = "1") Integer pages){
        return userBasedService.getCheckFocus(token,pages,"/scratch/user/focus");
    }

    /**
     * 查看关注自己的人,如果为false则为双向关注
     * @author HCY
     * @since 2020/12/13 下午 09:50
     * @param token: 令牌
     * @param pages: 页数
     * @return com.inet.code.utils.Result
    */
    @ApiOperation("查看关注自己的人,如果为false则为双向关注")
    @ApiImplicitParams({
            @ApiImplicitParam(name="pages",value="页数",dataType="String", paramType = "query",defaultValue = "1",example = "0"),
    })
    @GetMapping("/checkFans")
    @RequiresRoles(value = {"member"})
    public Result getCheckFans(@RequestHeader(value = "Token",defaultValue = "") String token,
                               @RequestParam(value = "pages",defaultValue = "1") Integer pages){
        return userBasedService.getCheckFans(token , pages , "/scratch/user/focus");
    }

    /**
     * 用户上传项目
     *
     * @author HCY
     * @since 2020/12/22 10:50 上午
     * @param token: 令牌
     * @param productionInsertDomain: 上传项目的实体类
     * @return com.inet.code.utils.Result
    */
    @ApiOperation("用户发布项目")
    @PostMapping("/insertProduction")
    @RequiresRoles(value = {"member"})
    public Result postInsertProduction(@RequestHeader(value = "Token",defaultValue = "") String token,
                                       @RequestBody ProductionInsertDomain productionInsertDomain){
        return userBasedService.getInsertProduction(
                 token
                ,productionInsertDomain
                ,"/scratch/user/insertProduction");
    }

    /**
     * 用户保存项目
     *
     * @author HCY
     * @since 2020/12/24 9:43 上午
     * @param token: 令牌
     * @param productionSaveDomain: 用户保存项目的实体类
     * @return com.inet.code.utils.Result
    */
    @ApiOperation("用户保存项目")
    @PostMapping("/saveProduction")
    @RequiresRoles(value = {"member"})
    public Result postSaveProduction(@RequestHeader(value = "Token",defaultValue = "") String token,
                                     @RequestBody ProductionSaveDomain productionSaveDomain){
        return userBasedService.getSaveProduction(
                 token
                ,productionSaveDomain
                ,"/scratch/user/saveProduction");
    }

    /**
     * 查看自己的上传的文件
     *
     * @author HCY
     * @since 2020/12/24 5:53 下午
     * @param token: 令牌
     * @param current: 页数
     * @param size: 条目数
     * @param issue: 状态
     * @return com.inet.code.utils.Result
    */
    @ApiOperation("查看自己的上传的文件")
    @GetMapping("/listProduction")
    @RequiresRoles(value = {"member"})
    public Result getListProduction(@RequestHeader(value = "Token",defaultValue = "") String token,
                                    @RequestParam(value = "current",defaultValue = "1") Integer current,
                                    @RequestParam(value = "size",defaultValue = "10") Integer size,
                                    @RequestParam(value = "issue",defaultValue = "") Boolean issue){
        return userBasedService.getListProduction(
                 token
                ,current
                ,size
                ,issue
                ,"/scratch/user/listProduction");
    }

    /**
     * 修改保存的项目
     *
     * @author HCY
     * @since 2020/12/28 8:53 上午
     * @param token: 令牌
     * @param productionSaveUploadDomain: 媳妇爱保存的项目的实体类
     * @return com.inet.code.utils.Result
    */
    @ApiOperation("修改保存的项目")
    @PutMapping("/saveProduction")
    @RequiresRoles(value = {"member"})
    public Result putSaveProduction(@RequestHeader(value = "Token",defaultValue = "") String token,
                                    @RequestBody ProductionSaveUploadDomain productionSaveUploadDomain){
        return userBasedService.getPutSaveProduction(
                 token
                ,productionSaveUploadDomain
                ,"/scratch/user/saveProduction");
    }

    /**
     * 修改上传的项目
     *
     * @author HCY
     * @since 2020/12/28 8:54 上午
     * @param token: 令牌
     * @param productionInsertUploadDomain: 修改上传的项目的实体类
     * @return com.inet.code.utils.Result
    */
    @ApiOperation("修改上传的项目")
    @PutMapping("/insertProduction")
    @RequiresRoles(value = {"member"})
    public Result putInsertProduction(@RequestHeader(value = "Token",defaultValue = "") String token,
                                      @RequestBody ProductionInsertUploadDomain productionInsertUploadDomain){
        return userBasedService.getUploadInsertProduction(
                 token
                ,productionInsertUploadDomain
                ,"/scratch/user/insertProduction");
    }

    /**
     * 点赞项目，如果已经点赞了，则取消点赞
     *
     * @author HCY
     * @since 2020/12/28 11:04 下午
     * @param token: 令牌
     * @param assistLikeDomain: 点赞的实体类
     * @return com.inet.code.utils.Result
    */
    @ApiOperation("点赞项目，如果已经点赞了，则取消点赞")
    @PostMapping("/likeProduction")
    @RequiresRoles(value = {"member"})
    public Result postLikeProduction(@RequestHeader(value = "Token",defaultValue = "") String token,
                                     @RequestBody AssistLikeDomain assistLikeDomain){
        return userBasedService.getLikeProduction(
                 token
                ,assistLikeDomain
                ,"/scratch/user/likeProduction");
    }

    /**
     * 访客项目，在访客模式下可以查看十个点赞数目多的项目
     *
     * @author HCY
     * @since 2020/12/30 下午2:59
     * @return com.inet.code.utils.Result
    */
    @ApiOperation("访客项目，在访客模式下可以查看十个点赞数目多的项目,不需要token")
    @GetMapping("/listTenProduction")
    public Result getListTenProduction(){
        return userBasedService.getListTenProduction("/scratch/user/listTenProduction");
    }

    /**
     * 访客项目，在访客模式下可以查看十个点赞数目多的用户
     *
     * @author HCY
     * @since 2020/12/30 下午3:44
     * @return com.inet.code.utils.Result
    */
    @ApiOperation("访客项目，在访客模式下可以查看十个点赞数目多的用户,不需要token")
    @GetMapping("/listTenUser")
    public Result getListTenUser(){
        return userBasedService.getListTenUser("/scratch/user/listTenUser");
    }

    /**
     * 通过项目序号查找到该项目的具体信息
     *
     * @author HCY
     * @since 2020/12/31 上午9:21
     * @param productionId: 项目的序号
     * @return com.inet.code.utils.Result
    */
    @ApiOperation("访客项目，通过项目序号查找到该项目的具体信息,不需要token")
    @ApiImplicitParams({
            @ApiImplicitParam(name="productionId",value="项目的序号",dataType="String", paramType = "query",defaultValue = "",example = "0"),
    })
    @GetMapping("/viewProduction")
    public Result getViewProduction(@RequestParam(value = "productionId",defaultValue = "") String productionId){
        return userBasedService.getViewProduction(productionId,"/scratch/user/viewProduction");
    }

}
