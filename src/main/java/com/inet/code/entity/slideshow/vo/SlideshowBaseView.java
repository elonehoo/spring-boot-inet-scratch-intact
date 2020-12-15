package com.inet.code.entity.slideshow.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 展示轮播图
 *
 * @author HCY
 * @since 2020/12/15 12:10 下午
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class SlideshowBaseView {

    /**
     * 轮播图的uuid
     */
    @ApiModelProperty("轮播图的uuid")
    private String slideshowUuid;

    /**
     * 轮播图的URL
     */
    @ApiModelProperty("轮播图的URL")
    private String slideshowUrl;

    /**
     * 是否展示的状态
     */
    @ApiModelProperty("展示的状态")
    private Boolean slideshowIsShow;


}
