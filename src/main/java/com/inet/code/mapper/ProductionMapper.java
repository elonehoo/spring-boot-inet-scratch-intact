package com.inet.code.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.inet.code.entity.production.po.Production;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.inet.code.entity.production.vo.ProductionUserLikeFiveView;
import com.inet.code.entity.production.vo.ProductionUsersView;
import com.inet.code.entity.production.vo.ProductionView;

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

    /**
     * 展示点赞数排名前五的项目
     *
     * @author HCY
     * @since 2020/12/30 上午10:03
     * @return java.util.List<com.inet.code.entity.production.vo.ProductionUserLikeFiveView>
    */
    List<ProductionUserLikeFiveView> getListFive();

    /**
     * 访客项目，在访客模式下可以查看十个点赞数目多的项目
     *
     * @author HCY
     * @since 2020/12/30 下午3:21
     * @return java.util.List<com.inet.code.entity.production.vo.ProductionUserLikeFiveView>
     */
    List<ProductionUserLikeFiveView> getListTen();

    /**
     * 通过项目的序号查询到项目的实体类
     *
     * @author HCY
     * @since 2020/12/31 上午9:23
     * @param productionId: 项目的序号
     * @return com.inet.code.entity.production.vo.ProductionView
    */
    ProductionView getViewProduction(String productionId);

    /**
     * 获取新增项目的序号
     *
     * @author HCY
     * @since 2021/1/6 下午4:00
     * @param production: 新增的项目的实体类
     * @return java.lang.String
     */
    String getByEntity(Production production);

    /**
     * 项目的名字搜索项目
     *
     * @author HCY
     * @since 2021/1/12 6:59 PM
     * @param productionName: 项目的名字
     * @return java.util.List<com.inet.code.entity.production.vo.ProductionView>
     */
    List<ProductionView> getSearchProduction(String productionName);
}
