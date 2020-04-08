package com.controller;

import com.domain.Role;
import com.domain.UserInfo;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/findAll.do")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView findAllUser(){
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userInfos = userService.findAllUser();
        mv.addObject("userList", userInfos);
        mv.setViewName("user-list");
        return mv;
    }

    @RequestMapping("/save.do")
//    @PreAuthorize("authentication.principal.username='wex'") // 只有wex可以操作
    public String saveUser(UserInfo userInfo){
        userService.saveUser(userInfo);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id", required = true) Integer id){
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(id);
        mv.addObject("user", userInfo);
        mv.setViewName("user-show");
        return mv;
    }

    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name="id", required = true) Integer userId){
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(userId);
        List<Role> roles = userService.findOtherRoles(userId);
        mv.addObject("user", userInfo);
        mv.addObject("roleList", roles);
        mv.setViewName("user-role-add");
        return mv;
    }

    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId", required = true) Integer userId, @RequestParam(name = "ids", required = true) Integer[] roleIds){
        userService.addRoleToUser(userId, roleIds);
        return "redirect:findAll.do";
    }
}
