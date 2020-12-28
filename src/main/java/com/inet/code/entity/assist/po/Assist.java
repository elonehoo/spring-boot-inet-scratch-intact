package com.inet.code.entity.assist.po;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户点赞项目实体类
 * </p>
 *
 * @author HCY
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@TableName("tbl_assist")
public class Assist implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 点赞的UUID
     */
    @TableId(value = "assist_uuid",type = IdType.UUID)
    private String assistUuid;

    /**
     * 点赞的项目
     */
    @TableField(value = "assist_production_id")
    private String assistProductionId;

    /**
     * 点赞的用户游戏
     */
    @TableField(value = "assist_user_email")
    private String assistUserEmail;

    /**
     * 点赞的创建时间
     */
    @TableField(value = "gmt_create",fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime gmtCreate;

    /**
     * 点赞的修改时间
     */
    @TableField(value = "gmt_modified",fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime gmtModified;


}
