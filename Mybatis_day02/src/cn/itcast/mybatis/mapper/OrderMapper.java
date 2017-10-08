package cn.itcast.mybatis.mapper;

import java.util.List;

import cn.itcast.mybatis.pojo.Order;

public interface OrderMapper {
	List<Order> queryOrderAll();
	
	//
	List<Order> queryOrderUserResultMap();
}
