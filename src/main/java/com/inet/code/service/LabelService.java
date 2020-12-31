package com.inet.code.service;

import com.inet.code.entity.label.po.Label;
import com.baomidou.mybatisplus.extension.service.IService;
import com.inet.code.entity.label.vo.LabelBaseView;

import java.util.List;

/**
 * <p>
 * 管理和查看所有的标签 服务类
 * </p>
 *
 * @author HCY
 * @since 2020-12-11
 */
public interface LabelService extends IService<Label> {

    /**
     * 通过项目的序号查找到项目的标签
     *
     * @author HCY
     * @since 2020/12/31 上午9:25
     * @param productionId: 项目的序号
     * @return java.util.List<com.inet.code.entity.label.vo.LabelBaseView>
    */
    List<LabelBaseView> getListLabel(String productionId);
}
