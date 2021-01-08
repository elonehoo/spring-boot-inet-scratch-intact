package com.inet.code.entity.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 管理员修改用户
 *
 * @author HCY
 * @since 2021/1/8 下午5:33
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("管理员修改用户")
public class UserModifyDTO {

    /**
     * 用户序号
     */
    @ApiModelProperty("用户序号")
    private String userUuid;

    /**
     * 用户密码
     */
    @ApiModelProperty("密码")
    private String cipherPassword;
}
