package com.inet.code.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.inet.code.entity.production.po.Production;
import com.inet.code.mapper.ProductionMapper;
import com.inet.code.service.ProductionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 上传的项目的管理模块 服务实现类
 * </p>
 *
 * @author HCY
 * @since 2020-12-11
 */
@Service
public class ProductionServiceImpl extends ServiceImpl<ProductionMapper, Production> implements ProductionService {

    /**
     * 查看近七天的上传文件的数量
     *
     * @author HCY
     * @since 2020/12/25 8:10 下午
     * @param issue: 状态
     * @param days: 日期
     * @return java.util.Map<java.lang.String,java.lang.Integer>
     */
    @Override
    public Map<String, Integer> getListNewProduction(Boolean issue, String[] days) {
        QueryWrapper<Production> queryWrapper = new QueryWrapper<>();
        Map<String, Integer> map = new HashMap<>(days.length);
        //判断状态
        if (issue != null){
            queryWrapper.eq("production_issue",issue);
        }
        for (String day : days) {
            queryWrapper.likeRight("gmt_create",day);
            map.put(day,this.count(queryWrapper));
        }
        return map;
    }
}
