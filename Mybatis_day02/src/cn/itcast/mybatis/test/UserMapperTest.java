package cn.itcast.mybatis.test;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import cn.itcast.mybatis.mapper.UserMapper;
import cn.itcast.mybatis.pojo.Order;
import cn.itcast.mybatis.pojo.QueryVo;
import cn.itcast.mybatis.pojo.User;

public class UserMapperTest {
	private SqlSessionFactory sqlSessionFactory;

	@Before
	public void init() throws Exception {
		SqlSessionFactoryBuilder sessionFactoryBuilder = new SqlSessionFactoryBuilder();
		// 加载Mybatis核心配置文件SqlMapConfig.xml
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("SqlMapConfig.xml");
		// 创建sqlSessionFactory
		sqlSessionFactory = sessionFactoryBuilder.build(inputStream);
	}

	@Test
	public void testQueryByUserQueryVo() {
		// 获取sqlSession对象
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 获取代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		// 使用包装对象
		QueryVo queryVo = new QueryVo();
		// 设置user条件
		User user = new User();
		// user.setUsername("张");
		user.setUsername("三");
		// 将user条件设置到包装类中
		queryVo.setUser(user);
		// 执行查询
		List<User> list = userMapper.queryByUserQueryVo(queryVo);
		for (User u : list) {
			System.out.println(u);
		}
		sqlSession.close();
	}

	@Test
	public void testQueryUserById() {
		// 获取sessionFactory对象，Mybatis和Spring整合之后，由Spring容器管理
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 从sqlSession获取UserMapper接口的代理对象
		UserMapper userMapperProxy = sqlSession.getMapper(UserMapper.class);
		// 通过接口的代理对象执行接口中的方法
		User user = userMapperProxy.queryUserById(10);
		System.out.println("---->" + user);
	}

	@Test
	public void testQueryUserByUsername() {
		// 获取sessionFactory对象，Mybatis和Spring整合之后，由Spring容器管理
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 从sqlSession获取UserMapper接口的代理对象
		UserMapper userMapperProxy = sqlSession.getMapper(UserMapper.class);
		List<User> userList = userMapperProxy.queryUserByUsername("张");
		System.out.println("---->" + userList);
	}

	@Test
	public void testSaveUser() {
		// 获取sessionFactory对象，Mybatis和Spring整合之后，由Spring容器管理
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 从sqlSession获取UserMapper接口的代理对象
		UserMapper userMapperProxy = sqlSession.getMapper(UserMapper.class);
		// 创建保存的对象
		User user = new User();
		user.setUsername("刘备");
		user.setBirthday(new Date());
		user.setSex("1");
		user.setAddress("蜀国");

		userMapperProxy.saveUser(user);

		System.out.println(user);

		// 和spring整合后由spring管理
		sqlSession.commit();
		sqlSession.close();
	}

	@Test
	public void testQueryUserCount() {
		// 获取sqlSession对象
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 获取代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		int count = userMapper.queryUserCount();
		System.out.println("得到的总记录数为:" + count);
		sqlSession.close();
	}
	// 注：输出数据类型为简单数据类型，必须查询出来的结果集有一条记录，最终将第一个字段的值转换为输出类型

	@Test
	public void testQueryUserByWhere() {
		// 获取sqlSession对象
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 获取代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		
		User user = new User();
		//user.setSex("1");
		user.setUsername("张");
		List<User> list = userMapper.queryUserByWhere(user);
		for (User u : list) {
			System.err.println(u);
		}
		sqlSession.close();
		/*
			注释掉：user.setSex("1");得到的SQL语句如下：
			SELECT id, username, birthday, sex, address FROM `user` WHERE sex = ? AND username LIKE '%张%' 
			测试结果二很显然不合理。
			按照之前所学的，要解决这个问题，需要编写多个sql，查询条件越多，
			需要编写的sql就更多了，显然这样是不靠谱的。
		*/
	}
	
	@Test
	public void testQueryUserByCondition() {
		// 获取sqlSession对象
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 获取代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		
		User user = new User();
		user.setSex("1");
		//user.setUsername("张");
		List<User> list = userMapper.queryUserByCondition(user);
		for (User u : list) {
			System.err.println(u);
		}
		sqlSession.close();
	}
	
	//where标签
	@Test
	public void testQueryUserByConditionWhereTag() {
		// 获取sqlSession对象
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 获取代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		
		User user = new User();
		user.setSex("1");
		user.setUsername("张");
		List<User> list = userMapper.queryUserByConditionWhereTag(user);
		for (User u : list) {
			System.err.println(u);
		}
		sqlSession.close();
	}
	
	//foreach标签
	@Test
	public void testQueryUserByIds(){
		// 获取sqlSession对象
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 获取代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		// 使用userMapper执行根据条件查询用户
		QueryVo vo = new QueryVo();
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(10);
		list.add(12);
		list.add(13);
		vo.setIds(list);
		List<User> listUser = userMapper.queryUserByIds(vo);
		for (User user : listUser) {
			System.out.println(user);
		}
		sqlSession.close();
	}
	
	//一对一关联 查询订单的同时查询用户信息
	@Test
	public void testQueryOrderUser() {
		// 获取sqlSession对象
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 获取代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		List<cn.itcast.mybatis.pojo.OrderUser> listInfo = userMapper.queryOrderUser();
		for (cn.itcast.mybatis.pojo.OrderUser orderUser : listInfo) {
			System.out.println(orderUser);
		}
		sqlSession.close();
	}
	
	//一对多关联查询
	@Test
	public void testFindUserOrderList(){
		// 获取sqlSession对象
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);	
		List<User> list = userMapper.findUserOrderList();
		for (User user : list) {
			System.out.println(user);
		}
		sqlSession.close();
	}
	
	
}
