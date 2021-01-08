package com.inet.code.service.impl;

import com.inet.code.entity.cipher.po.Cipher;
import com.inet.code.mapper.CipherMapper;
import com.inet.code.service.CipherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

    @Resource
    private CipherMapper cipherMapper;

    /**
     * 通过邮箱(账号)查找用户的密码
     *
     * @author HCY
     * @since 2020/12/12 下午 08:32
     * @param userEmail: 邮箱
     * @return com.inet.code.entity.cipher.po.Cipher
     */
    @Override
    public Cipher getByEmail(String userEmail) {
        return cipherMapper.getByEmail(userEmail);
    }

    /**
     * 通过用户的序号查找到用户的密码
     *
     * @author HCY
     * @since 2021/1/8 6:42 下午
     * @param userUuid: 用户的序号
     * @return com.inet.code.entity.cipher.po.Cipher
     */
    @Override
    public Cipher getByUserId(String userUuid) {
        return cipherMapper.getByUserId(userUuid);
    }
}
