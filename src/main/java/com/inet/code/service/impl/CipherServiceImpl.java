package com.inet.code.service.impl;

import com.inet.code.entity.po.Cipher;
import com.inet.code.mapper.CipherMapper;
import com.inet.code.service.CipherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理用户的密码 服务实现类
 * </p>
 *
 * @author HCY
 * @since 2020-12-11
 */
@Service
public class CipherServiceImpl extends ServiceImpl<CipherMapper, Cipher> implements CipherService {

}
