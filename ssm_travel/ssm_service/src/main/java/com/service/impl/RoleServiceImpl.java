package com.service.impl;

import com.dao.RoleDao;
import com.domain.Permission;
import com.domain.Role;
import com.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> findAllRole() {
        return roleDao.findAllRole();
    }

    @Override
    public void saveRole(Role role) {
        roleDao.saveRole(role);
    }

    @Override
    public Role findById(Integer roleId) {
        return roleDao.findById(roleId);
    }

    @Override
    public List<Permission> findOtherPermission(Integer roleId) {
        return roleDao.findOtherPermission(roleId);
    }

    @Override
    public void addPermissionToRole(Integer roleId, Integer[] permissionIds) {
        for (Integer permissionId:permissionIds) {
            roleDao.addPermissionToRole(roleId, permissionId);
        }
    }
}
