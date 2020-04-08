package com.service;

import com.domain.Role;
import com.domain.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
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
    public void saveUser(UserInfo userInfo);

    /**
     * 根据用户id查询用户信息
     * @param id
     * @return
     */
    public UserInfo findById(Integer id);

    public List<Role> findOtherRoles(Integer userId);

    void addRoleToUser(Integer userId, Integer[] roleIds);
}
