package com.inet.code.entity.slideshow.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 增加轮播图的实体类
 *
 * @author HCY
 * @since 2020/12/16 9:44 上午
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
@ApiModel("增加轮播图的实体类")
public class SlideshowIncreaseDomain {

    @ApiModelProperty("轮播图的URL地址")
    private String slideshowUrl;

    @ApiModelProperty("轮播图的显示状态")
    private Boolean slideshowIsShow;

    public SlideshowIncreaseDomain(String slideshowUrl, Boolean slideshowIsShow) {
        this.slideshowUrl = slideshowUrl;
        this.slideshowIsShow = slideshowIsShow;
    }
}
