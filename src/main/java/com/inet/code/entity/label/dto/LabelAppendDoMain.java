package com.inet.code.entity.label.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * 标签的基础DTO
 *
 * @author HCY
 * @since 2020/12/11 下午 09:03
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel
public class LabelAppendDoMain {

    /**
     * 标签名字
     */
    @ApiModelProperty("标签名字")
    private String labelName;
}
