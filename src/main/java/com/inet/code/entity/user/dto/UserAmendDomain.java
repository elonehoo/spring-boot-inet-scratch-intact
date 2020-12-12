package com.inet.code.entity.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 修改的用户实体类
 *
 * @author HCY
 * @since 2020/12/12 下午 08:56
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
@ApiModel
public class UserAmendDomain {
    /**
     * 用户的头像
     */
    @ApiModelProperty("用户的头像")
    private String userBuddha;

    /**
     * 用户的名字
     */
    @ApiModelProperty("用户的名字")
    private String userName;


    /**
     * 用户性别
     */
    @ApiModelProperty("用户性别")
    private Boolean userSex;

    /**
     * 用户生日
     */
    @ApiModelProperty("用户生日")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date userBirthday;

    /**
     * 用户地址
     */
    @ApiModelProperty("用户地址")
    private String userCity;

    /**
     * 用户个性签名
     */
    @ApiModelProperty("用户个性签名")
    private String userSignature;

    public UserAmendDomain(String userBuddha, String userName, Boolean userSex, Date userBirthday, String userCity, String userSignature) {
        this.userBuddha = userBuddha;
        this.userName = userName;
        this.userSex = userSex;
        this.userBirthday = userBirthday;
        this.userCity = userCity;
        this.userSignature = userSignature;
    }
}
