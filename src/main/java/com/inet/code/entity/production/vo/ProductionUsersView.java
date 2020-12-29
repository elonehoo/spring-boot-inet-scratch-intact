package com.inet.code.entity.production.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 用户展示自己的项目的实体类
 *
 * @author HCY
 * @since 2020/12/29 10:38 下午
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户展示自己的项目的实体类")
public class ProductionUsersView {

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
     * 点赞数
     */
    private Integer likeTotal;
}
