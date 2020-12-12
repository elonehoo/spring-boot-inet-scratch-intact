package com.inet.code.service.impl;

import com.inet.code.entity.editor.po.Editor;
import com.inet.code.mapper.EditorMapper;
import com.inet.code.service.EditorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 管理一个文件的标签 服务实现类
 * </p>
 *
 * @author HCY
 * @since 2020-12-11
 */
@Service
public class EditorServiceImpl extends ServiceImpl<EditorMapper, Editor> implements EditorService {

    @Resource
    private EditorMapper editorMapper;

    /**
     * 通过标签的序号查询有多少项目属于它
     *
     * @author HCY
     * @since 2020/12/12 上午 10:45
     * @param labelUuid: 标签的序号
     * @return java.lang.Integer
     */
    @Override
    public Integer getByLabelUuidCount(String labelUuid) {
        return editorMapper.getByLabelUuidCount(labelUuid);
    }

    /**
     * 删除属于该类别的所有项目的该标签
     *
     * @author HCY
     * @since 2020/12/12 下午 02:21
     * @param labelUuid: 需要删除的标签序号
     * @return java.lang.Boolean
     */
    @Override
    public Boolean removeByLabelUuid(String labelUuid) {
        return editorMapper.removeByLabelUuid(labelUuid);
    }
}
