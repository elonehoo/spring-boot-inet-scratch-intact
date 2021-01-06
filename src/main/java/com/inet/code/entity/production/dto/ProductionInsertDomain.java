package com.inet.code.entity.production.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 作品的添加实体类
 *
 * @author HCY
 * @since 2020/12/21 5:29 下午
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("作品的添加实体类")
public class ProductionInsertDomain {

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
     * 作品的说明(备注)
     */
    @ApiModelProperty("作品的说明(备注)")
    private String productionRemark;

    /**
     * 作品操作说明
     */
    @ApiModelProperty("作品操作说明")
    private String productionOi;

    /**
     * 作品的类型
     */
    @ApiModelProperty("作品的类型")
    private String[] editorLabelUuid;

    /**
     * 作品的类别
     */
    @ApiModelProperty("作品的类别")
    private String productionTypeUuid;

    /**
     * 作品是否允许改编
     */
    @ApiModelProperty("作品是否允许改编")
    private Boolean productionRecompose;


}
