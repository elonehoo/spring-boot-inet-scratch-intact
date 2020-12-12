package com.inet.code.service;

import com.inet.code.entity.editor.po.Editor;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 管理一个文件的标签 服务类
 * </p>
 *
 * @author HCY
 * @since 2020-12-11
 */
public interface EditorService extends IService<Editor> {

    /**
     * 通过标签的序号查询有多少项目属于它
     *
     * @author HCY
     * @since 2020/12/12 上午 10:45
     * @param labelUuid: 标签的序号
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
