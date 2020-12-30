package com.inet.code.mapper;

import com.inet.code.entity.user.dto.UserBaseDomain;
import com.inet.code.entity.user.po.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.inet.code.entity.user.vo.UserFanView;
import com.inet.code.entity.user.vo.UserFiveLikeView;

import java.util.List;

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
     * 查看自己的粉丝
     *
     * @author HCY
     * @since 2020/12/13 下午 03:51
     * @param userEmail: 用户邮箱
     * @param pages: 页数
     * @return java.util.List<com.inet.code.entity.user.vo.UserFanView>
    */
    List<UserFanView> getCheckFocus(String userEmail, int pages);

    /**
     * 查看关注的人的条目数
     *
     * @author HCY
     * @since 2020/12/13 下午 09:07
     * @param userEmail: 用户邮箱
     * @return java.lang.Integer
    */
    Integer getCheckFocusTotal(String userEmail);

    /**
     * 查看关注自己的用户
     *
     * @author HCY
     * @since 2020/12/13 下午 09:29
     * @param userEmail: 用户邮箱
     * @param pages: 页数
     * @return java.util.List<com.inet.code.entity.user.vo.UserFanView>
    */
    List<UserFanView> getCheckFans(String userEmail, int pages);

    /**
     * 查看关注自己的用户的条目数
     *
     * @author HCY
     * @since 2020/12/13 下午 09:32
     * @param userEmail: 用户邮箱
     * @return java.lang.Integer
     */
    Integer getCheckFansTotal(String userEmail);

    /**
     * 通过邮箱继续登陆操作
     *
     * @author HCY
     * @since 2020/12/14 5:20 下午
     * @param email: 用户邮箱
     * @return com.inet.code.entity.user.dto.UserBaseDomain
     */
    UserBaseDomain getByRoleEmail(String email);

    /**
     * 该日期新增多少人数
     *
     * @author HCY
     * @since 2020/12/24 9:59 下午
     * @param day: 日期
     * @return java.lang.Integer
     */
    Integer getNewUsers(String day);

    /**
     * 查看点赞数最高的五个用户
     *
     * @author HCY
     * @since 2020/12/30 上午11:05
     * @return java.util.List<com.inet.code.entity.user.vo.UserFiveLikeView>
     */
    List<UserFiveLikeView> getListFiveUsers();

}
