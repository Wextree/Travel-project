package com.controller;

import com.domain.Permission;
import com.domain.Role;
import com.domain.UserInfo;
import com.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<Role> roles = roleService.findAllRole();
        mv.addObject("roleList", roles);
        mv.setViewName("role-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String saveRole(Role role){
        roleService.saveRole(role);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id", required = true) Integer roleId){
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(roleId);
        List<Permission> permissions = roleService.findOtherPermission(roleId);
        mv.addObject("role", role);
        mv.addObject("permissionList", permissions);
        mv.setViewName("role-permission-add");
        return mv;
    }

    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(name = "roleId", required = true) Integer roleId, @RequestParam(name = "ids", required = true) Integer[] permissionIds){
        roleService.addPermissionToRole(roleId, permissionIds);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id", required = true) Integer roleId){
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(roleId);
        mv.addObject("role", role);
        mv.setViewName("role-show");
        return mv;
    }
}
