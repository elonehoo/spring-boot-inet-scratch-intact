package com.inet.code.mapper;

import com.inet.code.entity.editor.po.Editor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 管理一个文件的标签 Mapper 接口
 * </p>
 *
 * @author HCY
 * @since 2020-12-11
 */
public interface EditorMapper extends BaseMapper<Editor> {

    /**
     * 通过标签的序号查询有多少项目属于它
     *
     * @author HCY
     * @since 2020/12/12 上午 10:56
     * @param labelUuid: 标签序号
     * @return java.lang.Integer
    */
    Integer getByLabelUuidCount(String labelUuid);

    /**
     * 删除属于该类别的所有项目的该标签
     *
     * @author HCY
     * @since 2020/12/12 下午 02:21
     * @param labelUuid: 需要删除的标签序号
     * @return java.lang.Boolean
     */
    Boolean removeByLabelUuid(String labelUuid);
}
