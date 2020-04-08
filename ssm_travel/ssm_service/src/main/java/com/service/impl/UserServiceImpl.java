package com.service.impl;

import com.dao.UserDao;
import com.domain.Role;
import com.domain.UserInfo;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userDao.findByName(username);
        // 把自己的用户对象封装成userDetail
        User user = new User(userInfo.getUsername(), "{noop}"+userInfo.getPassword(), userInfo.getStatus()==0?false:true,true,true,true, getAuthority(userInfo.getRoles()));
        return user;
    }

    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role:roles) {
            list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        return list;
    }

    @Override
    public List<UserInfo> findAllUser() {
        return userDao.findAllUser();
    }

    @Override
    public void saveUser(UserInfo userInfo) {
        // 对密码进行加密
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userDao.saveUser(userInfo);
    }

    @Override
    public UserInfo findById(Integer id) {
        return userDao.findById(id);
    }

    @Override
    public List<Role> findOtherRoles(Integer userId) {
        return userDao.findOtherRoles(userId);
    }

    @Override
    public void addRoleToUser(Integer userId, Integer[] roleIds) {
        for (Integer roleId:roleIds) {
            userDao.addRoleToUser(userId,roleId);
        }
    }
}
