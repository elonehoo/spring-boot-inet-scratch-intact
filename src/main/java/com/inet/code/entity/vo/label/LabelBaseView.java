package com.inet.code.entity.vo.label;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 标签的基础视图
 *
 * @author HCY
 * @since 2020/12/11 下午 08:51
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class LabelBaseView {
    /**
     * 标签的uuid
     */
    private String labelUuid;

    /**
     * 标签的名字
     */
    private String labelName;
}
