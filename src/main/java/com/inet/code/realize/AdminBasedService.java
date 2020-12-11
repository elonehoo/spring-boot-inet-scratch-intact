package com.inet.code.realize;

import com.inet.code.entity.dto.label.LabelAppendDoMain;
import com.inet.code.entity.dto.type.TypeAppendDoMain;
import com.inet.code.utils.Result;

/**
 * 管理员的业务操作
 *
 * @author HCY
 * @since 2020/12/11 下午 06:23
*/
public interface AdminBasedService {
    /**
     * 标签的添加
     *
     * @author HCY
     * @since 2020/12/11 下午 09:10
     * @param labelAppendDoMain: 标签的基础属性 内涵 标签名字
     * @param path: URL路径
     * @return com.inet.code.utils.Result
    */
    Result getAppendLabel(LabelAppendDoMain labelAppendDoMain, String path);

    /**
     * 类别的添加
     *
     * @author HCY
     * @since 2020/12/11 下午 09:32
     * @param typeAppendDoMain: 类别的基础属性, 内涵 类被名称
     * @param path: URL路径
     * @return com.inet.code.utils.Result
    */
    Result getAppendType(TypeAppendDoMain typeAppendDoMain, String path);
}
