package com.inet.code.entity.label.dto;

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
 * 修改的标签的实体类
 * @author HCY
 * @since 2020/12/12 上午 09:37
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class LabelAmendDoMain {
    /**
     * 标签的uuid
     */
    @ApiModelProperty("标签的uuid")
    private String labelUuid;

    /**
     * 标签的名字
     */
    @ApiModelProperty("标签的名字")
    private String labelName;
}
