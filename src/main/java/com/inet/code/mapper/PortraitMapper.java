package com.inet.code.mapper;

import com.inet.code.entity.portrait.po.Portrait;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 管理用户的默认头像 Mapper 接口
 * </p>
 *
 * @author HCY
 * @since 2020-12-11
 */
public interface PortraitMapper extends BaseMapper<Portrait> {

    /**
     * 随机产生默认头像中的一个
     *
     * @author HCY
     * @since 2020/12/12 下午 05:16
     * @param randomInt: 随机值(下标)
     * @return com.inet.code.entity.portrait.po.Portrait
    */
    Portrait getRandomImages(int randomInt);
}
