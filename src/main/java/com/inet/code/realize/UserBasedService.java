package com.inet.code.realize;

import com.inet.code.entity.assist.dto.AssistLikeDomain;
import com.inet.code.entity.cipher.dto.CipherAmendDomain;
import com.inet.code.entity.production.dto.ProductionInsertDomain;
import com.inet.code.entity.production.dto.ProductionInsertUploadDomain;
import com.inet.code.entity.production.dto.ProductionSaveDomain;
import com.inet.code.entity.production.dto.ProductionSaveUploadDomain;
import com.inet.code.entity.user.dto.UserAmendDomain;
import com.inet.code.entity.user.dto.UserRegisterDomain;
import com.inet.code.utils.Result;
import org.springframework.web.multipart.MultipartFile;

import java.security.ProtectionDomain;

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

    /**
     * 新增用户的项目
     *
     * @author HCY
     * @since 2020/12/21 6:16 下午
     * @param token: 令牌
     * @param productionInsertDomain: 新增项目的实体类
     * @param path: URL路径
     * @return com.inet.code.utils.Result
    */
    Result getInsertProduction(String token, ProductionInsertDomain productionInsertDomain, String path);

    /**
     * 保存用户的项目
     *
     * @author HCY
     * @since 2020/12/24 9:27 上午
     * @param token: 令牌
     * @param productionSaveDomain: 保存项目的实体类
     * @param path: URL路径
     * @return com.inet.code.utils.Result 
    */
    Result getSaveProduction(String token, ProductionSaveDomain productionSaveDomain, String path);

    /**
     * 用户查看自己上传的项目
     *
     * @author HCY
     * @since 2020/12/24 5:55 下午
     * @param token: 令牌
     * @param current: 页数
     * @param size: 条目数
     * @param issue: 状态
     * @param path: URL路径
     * @return com.inet.code.utils.Result
    */
    Result getListProduction(String token, Integer current, Integer size, Boolean issue, String path);

    /**
     * 修改保存的项目
     *
     * @author HCY
     * @since 2020/12/27 下午7:26
     * @param token: 令牌
     * @param productionSaveUploadDomain: 修改保存的项目的实体类
     * @param path: URL路径
     * @return com.inet.code.utils.Result
    */
    Result getPutSaveProduction(String token, ProductionSaveUploadDomain productionSaveUploadDomain, String path);

    /**
     * 修改上传的项目
     *
     * @author HCY
     * @since 2020/12/27 下午8:02
     * @param token: 令牌
     * @param productionInsertUploadDomain:修改上传的项目的实体类
     * @param path: URL路径
     * @return com.inet.code.utils.Result
    */
    Result getUploadInsertProduction(String token, ProductionInsertUploadDomain productionInsertUploadDomain, String path);

    /**
     * 点赞，如果已经点赞了，则取消点赞
     *
     * @author HCY
     * @since 2020/12/28 10:42 下午
     * @param token: 令牌
     * @param assistLikeDomain: 点赞项目的实体类
     * @param path：URL路径
     * @return com.inet.code.utils.Result
    */
    Result getLikeProduction(String token, AssistLikeDomain assistLikeDomain,String path);
}
