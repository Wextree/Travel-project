package com.service.impl;

import com.dao.OrdersDao;
import com.domain.Orders;
import com.github.pagehelper.PageHelper;
import com.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("orderService")
@Transactional
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersDao ordersDao;

    @Override
    public List<Orders> findAllOrders(int page, int size) {
        // 页码值和每页显示的条数
        PageHelper.startPage(page, size);
        return ordersDao.findAllOrders();
    }

    @Override
    public Orders findById(Integer id) {
        return ordersDao.findById(id);
    }
}
