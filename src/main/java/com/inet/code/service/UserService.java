package com.inet.code.service;

import com.inet.code.entity.user.dto.UserBaseDomain;
import com.inet.code.entity.user.po.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.inet.code.entity.user.vo.UserFanView;

/**
 * <p>
 * 用户的基本信息 服务类
 * </p>
 *
 * @author HCY
 * @since 2020-12-11
 */
public interface UserService extends IService<User> {

    /**
     * 登录操作
     *
     * @author HCY
     * @since 2020/12/12 上午 10:54
     * @param account: 账号
     * @param password: 密码
     * @return com.inet.code.entity.user.dto.UserBaseDomain
    */
    UserBaseDomain getLogin(String account, String password);

    /**
     * 查找该邮箱的用户
     *
     * @author HCY
     * @since 2020/12/12 下午 03:14
     * @param email: 邮箱
     * @return com.inet.code.entity.user.po.User
    */
    User getEmailRepeat(String email);

    /**
     * 查看粉丝
     *
     * @author HCY
     * @since 2020/12/13 下午 02:57
     * @param userEmail: 用户的邮箱
     * @param pages: 页数
     * @return com.inet.code.entity.user.vo.UserFanView
    */
    UserFanView getCheckFan(String userEmail, Integer pages);
}
