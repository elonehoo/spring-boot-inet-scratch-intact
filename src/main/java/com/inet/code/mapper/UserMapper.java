package com.inet.code.mapper;

import com.inet.code.entity.dto.user.UserBaseDomain;
import com.inet.code.entity.po.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 用户的基本信息 Mapper 接口
 * </p>
 *
 * @author HCY
 * @since 2020-12-11
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 登录操作
     *
     * @author HCY
     * @since 2020/12/11 下午 07:10
     * @param account: 账号
     * @param password: 密码
     * @return com.inet.code.entity.domain.user.UserDomain
    */
    UserBaseDomain getLogin(String account, String password);
}
