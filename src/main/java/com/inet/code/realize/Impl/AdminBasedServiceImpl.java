package com.inet.code.realize.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.inet.code.entity.label.dto.LabelAmendDoMain;
import com.inet.code.entity.label.dto.LabelAppendDoMain;
import com.inet.code.entity.slideshow.po.Slideshow;
import com.inet.code.entity.type.dto.TypeAmendDoMain;
import com.inet.code.entity.type.dto.TypeAppendDoMain;
import com.inet.code.entity.label.po.Label;
import com.inet.code.entity.type.po.Type;
import com.inet.code.realize.AdminBasedService;
import com.inet.code.service.EditorService;
import com.inet.code.service.LabelService;
import com.inet.code.service.SlideshowService;
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

    @Resource
    private EditorService editorService;

    @Resource
    private SlideshowService slideshowService;
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
}
