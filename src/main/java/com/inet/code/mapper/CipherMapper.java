package com.inet.code.mapper;

import com.inet.code.entity.cipher.po.Cipher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 管理用户的密码 Mapper 接口
 * </p>
 *
 * @author HCY
 * @since 2020-12-11
 */
public interface CipherMapper extends BaseMapper<Cipher> {

    /**
     * 通过邮箱(账号)查找用户的密码
     *
     * @author HCY
     * @since 2020/12/12 下午 08:32
     * @param userEmail: 邮箱
     * @return com.inet.code.entity.cipher.po.Cipher
     */
    Cipher getByEmail(String userEmail);
}
