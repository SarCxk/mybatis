package com.scy.dao;

import com.scy.bean.OrdersExample;
import com.scy.bean.Orders;
import com.scy.bean.OrdersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrdersDAO {
    long countByExample(OrdersExample example);

    int deleteByExample(OrdersExample example);

    int deleteByPrimaryKey(Integer ordersId);

    int insert(Orders record);

    int insertSelective(Orders record);

    List<Orders> selectByExample(OrdersExample example);

    Orders selectByPrimaryKey(Integer ordersId);

    int updateByExampleSelective(@Param("record") Orders record, @Param("example") OrdersExample example);

    int updateByExample(@Param("record") Orders record, @Param("example") OrdersExample example);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);

    Orders selectPersonByOrdersId(Integer ordersId);
}