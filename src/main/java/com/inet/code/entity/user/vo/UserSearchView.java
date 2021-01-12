package com.inet.code.entity.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 搜索名字的用户
 * @author HCY
 * @since 2021/1/12 9:05 PM
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("搜索名字的用户")
public class UserSearchView {

    /**
     * 用户序号
     */
    @ApiModelProperty("用户序号")
    private String userUuid;

    /**
     * 用户名字
     */
    @ApiModelProperty("用户序号")
    private String userName;

    /**
     * 用户头像
     */
    @ApiModelProperty("用户头像")
    private String userBuddha;

    /**
     * 用户项目数目
     */
    @ApiModelProperty("用户项目数目")
    private Integer productionUser;

    /**
     * 粉丝数目
     */
    @ApiModelProperty("粉丝数目")
    private Integer fanUser;
}
