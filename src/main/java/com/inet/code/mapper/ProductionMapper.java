package com.inet.code.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.inet.code.entity.production.po.Production;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.inet.code.entity.production.vo.ProductionUsersView;

import java.util.List;

/**
 * <p>
 * 上传的项目的管理模块 Mapper 接口
 * </p>
 *
 * @author HCY
 * @since 2020-12-11
 */
public interface ProductionMapper extends BaseMapper<Production> {

    /**
     * 分页查看项目
     *
     * @author HCY
     * @since 2020/12/29 11:23 下午
     * @param page: 分页状态
     * @param userEmail: 用户邮箱
     * @param issue: 状态
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.inet.code.entity.production.vo.ProductionUsersView>
    */
    IPage<ProductionUsersView> getUserPage(Page<ProductionUsersView> page, String userEmail, Boolean issue);
}
