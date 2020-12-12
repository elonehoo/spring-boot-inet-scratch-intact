package com.inet.code.realize;

import com.inet.code.entity.label.dto.LabelAmendDoMain;
import com.inet.code.entity.label.dto.LabelAppendDoMain;
import com.inet.code.entity.type.dto.TypeAmendDoMain;
import com.inet.code.entity.type.dto.TypeAppendDoMain;
import com.inet.code.utils.Result;

/**
 * 管理员的业务操作
 *
 * @author HCY
 * @since 2020/12/11 下午 06:23
*/
public interface AdminBasedService {
    /**
     * 标签的添加
     *
     * @author HCY
     * @since 2020/12/11 下午 09:10
     * @param labelAppendDoMain: 标签的基础属性 内涵 标签名字
     * @param path: URL路径
     * @return com.inet.code.utils.Result
    */
    Result getAppendLabel(LabelAppendDoMain labelAppendDoMain, String path);

    /**
     * 类别的添加
     *
     * @author HCY
     * @since 2020/12/11 下午 09:32
     * @param typeAppendDoMain: 类别的基础属性, 内涵 类被名称
     * @param path: URL路径
     * @return com.inet.code.utils.Result
    */
    Result getAppendType(TypeAppendDoMain typeAppendDoMain, String path);

    /**
     * 标签的修改
     *
     * @author HCY
     * @since 2020/12/12 上午 09:48
     * @param labelAmendDoMain: 修改标签的实体类
     * @param path: URL路径
     * @return com.inet.code.utils.Result
    */
    Result getAmendLabel(LabelAmendDoMain labelAmendDoMain, String path);

    /**
     * 通过标签的uuid查询到有多少个项目文件属于这个标签
     *
     * @author HCY
     * @since 2020/12/12 上午 10:31
     * @param labelUuid: 需要删除的标签的名称
     * @param path: URL路径
     * @return com.inet.code.utils.Result
    */
    Result getRemoveInquireLabel(String labelUuid, String path);

    /**
     * 删除操作,会删除属于该类别的所有项目的该标签
     *
     * @author HCY
     * @since 2020/12/12 下午 01:45
     * @param labelUuid: 删除标签的序号
     * @param path: URL路径
     * @return com.inet.code.utils.Result
    */
    Result getRemoveLabel(String labelUuid, String path);

    /**
     * 类别的修改操作
     *
     * @author HCY
     * @since 2020/12/12 下午 02:55
     * @param typeAmendDoMain: 修改类别的实体类 含有 类别的uuid 和 类别的名字
     * @param path: URL 路径
     * @return com.inet.code.utils.Result
    */
    Result getAmendType(TypeAmendDoMain typeAmendDoMain, String path);
}
