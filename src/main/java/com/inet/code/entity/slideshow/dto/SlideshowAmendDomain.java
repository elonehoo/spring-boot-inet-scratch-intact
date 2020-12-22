package com.inet.code.entity.slideshow.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 修改轮播图的地址或者状态
 *
 * @author HCY
 * @since 2020/12/16 下午 03:27
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
@ApiModel("修改轮播图的地址或者状态")
public class SlideshowAmendDomain {

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
    @ApiModelProperty("是否展示的状态")
    private Boolean slideshowIsShow;

    public SlideshowAmendDomain(String slideshowUuid, String slideshowUrl, Boolean slideshowIsShow) {
        this.slideshowUuid = slideshowUuid;
        this.slideshowUrl = slideshowUrl;
        this.slideshowIsShow = slideshowIsShow;
    }
}
