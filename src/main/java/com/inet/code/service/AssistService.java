package com.inet.code.service;

import com.inet.code.entity.assist.po.Assist;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author HCY
 * @since 2020-12-28
 */
public interface AssistService extends IService<Assist> {

    /**
     * 通过项目的序号和用户的邮箱判断是点赞还是取消点赞
     *
     * @author HCY
     * @since 2020/12/28 10:54 下午
     * @param productionId: 项目的序号
     * @param userEmail: 用户邮箱
     * @return com.inet.code.entity.assist.po.Assist
    */
    Assist getByProductionIdAndUserEmail(String productionId, String userEmail);
}
