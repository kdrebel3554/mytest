package cn.itcast.mybatis.test;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import cn.itcast.mybatis.mapper.OrderMapper;
import cn.itcast.mybatis.mapper.UserMapper;
import cn.itcast.mybatis.pojo.Order;

public class OrderMapperTest {
	private SqlSessionFactory sqlSessionFactory;
	@Before
	public void init() throws Exception {
		SqlSessionFactoryBuilder sessionFactoryBuilder = new SqlSessionFactoryBuilder();
		// ����Mybatis���������ļ�SqlMapConfig.xml
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("SqlMapConfig.xml");
		// ����sqlSessionFactory
		sqlSessionFactory = sessionFactoryBuilder.build(inputStream);
	}
	
	@Test
	public void testQueryOrderAll() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
		//ִ�в�ѯ
		List<Order> list = orderMapper.queryOrderAll();
		for(Order o : list){
			System.err.println("------" + o);
		}
		sqlSession.close();
	}
	
	//
	@Test
	public void testQueryOrderUserResultMap(){
		// ��ȡsqlSession����
		SqlSession sqlSession = sqlSessionFactory.openSession();
		OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
		List<Order> list = orderMapper.queryOrderUserResultMap();
		for (Order order : list) {
			System.out.println("--->" + order);
		}
		sqlSession.close();
	}
}
