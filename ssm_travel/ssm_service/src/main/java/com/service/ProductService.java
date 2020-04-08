package com.service;

import com.domain.Product;

import java.util.List;

public interface ProductService {
    /**
     * 查询所有产品信息
     * @return 产品信息列表
     */
     public List<Product> findAllPro() throws Exception;

    /**
     * 添加产品
     * @param product
     */
    public void save(Product product);

    /**
     * 根据id查询产品
     * @param id
     * @return
     */
    public Product findById(Integer id);
}
