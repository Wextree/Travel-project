package com.controller;

import com.domain.Product;
import com.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/findAll.do")
    @RolesAllowed("ADMIN")
    public ModelAndView findAllPro() throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Product> products = productService.findAllPro();
        mv.addObject("productList", products);
        mv.setViewName("product-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String savePro(Product product){
        productService.save(product);
        return "redirect:findAll.do";
    }
}
