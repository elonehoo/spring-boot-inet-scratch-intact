package com.inet.code.mapper;

import com.inet.code.entity.user.dto.UserBaseDomain;
import com.inet.code.entity.user.po.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.inet.code.entity.user.vo.UserFanView;

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

    /**
     * 查找该邮箱的用户
     *
     * @author HCY
     * @since 2020/12/12 下午 03:14
     * @param email: 邮箱
     * @return com.inet.code.entity.user.po.User
     */
    User getByEmail(String email);

    /**
     * 查看粉丝
     *
     * @author HCY
     * @since 2020/12/13 下午 02:57
     * @param userEmail: 用户的邮箱
     * @param pages: 页数
     * @return com.inet.code.entity.user.vo.UserFanView
     */
    UserFanView getCheckFan(String userEmail, int pages);
}
