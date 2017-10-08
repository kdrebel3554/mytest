package cn.itcast.mybatis.test;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;




public class OrderUserTest {
	private SqlSessionFactory sqlSessionFactory;

	@Before
	public void init() throws Exception {
		SqlSessionFactoryBuilder sessionFactoryBuilder = new SqlSessionFactoryBuilder();
		// ����Mybatis���������ļ�SqlMapConfig.xml
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("SqlMapConfig.xml");
		// ����sqlSessionFactory
		sqlSessionFactory = sessionFactoryBuilder.build(inputStream);
	}

	

}
