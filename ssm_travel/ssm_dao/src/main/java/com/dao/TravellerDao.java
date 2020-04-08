package com.dao;

import com.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TravellerDao {


    /**
     * 根据订单id查询游客信息
     * @param id
     * @return
     */
    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId = #{id})")
    public List<Traveller> findByOrdersId(Integer id);
}
