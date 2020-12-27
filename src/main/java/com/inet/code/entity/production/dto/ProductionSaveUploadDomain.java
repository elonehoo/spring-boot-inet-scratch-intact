package com.inet.code.entity.production.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 修改保存项目的实体类
 *
 * @author HCY
 * @since 2020/12/27 下午7:05
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("修改作品的保存实体类")
public class ProductionSaveUploadDomain {

    /**
     * 作品uuid
     */
    @ApiModelProperty("作品UUID")
    private String productionUuid;

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
