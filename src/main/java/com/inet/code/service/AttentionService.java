package com.inet.code.service;

import com.inet.code.entity.attention.po.Attention;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 管理关注模块 服务类
 * </p>
 *
 * @author HCY
 * @since 2020-12-11
 */
public interface AttentionService extends IService<Attention> {

    /**
     * 查询是否关注了该用户
     *
     * @author HCY
     * @since 2020/12/12 11:34 下午
     * @param userEmail: 用户邮箱
     * @param focusEmail: 关注者邮箱
     * @return com.inet.code.entity.attention.po.Attention
    */
    Attention getByUserFocus(String userEmail, String focusEmail);
}
