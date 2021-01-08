package com.inet.code.entity.user.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 管理员注册实体类
 *
 * @author HCY
 * @since 2021/1/8 下午12:46
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("管理员注册实体类")
public class UserRegisterDTO {

    /**
     * 用户昵称
     */
    @ApiModelProperty("用户昵称")
    private String userName;

}
