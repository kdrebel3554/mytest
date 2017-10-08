package cn.itcast.mybatis.mapper;

import java.util.List;

import cn.itcast.mybatis.pojo.OrderUser;
import cn.itcast.mybatis.pojo.QueryVo;
import cn.itcast.mybatis.pojo.User;

public interface UserMapper {
	public User queryUserById(int id);
	
	public List<User> queryUserByUsername(String username);
	
	public void saveUser(User user);
	
	//根据包装类查询用户的信息
	public List<User> queryByUserQueryVo(QueryVo queryVo);
	
	//
	public int queryUserCount();
	
	public List<User> queryUserByWhere(User user);
	
	public List<User> queryUserByCondition(User user);
	
	List<User> queryUserByConditionWhereTag(User user);
	//
	List<User> queryUserByConditionSqlSnippet(User user);
	//
	List<User> queryUserByIds(QueryVo queryVo);
	
	//一对一，查询订单同时包含用户信息
	List<OrderUser> queryOrderUser();
	
	List<User> findUserOrderList();
}
