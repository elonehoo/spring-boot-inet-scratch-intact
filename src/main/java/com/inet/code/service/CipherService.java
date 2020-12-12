package com.inet.code.service;

import com.inet.code.entity.cipher.po.Cipher;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 管理用户的密码 服务类
 * </p>
 *
 * @author HCY
 * @since 2020-12-11
 */
public interface CipherService extends IService<Cipher> {

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
