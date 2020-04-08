package com.service;

import com.domain.Permission;
import com.domain.Role;


import java.util.List;

public interface RoleService {
    /**
     * 查询所有角色信息
     * @return
     */
    public List<Role> findAllRole();

    /**
     * 保存角色信息
     * @param role
     */
    public void saveRole(Role role);

    Role findById(Integer roleId);

    List<Permission> findOtherPermission(Integer roleId);

    void addPermissionToRole(Integer roleId, Integer[] permissionIds);
}
