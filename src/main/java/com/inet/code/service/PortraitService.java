package com.inet.code.service;

import com.inet.code.entity.portrait.po.Portrait;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 管理用户的默认头像 服务类
 * </p>
 *
 * @author HCY
 * @since 2020-12-11
 */
public interface PortraitService extends IService<Portrait> {

    /**
     * 随机产生头像
     * @author HCY
     * @since 2020/12/12 下午 05:14
     * @return com.inet.code.entity.portrait.po.Portrait
    */
    Portrait getRandomImagesUrl();
}
