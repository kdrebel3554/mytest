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
		// ����Mybatis���������ļ�SqlMapConfig.xml
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("SqlMapConfig.xml");
		// ����sqlSessionFactory
		sqlSessionFactory = sessionFactoryBuilder.build(inputStream);
	}

	@Test
	public void testQueryByUserQueryVo() {
		// ��ȡsqlSession����
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// ��ȡ�������
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		// ʹ�ð�װ����
		QueryVo queryVo = new QueryVo();
		// ����user����
		User user = new User();
		// user.setUsername("��");
		user.setUsername("��");
		// ��user�������õ���װ����
		queryVo.setUser(user);
		// ִ�в�ѯ
		List<User> list = userMapper.queryByUserQueryVo(queryVo);
		for (User u : list) {
			System.out.println(u);
		}
		sqlSession.close();
	}

	@Test
	public void testQueryUserById() {
		// ��ȡsessionFactory����Mybatis��Spring����֮����Spring��������
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// ��sqlSession��ȡUserMapper�ӿڵĴ������
		UserMapper userMapperProxy = sqlSession.getMapper(UserMapper.class);
		// ͨ���ӿڵĴ������ִ�нӿ��еķ���
		User user = userMapperProxy.queryUserById(10);
		System.out.println("---->" + user);
	}

	@Test
	public void testQueryUserByUsername() {
		// ��ȡsessionFactory����Mybatis��Spring����֮����Spring��������
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// ��sqlSession��ȡUserMapper�ӿڵĴ������
		UserMapper userMapperProxy = sqlSession.getMapper(UserMapper.class);
		List<User> userList = userMapperProxy.queryUserByUsername("��");
		System.out.println("---->" + userList);
	}

	@Test
	public void testSaveUser() {
		// ��ȡsessionFactory����Mybatis��Spring����֮����Spring��������
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// ��sqlSession��ȡUserMapper�ӿڵĴ������
		UserMapper userMapperProxy = sqlSession.getMapper(UserMapper.class);
		// ��������Ķ���
		User user = new User();
		user.setUsername("����");
		user.setBirthday(new Date());
		user.setSex("1");
		user.setAddress("���");

		userMapperProxy.saveUser(user);

		System.out.println(user);

		// ��spring���Ϻ���spring����
		sqlSession.commit();
		sqlSession.close();
	}

	@Test
	public void testQueryUserCount() {
		// ��ȡsqlSession����
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// ��ȡ�������
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		int count = userMapper.queryUserCount();
		System.out.println("�õ����ܼ�¼��Ϊ:" + count);
		sqlSession.close();
	}
	// ע�������������Ϊ���������ͣ������ѯ�����Ľ������һ����¼�����ս���һ���ֶε�ֵת��Ϊ�������

	@Test
	public void testQueryUserByWhere() {
		// ��ȡsqlSession����
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// ��ȡ�������
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		
		User user = new User();
		//user.setSex("1");
		user.setUsername("��");
		List<User> list = userMapper.queryUserByWhere(user);
		for (User u : list) {
			System.err.println(u);
		}
		sqlSession.close();
		/*
			ע�͵���user.setSex("1");�õ���SQL������£�
			SELECT id, username, birthday, sex, address FROM `user` WHERE sex = ? AND username LIKE '%��%' 
			���Խ��������Ȼ������
			����֮ǰ��ѧ�ģ�Ҫ���������⣬��Ҫ��д���sql����ѯ����Խ�࣬
			��Ҫ��д��sql�͸����ˣ���Ȼ�����ǲ����׵ġ�
		*/
	}
	
	@Test
	public void testQueryUserByCondition() {
		// ��ȡsqlSession����
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// ��ȡ�������
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		
		User user = new User();
		user.setSex("1");
		//user.setUsername("��");
		List<User> list = userMapper.queryUserByCondition(user);
		for (User u : list) {
			System.err.println(u);
		}
		sqlSession.close();
	}
	
	//where��ǩ
	@Test
	public void testQueryUserByConditionWhereTag() {
		// ��ȡsqlSession����
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// ��ȡ�������
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		
		User user = new User();
		user.setSex("1");
		user.setUsername("��");
		List<User> list = userMapper.queryUserByConditionWhereTag(user);
		for (User u : list) {
			System.err.println(u);
		}
		sqlSession.close();
	}
	
	//foreach��ǩ
	@Test
	public void testQueryUserByIds(){
		// ��ȡsqlSession����
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// ��ȡ�������
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		// ʹ��userMapperִ�и���������ѯ�û�
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
	
	//һ��һ���� ��ѯ������ͬʱ��ѯ�û���Ϣ
	@Test
	public void testQueryOrderUser() {
		// ��ȡsqlSession����
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// ��ȡ�������
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		List<cn.itcast.mybatis.pojo.OrderUser> listInfo = userMapper.queryOrderUser();
		for (cn.itcast.mybatis.pojo.OrderUser orderUser : listInfo) {
			System.out.println(orderUser);
		}
		sqlSession.close();
	}
	
	//һ�Զ������ѯ
	@Test
	public void testFindUserOrderList(){
		// ��ȡsqlSession����
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);	
		List<User> list = userMapper.findUserOrderList();
		for (User user : list) {
			System.out.println(user);
		}
		sqlSession.close();
	}
	
	
}
