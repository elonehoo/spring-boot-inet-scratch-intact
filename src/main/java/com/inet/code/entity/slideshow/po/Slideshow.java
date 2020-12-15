package com.inet.code.entity.slideshow.po;

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
 * 轮播图的管理
 * </p>
 *
 * @author HCY
 * @since 2020-12-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@TableName("tbl_slideshow")
public class Slideshow implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 轮播图的uuid
     */
    @TableId(value = "slideshow_uuid",type = IdType.UUID)
    private String slideshowUuid;

    /**
     * 轮播图的URL
     */
    @TableField(value = "slideshow_url")
    private String slideshowUrl;

    /**
     * 是否展示的状态
     */
    @TableField(value = "slideshow_is_show")
    private Boolean slideshowIsShow;

    /**
     * 创建时间
     */
    @TableField(value = "gmt_create",fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    @TableField(value = "gmt_modified",fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime gmtModified;


}
