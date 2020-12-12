package com.inet.code.entity.type.vo;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * 类别的基础视图
 *
 * @author HCY
 * @since 2020/12/11 下午 08:07
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TypeBaseView {

    private String typeUuid;

    private String typeName;
}
