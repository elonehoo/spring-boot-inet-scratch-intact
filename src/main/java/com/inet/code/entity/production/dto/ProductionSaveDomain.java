package com.inet.code.entity.production.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 保存的文件类型
 *
 * @author HCY
 * @since 2020/12/24 8:53 上午
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("作品的保存实体类")
public class ProductionSaveDomain {

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
}
