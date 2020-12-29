package com.inet.code.entity.production.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 展示项目的View实体类
 *
 * @author HCY
 * @since 2020/12/29 9:38 上午
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class ProductionView {

    /**
     * 作品uuid
     */
    private String productionUuid;

    /**
     * 作品名称
     */
    private String productionName;

    /**
     * 封面的URL地址
     */
    private String productionCover;
    /**
     * 项目的地址
     */
    private String productionUrl;

    /**
     * 作品的说明(备注)
     */
    private String productionRemark;

    /**
     * 作品操作说明
     */
    private String productionOi;

    /**
     * 作品的类型
     */
    private String productionTypeUuid;

    /**
     * 作品是否允许改编
     */
    private Boolean productionRecompose;

    /**
     * 点赞数
     */
    private Integer likeTotal;

    /**
     * 评论数
     */
    private Integer commentsTotal;

}
