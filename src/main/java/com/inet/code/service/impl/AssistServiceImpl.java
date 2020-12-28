package com.inet.code.service.impl;

import com.inet.code.entity.assist.po.Assist;
import com.inet.code.mapper.AssistMapper;
import com.inet.code.service.AssistService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author HCY
 * @since 2020-12-28
 */
@Service
public class AssistServiceImpl extends ServiceImpl<AssistMapper, Assist> implements AssistService {

    @Resource
    private AssistMapper assistMapper;

    /**
     * 通过项目的序号和用户的邮箱判断是点赞还是取消点赞
     *
     * @author HCY
     * @since 2020/12/28 10:54 下午
     * @param productionId: 项目的序号
     * @param userEmail: 用户邮箱
     * @return com.inet.code.entity.assist.po.Assist
     */
    @Override
    public Assist getByProductionIdAndUserEmail(String productionId, String userEmail) {
        return assistMapper.getByProductionIdAndUserEmail(productionId,userEmail);
    }
}
