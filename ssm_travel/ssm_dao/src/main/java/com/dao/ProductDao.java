package com.dao;

import com.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductDao {

    /**
     * 查询所有产品信息
     * @return 产品信息列表
     */
    @Select("select * from product")
    public List<Product> findAllPro() throws Exception;

    /**
     * 添加产品
     * @param product
     */
    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    public void save(Product product);

    /**
     * 根据id查询产品
     * @param id
     * @return
     */
    @Select("select * from product where id=#{id}")
    public Product findById(Integer id);
}