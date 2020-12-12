package com.inet.code.service;

import com.inet.code.entity.role.po.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 设计用户的权限 服务类
 * </p>
 *
 * @author HCY
 * @since 2020-12-11
 */
public interface RoleService extends IService<Role> {

    /**
     * 通过权限名字查找到权限的实体类
     *
     * @author HCY
     * @since 2020/12/12 下午 08:08
     * @param roleName: 需要的权限名称
     * @return com.inet.code.entity.role.po.Role
    */
    Role getRoleName(String roleName);
}
