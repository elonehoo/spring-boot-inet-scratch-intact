package com.inet.code.entity.role.po;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 设计用户的权限
 * </p>
 *
 * @author HCY
 * @since 2020-12-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tbl_role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 作品uuid
     */
    @TableId(value = "production_uuid",type = IdType.UUID)
    private String productionUuid;

    /**
     * 作品名称
     */
    @TableField(value = "production_name")
    private String productionName;

    /**
     * 封面的URL地址
     */
    @TableField(value = "production_cover")
    private String productionCover;

    /**
     * 作品的用户邮箱
     */
    @TableField(value = "production_user_email")
    private String productionUserEmail;

    /**
     * 项目的地址
     */
    @TableField(value = "production_url")
    private String productionUrl;

    /**
     * 作品的说明(备注)
     */
    @TableField(value = "production_remark")
    private String productionRemark;

    /**
     * 作品操作说明
     */
    @TableField(value = "production_oi")
    private String productionOi;

    /**
     * 作品的类型
     */
    @TableField(value = "production_type")
    private String productionType;

    /**
     * 作品是否允许改编
     */
    @TableField(value = "production_recompose")
    private Boolean productionRecompose;

    /**
     * 作品是发布还是草稿
     */
    @TableField(value = "production_issue")
    private Boolean productionIssue;

    /**
     * 创建时间
     */
    @TableField(value = "gmt_create",fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;

    /**
     * 修改时间
     */
    @TableField(value = "gmt_modified",fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtModified;


}
