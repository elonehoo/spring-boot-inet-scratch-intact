package com.inet.code.service.impl;

import com.inet.code.entity.po.Type;
import com.inet.code.mapper.TypeMapper;
import com.inet.code.service.TypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 类别的管理器 服务实现类
 * </p>
 *
 * @author HCY
 * @since 2020-12-11
 */
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService {

}
