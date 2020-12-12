package com.inet.code.realize.Impl;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import com.inet.code.entity.portrait.po.Portrait;
import com.inet.code.entity.portrait.vo.PortraitBuddhaView;
import com.inet.code.entity.user.dto.UserRegisterDomain;
import com.inet.code.entity.user.po.User;
import com.inet.code.realize.UserBasedService;
import com.inet.code.service.PortraitService;
import com.inet.code.service.UserService;
import com.inet.code.utils.CloneUtil;
import com.inet.code.utils.FromMailUtil;
import com.inet.code.utils.Result;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 用户模块的业务操作
 *
 * @author HCY
 * @since 2020/12/11 下午 06:24
*/
@Service
public class UserBasedServiceImpl implements UserBasedService {

    @Resource
    private UserService userService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private PortraitService portraitService;

    /**
     * 通过邮箱发送验证码
     *
     * @author HCY
     * @since 2020/12/12 下午 03:08
     * @param email: 邮箱
     * @param path: URL路径
     * @return com.inet.code.utils.Result
     */
    @Override
    public Result getVerification(String email, String path) {
        //判断邮箱是否合法
        if (!Validator.isEmail(email)){
            return new Result().result401("邮箱不合法，无法进行发送验证码",path);
        }
        //判断邮箱是否重复
        Result emailRepeat = this.getEmailRepeat(email, path);
        if (!emailRepeat.getStatus().equals(Result.STATUS_OK_200)){
            return emailRepeat;
        }
        //产生验证码
        String code = RandomUtil.randomString(5);
        //设置邮箱的内容
        MailUtil.send(
                this.getMail()
                , email
                , "博语编程社区验证码"
                , "注册验证码为:" + code + ",有效时长为5分钟"
                , false);
        //将验证码存入Redis
        redisTemplate.opsForValue().set(
                email
                ,code
                ,60 * 5
                , TimeUnit.SECONDS);
        //递交返回值
        return new Result().result200("验证码发送成功",path);
    }


    /**
     * 完成验证码之后的注册请求
     *
     * @author HCY
     * @since 2020/12/12 下午 04:00
     * @param userRegisterDomain: 用户注册的实体类 , 含有 用户的邮箱 和 验证码 和密码
     * @param path: URL路径
     * @return com.inet.code.utils.Result
     */
    @Override
    public Result getRegister(UserRegisterDomain userRegisterDomain, String path) {
        //判断邮箱是否合法
        if (!Validator.isEmail(userRegisterDomain.getEmail())) {
            return new Result().result403("邮箱不合法,无法进行注册", path);
        }
        //判断邮箱是否重复
        Result emailRepeat = this.getEmailRepeat(userRegisterDomain.getEmail(), path);
        if (!emailRepeat.getStatus().equals(Result.STATUS_OK_200)){
            return emailRepeat;
        }
        //判断验证码是否正确
        String verification = redisTemplate.opsForValue().get(userRegisterDomain.getEmail()) == null ? "" : (String) redisTemplate.opsForValue().get(userRegisterDomain.getEmail());
        if (!userRegisterDomain.getCode().equals(verification)){
            return new Result().result403("验证码输入错误",path);
        }
        //判断密码是否合法
        if (! Validator.isGeneral(userRegisterDomain.getPassword())) {
            return new Result().result401("密码格式不正确，需要包含大写字母，小写字母，数字",path);
        }
        //随机产生头像
        Portrait portrait = portraitService.getRandomImagesUrl();
        //进行用户的注册
        User user = new User(
                  CloneUtil.clone(portrait,PortraitBuddhaView.class).getPortraitSrc()
                , userRegisterDomain.getEmail()
                , userRegisterDomain.getEmail()
                ,true
                ,new Date()
                ,"",
                ""
        );
        return null;
    }

    /**
     * 设置邮箱的配置文件
     * @author HCY
     * @since 2020-11-16
     * @return MailAccount
     */
    private MailAccount getMail() {
        MailAccount account = new MailAccount();
        account.setHost(FromMailUtil.HOST);
        account.setPort(FromMailUtil.PORT);
        account.setAuth(true);
        account.setFrom(FromMailUtil.FROM);
        account.setUser(FromMailUtil.USER);
        account.setPass(FromMailUtil.PASS);
        account.setSslEnable(FromMailUtil.SSLENABLE);
        return account;
    }

    /**
     * 判断邮箱是否重复
     *
     * @author HCY
     * @since 2020/12/12 下午 03:12
     * @param email: 邮箱
     * @param path: URL路径
     * @return com.inet.code.utils.Result
    */
    private Result getEmailRepeat(String email, String path) {
        //判断邮箱是否产生了重复
        if (userService.getEmailRepeat(email) != null){
            return new Result().result403("邮箱产生了重复",path);
        }
        return new Result().result200("邮箱未产生重复",path);
    }
}
