package com.inet.code.controller.adminstrator;

import com.inet.code.entity.label.dto.LabelAmendDoMain;
import com.inet.code.entity.label.dto.LabelAppendDoMain;
import com.inet.code.entity.type.dto.TypeAmendDoMain;
import com.inet.code.entity.type.dto.TypeAppendDoMain;
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

}
