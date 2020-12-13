package com.inet.code.entity.tool;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 当前页数
 *
 * @author HCY
 * @since 2020/12/13 下午 08:26
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public  class  PageToll<T> {
    /**
     * 页数
     */
    private Integer currentPageNumber;

    /**
     * 条目数
     */
    private Integer pageSize;

    /**
     * 总页数
     */
    private Integer totalPageCount;

    /**
     * 内容
     */
    private List<T> records;
}
