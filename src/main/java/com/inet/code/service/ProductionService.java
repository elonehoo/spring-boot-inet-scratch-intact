package com.inet.code.service;

import com.inet.code.entity.production.po.Production;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 上传的项目的管理模块 服务类
 * </p>
 *
 * @author HCY
 * @since 2020-12-11
 */
public interface ProductionService extends IService<Production> {

    /**
     * 查看近七天的上传文件的数量
     *
     * @author HCY
     * @since 2020/12/25 8:10 下午
     * @param issue: 状态
     * @param days: 日期
     * @return java.util.Map<java.lang.String,java.lang.Integer>
    */
    Map<String, Integer> getListNewProduction(Boolean issue, String[] days);
}
