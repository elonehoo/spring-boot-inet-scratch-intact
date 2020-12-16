package com.inet.code.realize;

import com.inet.code.entity.cipher.dto.CipherAmendDomain;
import com.inet.code.entity.user.dto.UserAmendDomain;
import com.inet.code.entity.user.dto.UserRegisterDomain;
import com.inet.code.utils.Result;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     * 修改密码
     *
     * @author HCY
     * @since 2020/12/12 下午 08:26
     * @param token: 令牌
     * @param cipherAmendDomain: 修改密码的实体类 含有 旧密码,新密码
     * @param path: URL路径
     * @return com.inet.code.utils.Result
    */
    Result getAmendCipher(String token, CipherAmendDomain cipherAmendDomain, String path);

    /**
     * 用户修改自己的个人信息
     *
     * @author HCY
     * @since 2020/12/12 下午 09:03
     * @param token : 令牌
     * @param userAmendDomain: 修改的用户的实体类 , 包含 : 头像,名字,性别,生日,地址,个性签名
     * @param path: URL路径
     * @return com.inet.code.utils.Result
    */
    Result getAmendUser(String token,UserAmendDomain userAmendDomain, String path);

    /**
     * 关注或者取消关注的用户邮箱
     *
     * @author HCY
     * @since 2020/12/12 下午 09:43
     * @param token: 令牌
     * @param focusEmail: 关注或者取消关注的用户邮箱
     * @param path: URL路径
     * @return com.inet.code.utils.Result
    */
    Result getFocus(String token, String focusEmail, String path);

    /**
     * 查看关注自己的用户
     *
     * @author HCY
     * @since 2020/12/13 下午 02:50
     * @param token: 令牌
     * @param pages: 页数
     * @param path: URL路径
     * @return com.inet.code.utils.Result
    */
    Result getCheckFocus(String token, Integer pages, String path);

    /**
     * 查看关注自己的人
     *
     * @author HCY
     * @since 2020/12/13 下午 09:22
     * @param token: 令牌
     * @param pages: 页数
     * @param path: URL路径
     * @return com.inet.code.utils.Result
    */
    Result getCheckFans(String token, Integer pages, String path);


}
