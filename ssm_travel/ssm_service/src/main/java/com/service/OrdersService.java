package com.service;

import com.domain.Orders;

import java.util.List;

public interface OrdersService {
    /**
     * 查询所有订单信息
     * @return
     */
    public List<Orders> findAllOrders(int page, int size);

    /**
     * 通过id查询订单信息
     * @param id
     * @return
     */
    public Orders findById(Integer id);
}
