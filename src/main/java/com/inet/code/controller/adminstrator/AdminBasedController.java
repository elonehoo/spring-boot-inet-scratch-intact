package com.inet.code.controller.adminstrator;

import com.inet.code.entity.dto.label.LabelAppendDoMain;
import com.inet.code.entity.dto.type.TypeAppendDoMain;
import com.inet.code.realize.AdminBasedService;
import com.inet.code.utils.Result;
import io.swagger.annotations.Api;
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
     * @param labelAppendDoMain:  新增标签的名称
     * @return com.inet.code.utils.Result
    */
    @ApiOperation("新增类别操作")
    @PostMapping("/appendLabel")
    @RequiresRoles(value = {"admin"})
    public Result postAppendLabel(@RequestBody LabelAppendDoMain labelAppendDoMain){
        return adminBasedService.getAppendLabel(labelAppendDoMain,"/scratch/based/appendLabel");
    }

    @PostMapping("/appendType")
    public Result postAppendType(@RequestBody TypeAppendDoMain typeAppendDoMain){
        return adminBasedService.getAppendType(typeAppendDoMain,"/scratch/based/appendType");
    }



}
