package com.dao;

import com.domain.Permission;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionDao {

    /**
     * 根据角色id查询权限资源信息
     * @param roleId
     * @return
     */
    @Select("select * from permission where id in (select permissionId from role_permission where roleId = #{roleId})")
    List<Permission> findPermissionByRoleId(Integer roleId);

    /**
     * 查询所有权限信息
     * @return
     */
    @Select("select * from permission")
    List<Permission> findAll();
}
