package com.inet.code.entity.assist.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 进行点赞的实体类
 *
 * @author HCY
 * @since 2020/12/28 10:38 下午
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
@ApiModel("进行点赞的实体类")
public class AssistLikeDomain {

    /**
     * 点赞的项目
     */
    @ApiModelProperty("点赞的项目主键（UUID）")
    private String assistProductionId;
}
