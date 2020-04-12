package com.dao;

import com.domain.Member;
import com.domain.Orders;
import com.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OrdersDao {

    /**
     * 查询所有订单信息
     * @return
     */
    @Select("select * from orders")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(column = "productId", property = "product", one = @One(
                    select = "com.dao.ProductDao.findByIdTwo"))
    })
    public List<Orders> findAllOrders();

    /**
     * 通过id查询订单信息
     * @param id
     * @return
     */
    @Select("select * from orders where id = #{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(column = "productId", property = "product", javaType = Product.class, one = @One(
                    select = "com.dao.ProductDao.findById")),
            @Result(column = "memberId", property = "member", javaType = Member.class, one = @One(
                    select = "com.dao.MemberDao.findById")),
            @Result(column = "id", property = "travellers", javaType = java.util.List.class, many = @Many(
                    select = "com.dao.TravellerDao.findByOrdersId"))
    })
    public Orders findById(Integer id);
}
