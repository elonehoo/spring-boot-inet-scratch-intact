package com.inet.code.entity.cipher.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 修改密码的实体类
 *
 * @author HCY
 * @since 2020/12/12 下午 08:23
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("修改密码的实体类")
public class CipherAmendDomain {

    /**
     * 旧密码
     */
    @ApiModelProperty("旧密码")
    private String oldPassword;
    /**
     * 用户新密码
     */
    @ApiModelProperty("新密码")
    private String cipherPassword;
}
