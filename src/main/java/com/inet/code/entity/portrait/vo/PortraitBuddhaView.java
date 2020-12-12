package com.inet.code.entity.portrait.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 显示用户头像的实体类
 *
 * @author HCY
 * @since 2020/12/12 下午 05:12
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class PortraitBuddhaView {

    /**
     * 默认头像的SRC地址
     */
    private String portraitSrc;
}
