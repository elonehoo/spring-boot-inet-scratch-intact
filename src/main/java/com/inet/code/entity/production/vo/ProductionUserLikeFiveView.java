package com.inet.code.entity.production.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 展示前五个点赞数最高的展示实体类
 *
 * @author HCY
 * @since 2020/12/30 上午9:48
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("展示前五个点赞数最高的展示实体类")
public class ProductionUserLikeFiveView {
    /**
     * 作品uuid
     */
    @ApiModelProperty("作品uuid")
    private String productionUuid;

    /**
     * 作品名称
     */
    @ApiModelProperty("作品名称")
    private String productionName;

    /**
     * 封面的URL地址
     */
    @ApiModelProperty("封面的URL地址")
    private String productionCover;

    /**
     * 项目的地址
     */
    @ApiModelProperty("项目的地址")
    private String productionUrl;

    /**
     * 用户的头像
     */
    @ApiModelProperty("用户的头像")
    private String userBuddha;

    /**
     * 用户昵称
     */
    @ApiModelProperty("用户昵称")
    private String userName;

    /**
     * 点赞数
     */
    @ApiModelProperty("点赞数")
    private Integer likeTotal;
}
