package com.dao;

import com.domain.Role;
import com.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserDao {

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "email", property = "email"),
            @Result(column = "password", property = "password"),
            @Result(column = "phoneNum", property = "phoneNum"),
            @Result(column = "status", property = "status"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class, many = @Many(
                    select = "com.dao.RoleDao.findRoleByUserId"))
    })
    public UserInfo findByName(String username);

    /**
     * 查询所有用户信息
     * @return
     */
    @Select("select * from users")
    public List<UserInfo> findAllUser();

    /**
     * 保存用户信息
     * @param userInfo
     */
    @Insert("insert into users(username, password, email, phoneNum, status) " +
            "values(#{username}, #{password}, #{email}, #{phoneNum}, #{status})")
    public void saveUser(UserInfo userInfo);

    /**
     * 根据用户id查询用户信息
     * @param id
     * @return
     */
    @Select("select * from users where id = #{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "email", property = "email"),
            @Result(column = "password", property = "password"),
            @Result(column = "phoneNum", property = "phoneNum"),
            @Result(column = "status", property = "status"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class, many = @Many(
                    select = "com.dao.RoleDao.findRoleByUserId"))
    })
    public UserInfo findById(Integer id);

    /**
     * 根据用户id查询用户没有的角色信息
     * @param userId
     * @return
     */
    @Select("select * from role where id not in (select roleId from users_role where userId = #{userId})")
    public List<Role> findOtherRoles(Integer userId);

    /**
     * 添加用户角色关联
     * @param userId
     * @param roleId
     */
    @Insert("insert into users_role values(#{userId}, #{roleId})")
    void addRoleToUser(@Param("userId") Integer userId, @Param("roleId") Integer roleId);
}
