package com.inet.code.controller.username;

import com.inet.code.entity.user.dto.UserRegisterDomain;
import com.inet.code.realize.UserBasedService;
import com.inet.code.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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

    @ApiOperation("完成验证码之后的注册请求")
    @PostMapping("/register")
    public Result getRegister(@RequestBody UserRegisterDomain userRegisterDomain){
        return userBasedService.getRegister(userRegisterDomain,"/scratch/user/register");
    }


}
