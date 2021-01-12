package com.inet.code.entity.production.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 搜索出来的项目
 * @author HCY
 * @since 2021/1/12 8:15 PM
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("展示前五个点赞数最高的展示实体类")
public class ProductionSearchUserView {
    /**
     * 作品uuid
     */
    @ApiModelProperty("作品uuid")
    private String productionUuid;

    /**
     * 作品的名字
     */
    @ApiModelProperty("作品的名字")
    private String productionName;

    /**
     * 作品的封面地址
     */
    @ApiModelProperty("作品的封面地址")
    private String productionCover;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String userName;

    /**
     * 用户头像
     */
    @ApiModelProperty("用户头像")
    private String userBuddha;

    /**
     * 点赞数目
     */
    @ApiModelProperty("点赞数目")
    private Integer likeTotal;
}
