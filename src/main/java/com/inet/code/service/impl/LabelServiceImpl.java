package com.inet.code.service.impl;

import com.inet.code.entity.label.po.Label;
import com.inet.code.entity.label.vo.LabelBaseView;
import com.inet.code.mapper.LabelMapper;
import com.inet.code.service.LabelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 管理和查看所有的标签 服务实现类
 * </p>
 *
 * @author HCY
 * @since 2020-12-11
 */
@Service
public class LabelServiceImpl extends ServiceImpl<LabelMapper, Label> implements LabelService {

    @Resource
    private LabelMapper labelMapper;

    /**
     * 通过项目的序号查找到项目的标签
     *
     * @author HCY
     * @since 2020/12/31 上午9:26
     * @param productionId: 项目的序号
     * @return java.util.List<com.inet.code.entity.label.vo.LabelBaseView>
    */
    @Override
    public List<LabelBaseView> getListLabel(String productionId) {
        return labelMapper.getListLabel(productionId);
    }
}
