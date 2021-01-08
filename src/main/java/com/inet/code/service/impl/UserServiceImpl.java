package com.inet.code.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.inet.code.entity.user.dto.UserBaseDomain;
import com.inet.code.entity.user.po.User;
import com.inet.code.entity.user.vo.UserFanView;
import com.inet.code.entity.user.vo.UserFiveLikeView;
import com.inet.code.mapper.UserMapper;
import com.inet.code.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户的基本信息 服务实现类
 * </p>
 *
 * @author HCY
 * @since 2020-12-11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 登录操作
     *
     * @author HCY
     * @since 2020/12/12 上午 10:54
     * @param account: 账号
     * @param password: 密码
     * @return com.inet.code.entity.user.dto.UserBaseDomain
     */
    @Override
    public UserBaseDomain getLogin(String account, String password) {
        return userMapper.getLogin(account,password);
    }

    /**
     * 查找该邮箱的用户
     *
     * @author HCY
     * @since 2020/12/12 下午 03:14
     * @param email: 邮箱
     * @return com.inet.code.entity.user.po.User
     */
    @Override
    public User getEmailRepeat(String email) {
        return userMapper.getByEmail(email);
    }

    /**
     * 查看自己的粉丝
     *
     * @author HCY
     * @since 2020/12/13 下午 03:52
     * @param userEmail: 用户邮箱
     * @param pages: 页数
     * @return java.util.List<com.inet.code.entity.user.vo.UserFanView>
    */
    @Override
    public List<UserFanView> getCheckFocus(String userEmail, Integer pages) {
        return userMapper.getCheckFocus(userEmail,(pages - 1) * 20 );
    }

    /**
     * 查看关注的人条目数
     *
     * @author HCY
     * @since 2020/12/13 下午 09:04
     * @param userEmail: 用户邮箱
     * @return java.lang.Integer
     */
    @Override
    public Integer getCheckFocusTotal(String userEmail) {
        return userMapper.getCheckFocusTotal(userEmail);
    }

    /**
     * 查看关注自己的用户
     *
     * @author HCY
     * @since 2020/12/13 下午 09:27
     * @param userEmail: 用户邮箱
     * @param pages: 页数
     * @return java.util.List<com.inet.code.entity.user.vo.UserFanView>
     */
    @Override
    public List<UserFanView> getCheckFans(String userEmail, Integer pages) {
        return userMapper.getCheckFans(userEmail , (pages - 1) * 20);
    }

    /**
     * 查看关注自己的用户的条目数
     *
     * @author HCY
     * @since 2020/12/13 下午 09:32
     * @param userEmail: 用户邮箱
     * @return java.lang.Integer
     */
    @Override
    public Integer getCheckFansTotal(String userEmail) {
        return userMapper.getCheckFansTotal(userEmail);
    }

    /**
     * 通过邮箱继续登陆操作
     *
     * @author HCY
     * @since 2020/12/14 5:20 下午
     * @param email: 用户邮箱
     * @return com.inet.code.entity.user.dto.UserBaseDomain
     */
    @Override
    public UserBaseDomain getByRoleEmail(String email) {
        return userMapper.getByRoleEmail(email);
    }

    /**
     * 该日期新增多少人数
     *
     * @author HCY
     * @since 2020/12/24 9:59 下午
     * @param day: 日期
     * @return java.lang.Integer
     */
    @Override
    public Integer getNewUsers(String day) {
        return userMapper.getNewUsers(day + "%");
    }

    /**
     * 查看点赞数最高的五个用户
     *
     * @author HCY
     * @since 2020/12/30 上午11:05
     * @return java.util.List<com.inet.code.entity.user.vo.UserFiveLikeView>
    */
    @Override
    public List<UserFiveLikeView> getListFiveUsers() {
        return userMapper.getListFiveUsers();
    }

    /**
     * 访客项目，在访客模式下可以查看十个点赞数目多的用户
     *
     * @author HCY
     * @since 2020/12/30 下午3:51
     * @return java.util.List<com.inet.code.entity.user.vo.UserFiveLikeView>
     */
    @Override
    public List<UserFiveLikeView> getListTenUser() {
        return userMapper.getListTenUser();
    }
    /**
     * 判断用户的账号是否重复
     *
     * @author HCY
     * @since 2021/1/8 下午1:13
     * @param userEmail: 账号
     * @return java.lang.Boolean
     */
    @Override
    public User getByEmail(String userEmail) {
        return userMapper.getByEmail(userEmail);
    }

    /**
     * 分页查看用户
     *
     * @author HCY
     * @since 2021/1/8 下午2:34
     * @param userPage: 分页条件
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.inet.code.entity.user.po.User>
     */
    @Override
    public IPage<User> getPage(Page<User> userPage) {
        return userMapper.getPage(userPage);
    }


}
