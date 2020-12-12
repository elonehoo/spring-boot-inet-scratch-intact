package com.inet.code.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.inet.code.entity.portrait.po.Portrait;
import com.inet.code.mapper.PortraitMapper;
import com.inet.code.service.PortraitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 管理用户的默认头像 服务实现类
 * </p>
 *
 * @author HCY
 * @since 2020-12-11
 */
@Service
public class PortraitServiceImpl extends ServiceImpl<PortraitMapper, Portrait> implements PortraitService {

    @Resource
    private PortraitMapper portraitMapper;

    /**
     * 随机产生头像
     * @author HCY
     * @since 2020/12/12 下午 05:14
     * @return com.inet.code.entity.portrait.po.Portrait
     */
    @Override
    public Portrait getRandomImagesUrl() {
        int randomInt = RandomUtil.randomInt(0, this.count() - 1);
        return portraitMapper.getRandomImages(randomInt);
    }
}
