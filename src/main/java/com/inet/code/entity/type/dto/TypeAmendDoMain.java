package com.inet.code.entity.type.dto;

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
 * 修改类别的实体类
 *
 * @author HCY
 * @since 2020/12/12 下午 02:51
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class TypeAmendDoMain {

    /**
     * 类别的uuid
     */
    @ApiModelProperty("类别的uuid")
    private String typeUuid;

    /**
     * 类别的名称
     */
    @ApiModelProperty("类别的名称")
    private String typeName;
}
