package com.inet.code.service.impl;

import com.inet.code.entity.attention.po.Attention;
import com.inet.code.mapper.AttentionMapper;
import com.inet.code.service.AttentionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 管理关注模块 服务实现类
 * </p>
 *
 * @author HCY
 * @since 2020-12-11
 */
@Service
public class AttentionServiceImpl extends ServiceImpl<AttentionMapper, Attention> implements AttentionService {

    @Resource
    private AttentionMapper attentionMapper;

    /**
     * 查询是否关注了该用户
     *
     * @author HCY
     * @since 2020/12/12 11:34 下午
     * @param userEmail: 用户邮箱
     * @param focusEmail: 关注者邮箱
     * @return com.inet.code.entity.attention.po.Attention
     */
    @Override
    public Attention getByUserFocus(String userEmail, String focusEmail) {
        return attentionMapper.getByUserFocus(userEmail,focusEmail);
    }
}
