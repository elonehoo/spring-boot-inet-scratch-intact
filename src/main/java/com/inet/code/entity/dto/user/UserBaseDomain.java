package com.inet.code.entity.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * user实体类的DO,用于控制业务实体
 * @author HCY
 * @since 2020/12/11 下午 05:35
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ToString
@Accessors(chain = true)
public class UserBaseDomain implements Serializable {
    /**
     * 用户的头像
     */
    private String userBuddha;

    /**
     * 用户的名字
     */
    private String userName;

    /**
     * 用户邮箱
     */
    private String userEmail;

    /**
     * 用户性别
     */
    private Boolean userSex;

    /**
     * 用户生日
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date userBirthday;

    /**
     * 用户地址
     */
    private String userCity;

    /**
     * 用户个性签名
     */
    private String userSignature;

    /**
     * 用户权限
     */
    private String roleName;

}
