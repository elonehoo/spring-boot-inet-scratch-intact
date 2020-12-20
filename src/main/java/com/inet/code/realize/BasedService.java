package com.inet.code.realize;

import com.inet.code.entity.user.dto.UserLandingDomain;
import com.inet.code.entity.user.dto.UserLoginDomain;
import com.inet.code.utils.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * 基础的业务操作
 * @author HCY
 * @since 2020/12/11 下午 06:24
*/
public interface BasedService {
    /**
     * 登录操作
     *
     * @author HCY
     * @since 2020/12/11 下午 06:33
     * @param userLoginDomain: 登录的实体类(含有账号和密码)
     * @param path: URL路径
     * @return com.inet.code.utils.Result
    */
    Result getLogin(UserLoginDomain userLoginDomain, String path);

    /**
     * 登出操作
     *
     * @author HCY
     * @since 2020/12/11 下午 07:58
     * @param token: 令牌
     * @param path: URL路径
     * @return com.inet.code.utils.Result
    */
    Result getLogout(String token, String path);

    /**
     * 查看所有的类别
     *
     * @author HCY
     * @since 2020/12/11 下午 08:02
     * @param path: URL路径
     * @return com.inet.code.utils.Result
    */
    Result getListType(String path);

    /**
     * 查看所有的标签
     *
     * @author HCY
     * @since 2020/12/11 下午 08:49
     * @param path: URL路径
     * @return com.inet.code.utils.Result
    */
    Result getListLabel(String path);

    /**
     * 通过邮箱发送验证码进行登陆操作
     *
     * @author HCY
     * @since 2020/12/14 4:07 下午
     * @param email: 邮箱
     * @param path: URL路径
     * @return com.inet.code.utils.Result
    */
    Result getVerificationLanding(String email, String path);

    /**
     * 通过验证码登陆
     *
     * @author HCY
     * @since 2020/12/14 5:11 下午
     * @param userLandingDomain: 验证码登陆的实体类
     * @param path: URL路径
     * @return com.inet.code.utils.Result
    */
    Result getLanding(UserLandingDomain userLandingDomain, String path);

    /**
     * 文件上传，返回的是文件的URL地址
     *
     * @author HCY
     * @since 2020/12/14 1:33 下午
     * @param file: 上传的文件
     * @param path: URL路径
     * @return com.inet.code.utils.Result
     */
    Result getUploadFiles(MultipartFile file, String path);

    /**
     * 通过token返回用户的信息
     *
     * @author HCY
     * @since 2020/12/19 下午 05:50
     * @param token: 令牌
     * @param path: URL路径
     * @return com.inet.code.utils.Result
    */
    Result getInteraction(String token, String path);
}
