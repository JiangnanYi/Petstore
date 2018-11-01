package com.lij.dao;

import com.lij.entity.Order;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderMapper {

    List<Order> selectAll();

    int insert(Order record);

    Order selectByPrimarypId(Integer pId);

    Order selectByPrimarypStatus(String ostatus);

    int deleteByPrimaryKey(Integer oId);

    int insertSelective(Order record);
}