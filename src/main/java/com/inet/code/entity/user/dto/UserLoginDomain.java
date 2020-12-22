package com.inet.code.entity.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 登录的DO层
 *
 * @author HCY
 * @since 2020/12/11 下午 06:28
*/
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel("登录的DO层")
public class UserLoginDomain {

    /**
     * 账号
     */
    @ApiModelProperty("账号")
    private String account;


    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String password;
}
