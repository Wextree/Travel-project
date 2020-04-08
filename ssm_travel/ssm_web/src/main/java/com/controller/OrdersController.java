package com.controller;

import com.domain.Orders;
import com.github.pagehelper.PageInfo;
import com.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    //    @Secured("ROLE_ADMIN")  // 记得加前缀
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name="page", required = true, defaultValue = "1") Integer page,
                                @RequestParam(name = "size", required = true, defaultValue = "size") Integer size){
        ModelAndView mv = new ModelAndView();
        List<Orders> orders = ordersService.findAllOrders(page, size);
        PageInfo pageInfo = new PageInfo(orders);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("orders-list");
        return mv;
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name="id", required = true) Integer id){
        ModelAndView mv = new ModelAndView();
        Orders orders = ordersService.findById(id);
        mv.addObject("orders", orders);
        mv.setViewName("orders-show");
        return mv;
    }
}
