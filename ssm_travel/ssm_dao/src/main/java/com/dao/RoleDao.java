package com.dao;

import com.domain.Permission;
import com.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RoleDao {

    /**
     * 通过用户id查询对应的jues
     * @param userId
     * @return
     */
    @Select("select * from role where id in (select roleId from users_role where userId=#{userId})")
    @Results({
            @Result(id =true, column = "id", property = "id"),
            @Result(column = "roleName;", property = "roleName;"),
            @Result(column = "roleDesc", property = "roleDesc"),
            @Result(column = "id", property = "permissions", javaType = java.util.List.class, many = @Many(
                    select = "com.dao.PermissionDao.findPermissionByRoleId"
            ))
    })
    public List<Role> findRoleByUserId(Integer userId);

    /**
     * 查询所有角色信息
     * @return
     */
    @Select("select * from role")
    public List<Role> findAllRole();

    /**
     * 保存角色信息
     * @param role
     */
    @Insert("insert into role(roleName,roleDesc) value(#{roleName},#{roleDesc})")
    public void saveRole(Role role);

    /**
     * 根据id查询角色信息
     * @param roleId
     * @return
     */
    @Select("select * from role where id=#{roleId}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "roleName", property = "roleName"),
            @Result(column = "roleDesc", property = "roleDesc"),
            @Result(property = "permissions", column = "id", javaType = java.util.List.class, many = @Many(
                    select = "com.dao.PermissionDao.findPermissionByRoleId"))
    })
    Role findById(Integer roleId);

    /**
     * 查询该角色未获得的权限
     * @param roleId
     * @return
     */
    @Select("select * from permission where id not in (select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findOtherPermission(Integer roleId);

    /**
     * 关联角色和权限
     * @param roleId
     * @param permissionId
     */
    @Insert("insert into role_permission(roleId, permissionId) values(#{roleId}, #{permissionId})")
    void addPermissionToRole(@Param("roleId") Integer roleId, @Param("permissionId") Integer permissionId);
}
