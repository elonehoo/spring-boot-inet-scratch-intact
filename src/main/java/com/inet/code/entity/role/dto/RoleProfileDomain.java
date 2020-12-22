package com.inet.code.entity.role.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 修改权限的实体类
 *
 * @author HCY
 * @since 2020/12/12 下午 07:26
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
@ApiModel("修改权限的实体类")
public class RoleProfileDomain {

    /**
     * 权限序号
     */
    @ApiModelProperty("权限序号")
    private String roleUuid;

    /**
     * 权限名称
     */
    @ApiModelProperty("权限名称")
    private String roleName;
}
