package com.inet.code.entity.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 展示五个点赞数最高的用户
 *
 * @author HCY
 * @since 2020/12/30 上午11:00
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("展示五个点赞数最高的用户")
public class UserFiveLikeView {

    /**
     * 用户的uuid
     */
    @ApiModelProperty("用户的uuid")
    private String userUuid;

    /**
     * 头像的连接地址
     */
    @ApiModelProperty("头像的连接地址")
    private String userBuddha;

    /**
     * 用户昵称
     */
    @ApiModelProperty("用户昵称")
    private String userName;

    /**
     * 用户的点赞数
     */
    @ApiModelProperty("用户的点赞数")
    private Integer likeTotal;
}
