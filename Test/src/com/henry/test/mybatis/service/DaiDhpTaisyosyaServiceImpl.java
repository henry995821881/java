package com.henry.test.mybatis.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


import com.henry.test.mybatis.bean.DaiDhpTaisyosya;
import com.henry.test.mybatis.dao.DaiDhpTaisyosyaDao;


public class DaiDhpTaisyosyaServiceImpl {

	public static void main(String[] args) {

		
		// mybatis的配置文件
		String resource = "com/henry/test/mybatis/config.xml";
		// 使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
		InputStream is = DaiDhpTaisyosyaServiceImpl.class.getClassLoader().getResourceAsStream(resource);
		// 构建sqlSession的工厂
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
		try {
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			is =null;
		}
		// 使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）
		// Reader reader = Resources.getResourceAsReader(resource);
		// 构建sqlSession的工厂
		// SqlSessionFactory sessionFactory = new
		// SqlSessionFactoryBuilder().build(reader);
		// 创建能执行映射文件中sql的sqlSession
		SqlSession session = sessionFactory.openSession();
		/**
		 * 映射sql的标识字符串，
		 * me.gacl.mapping.userMapper是userMapper.xml文件中mapper标签的namespace属性的值，
		 * getUser是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
		 */
		// 执行查询返回一个唯一user对象的sql
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("sex", "1");
		data.put("dhpArrayCd", new String[]{"0","A","B","E"});
		//String statement = "com.henry.test.mybatis.dao.DaiDhpTaisyosyaDao.getDhpTaisyosyaList";// 映射sql的标识字符串
		//List<DaiDhpTaisyosya> selectList = session.selectList(statement, map);
		//System.out.println(selectList);
	
		DaiDhpTaisyosyaDao dao = session.getMapper(DaiDhpTaisyosyaDao.class);
		List<DaiDhpTaisyosya> dhpTaisyosyaList = dao.getDhpTaisyosyaList(data);
		System.out.println(dhpTaisyosyaList);
		
		session.close();
		

	}

}
