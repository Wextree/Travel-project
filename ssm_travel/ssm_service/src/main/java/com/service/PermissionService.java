package com.service;

import com.domain.Permission;

import java.util.List;

public interface PermissionService {
    /**
     * 查询所有权限信息
     * @return
     */
    List<Permission> findAll();
}
