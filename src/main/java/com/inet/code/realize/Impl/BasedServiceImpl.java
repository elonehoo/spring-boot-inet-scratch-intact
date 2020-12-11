package com.inet.code.realize.Impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.inet.code.entity.dto.user.UserBaseDomain;
import com.inet.code.entity.dto.user.UserLoginDomain;
import com.inet.code.entity.vo.label.LabelBaseView;
import com.inet.code.entity.vo.type.TypeBaseView;
import com.inet.code.mapper.UserMapper;
import com.inet.code.realize.BasedService;
import com.inet.code.service.LabelService;
import com.inet.code.service.TypeService;
import com.inet.code.utils.CloneUtil;
import com.inet.code.utils.JwtUtils;
import com.inet.code.utils.Result;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 基础模块的操作
 *
 * @author HCY
 * @since 2020/12/11 下午 06:25
*/
@Service
public class BasedServiceImpl implements BasedService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private TypeService typeService;

    @Resource
    private LabelService labelService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 登录操作
     *
     * @author HCY
     * @since 2020/12/11 下午 06:33
     * @param userLoginDomain: 登录的实体类(含有账号和密码)
     * @param path: URL路径
     * @return com.inet.code.utils.Result
     */
    @Override
    public Result getLogin(UserLoginDomain userLoginDomain, String path) {
        //将密码进行加密处理,在进行登录的查找
        UserBaseDomain userDomain = userMapper.getLogin(
                  userLoginDomain.getAccount()
                , DigestUtil.md5Hex(userLoginDomain.getPassword()));
        //账号或者密码产生了错误
        if (userDomain == null){
            return new Result().result404("您的账号或者密码错误",path);
        }
        //设置产生token的条件
        Map<String, String> map = new HashMap<>(2);
        map.put("userName",userDomain.getUserName());
        map.put("roleName",userDomain.getRoleName());
        //产生token
        String token = JwtUtils.getToken(map);
        //存入Redis,存储时间为7天
        redisTemplate.opsForValue().set(token,userDomain,7, TimeUnit.DAYS);
        //设置返回值
        Map<String, Object> results = new HashMap<>(3);
        results.put("info","登录成功");
        results.put("token",token);
        results.put("user",userDomain);
        return new Result().result200(results,path);
    }

    /**
     * 登出操作
     *
     * @author HCY
     * @since 2020/12/11 下午 07:58
     * @param token: 令牌
     * @param path: URL路径
     * @return com.inet.code.utils.Result
     */
    @Override
    public Result getLogout(String token, String path) {
        if (redisTemplate.delete(token)){
            return new Result().result200(
                    "退出成功"
                    ,path);
        }
        return new Result().result500(
                "退出失败"
                ,path);
    }

    /**
     * 查看所有的类别
     *
     * @author HCY
     * @since 2020/12/11 下午 08:02
     * @param path: URL路径
     * @return com.inet.code.utils.Result
     */
    @Override
    public Result getListType(String path) {
        return new Result().result200(
                 CloneUtil.batchClone(typeService.list(),TypeBaseView.class)
                ,path);
    }

    /**
     * 查看所有的标签
     *
     * @author HCY
     * @since 2020/12/11 下午 08:49
     * @param path: URL路径
     * @return com.inet.code.utils.Result
     */
    @Override
    public Result getListLabel(String path) {
        return new Result().result200(
                 CloneUtil.batchClone(labelService.list(), LabelBaseView.class)
                ,path);
    }
}
