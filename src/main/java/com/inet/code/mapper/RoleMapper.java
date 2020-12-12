package com.inet.code.mapper;

import com.inet.code.entity.role.po.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 设计用户的权限 Mapper 接口
 * </p>
 *
 * @author HCY
 * @since 2020-12-11
 */
public interface RoleMapper extends BaseMapper<Role> {

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
