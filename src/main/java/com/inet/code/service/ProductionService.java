package com.inet.code.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.inet.code.entity.production.po.Production;
import com.baomidou.mybatisplus.extension.service.IService;
import com.inet.code.entity.production.vo.ProductionUsersView;

import java.util.List;
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

    /**
     * 分页查看项目的状态
     *
     * @author HCY
     * @since 2020/12/29 11:22 下午
     * @param page: 分页条件
     * @param userEmail: 用户邮箱
     * @param issue: 状态
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.inet.code.entity.production.vo.ProductionUsersView>
    */
    IPage<ProductionUsersView> getPageView(Page<ProductionUsersView> page, String userEmail, Boolean issue);
}
