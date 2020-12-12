package com.inet.code.entity.attention.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 进行关注的实体类
 *
 * @author HCY
 * @since 2020/12/13 12:00 上午
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class AttentionFocusDomain {

    /**
     * 用户的邮箱
     */
    @ApiModelProperty("用户的邮箱")
    private String attentionEmail;

    /**
     * 用户的关注者的邮箱
     */
    @ApiModelProperty("用户的关注者的邮箱")
    private String attentionConcern;

}
