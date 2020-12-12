package com.inet.code.realize;

import com.inet.code.entity.user.dto.UserRegisterDomain;
import com.inet.code.utils.Result;

/**
 * 用户模块的业务控操作
 *
 * @author HCY
 * @since 2020/12/11 下午 06:23
*/
public interface UserBasedService {
    /**
     * 通过邮箱发送验证码
     *
     * @author HCY
     * @since 2020/12/12 下午 03:08
     * @param email: 邮箱
     * @param path: URL路径
     * @return com.inet.code.utils.Result
    */
    Result getVerification(String email, String path);

    /**
     * 完成验证码之后的注册请求
     *
     * @author HCY
     * @since 2020/12/12 下午 04:00
     * @param userRegisterDomain: 用户注册的实体类 , 含有 用户的邮箱 和 验证码 和密码
     * @param path: URL路径
     * @return com.inet.code.utils.Result
    */
    Result getRegister(UserRegisterDomain userRegisterDomain, String path);
}
