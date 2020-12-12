package com.inet.code.controller.username;

import com.inet.code.entity.cipher.dto.CipherAmendDomain;
import com.inet.code.entity.user.dto.UserAmendDomain;
import com.inet.code.entity.user.dto.UserBaseDomain;
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
import java.sql.ResultSet;

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
    @ApiOperation("通过邮箱发送验证码(注册的前提操作)")
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
    @ApiOperation("完成验证码之后的注册操作")
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
    @ApiOperation("修改密码,输入旧密码和新密码")
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
            @ApiImplicitParam(name="FocusEmail",value="关注的邮箱",dataType="String", paramType = "query"),
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
}
