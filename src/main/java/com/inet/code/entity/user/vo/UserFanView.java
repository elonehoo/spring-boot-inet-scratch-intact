package com.inet.code.entity.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 显示用户的粉丝的实体类
 *
 * @author HCY
 * @since 2020/12/13 上午 10:32
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("显示用户的粉丝的实体类")
public class UserFanView {

    /**
     * 用户邮箱
     */
    @ApiModelProperty("用户邮箱")
    private String userEmail;

    /**
     * 用户的名字
     */
    @ApiModelProperty("用户名")
    private String userName;

    /**
     * 用户头像URL
     */
    @ApiModelProperty("用户头像的URL")
    private String userBuddha;

    /**
     * 用户的关注状态
     * true : 互相关注
     * false : 单方面
     */
    @ApiModelProperty("用户的关注状态")
    private Boolean isFriend;

}
