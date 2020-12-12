package com.inet.code.service.impl;

import com.inet.code.entity.role.po.Role;
import com.inet.code.mapper.RoleMapper;
import com.inet.code.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 设计用户的权限 服务实现类
 * </p>
 *
 * @author HCY
 * @since 2020-12-11
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    /**
     * 通过权限名字查找到权限的实体类
     *
     * @author HCY
     * @since 2020/12/12 下午 08:08
     * @param roleName: 需要的权限名称
     * @return com.inet.code.entity.role.po.Role
     */
    @Override
    public Role getRoleName(String roleName) {
        return roleMapper.getRoleName(roleName);
    }
}
