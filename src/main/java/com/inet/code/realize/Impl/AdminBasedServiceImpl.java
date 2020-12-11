package com.inet.code.realize.Impl;

import com.inet.code.entity.dto.label.LabelAppendDoMain;
import com.inet.code.entity.dto.type.TypeAppendDoMain;
import com.inet.code.entity.po.Label;
import com.inet.code.entity.po.Type;
import com.inet.code.realize.AdminBasedService;
import com.inet.code.service.LabelService;
import com.inet.code.service.TypeService;
import com.inet.code.utils.CloneUtil;
import com.inet.code.utils.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
        Label label = CloneUtil.clone(labelAppendDoMain , Label.class);
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
        Type type = CloneUtil.clone(typeAppendDoMain,Type.class);
        //进行存储操作
        if (typeService.save(type)){
            return new Result().result200(type.getTypeName() + "标签,新增成功",path);
        }
        return new Result().result500(type.getTypeName() + "标签,新增成功",path);
    }
}
