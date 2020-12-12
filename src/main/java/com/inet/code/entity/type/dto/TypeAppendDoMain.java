package com.inet.code.entity.type.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * 类别的新增类
 *
 * @author HCY
 * @since 2020/12/11 下午 09:29
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel
public class TypeAppendDoMain {
    /**
     * 类别的名称
     */
    @ApiModelProperty("类别名字")
    private String typeName;
}
