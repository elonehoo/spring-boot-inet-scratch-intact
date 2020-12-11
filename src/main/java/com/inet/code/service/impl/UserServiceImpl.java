package com.inet.code.service.impl;

import com.inet.code.entity.po.User;
import com.inet.code.mapper.UserMapper;
import com.inet.code.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
