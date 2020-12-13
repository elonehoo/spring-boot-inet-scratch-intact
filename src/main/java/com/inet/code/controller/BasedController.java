package com.inet.code.controller;

import com.inet.code.entity.user.dto.UserLoginDomain;
import com.inet.code.realize.BasedService;
import com.inet.code.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 基本操作,管理员和用户都需要的操作
 *
 * @author HCY
 * @since 2020/12/11 下午 06:17
*/
@RestController
@CrossOrigin
@RequestMapping("/base")
@Api(tags = {"管理和用户页面的基础操作"},description = "通用模块")
public class BasedController {

    @Resource
    private BasedService basedService;

    /**
     * 登录的API
     *
     * @author HCY
     * @since 2020/12/11 下午 08:01
     * @param userLoginDomain: 登录的DTO端口 , 含有账号和密码
     * @return com.inet.code.utils.Result
    */
    @ApiOperation("登录操作，不需要token")
    @PostMapping("/login")
    public Result postLogin(@RequestBody UserLoginDomain userLoginDomain){
        return basedService.getLogin(userLoginDomain , "/scratch/based/login");
    }

    /**
     * 登出的API
     *
     * @author HCY
     * @since 2020/12/11 下午 08:02
     * @param token: 令牌
     * @return com.inet.code.utils.Result
    */
    @ApiOperation("退出操作")
    @GetMapping("/logout")
    @RequiresRoles(logical = Logical.OR,value = {"admin","member"})
    public Result getLogout(@RequestHeader(value = "Token",defaultValue = "") String token){
        return basedService.getLogout(token,"/scratch/based/logout");
    }

    /**
     * 查看所有类别的API
     *
     * @author HCY
     * @since 2020/12/11 下午 08:02
     * @return com.inet.code.utils.Result
    */
    @ApiOperation("查看所有类别")
    @GetMapping("/listType")
    @RequiresRoles(logical = Logical.OR,value = {"admin","member"})
    public Result getListType(){
        return basedService.getListType("/scratch/based/listType");
    }

    /**
     * 查看所有的标签
     * @author HCY
     * @since 2020/12/11 下午 08:49
     * @return com.inet.code.utils.Result
    */
    @ApiOperation("查看所有的标签")
    @GetMapping("/listLabel")
    @RequiresRoles(logical = Logical.OR,value = {"admin","member"})
    public Result getListLabel(){
        return basedService.getListLabel("/scratch/based/listLabel");
    }

}
