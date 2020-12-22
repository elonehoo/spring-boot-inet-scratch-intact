package com.inet.code.entity.portrait.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 新增默认头像的实体类
 *
 * @author HCY
 * @since 2020/12/21 2:00 下午
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class PortraitIncreaseDomain {

    /**
     * 默认头像的链接
     */
    @ApiModelProperty("默认头像的链接")
    private String portraitSrc;
}
