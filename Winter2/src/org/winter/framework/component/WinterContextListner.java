package org.winter.framework.component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.xml.parsers.ParserConfigurationException;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import org.winter.framework.annotation.Repository;
import org.winter.framework.mybatis.SqlSessionFactoryBuilderBean;
import org.xml.sax.SAXException;

import com.app.bean.DaiDhpTaisyosya;
import com.app.dao.DaiDhpTaisyosyaDao;





public class WinterContextListner implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

		ApplicationBeanFactory.clearBeans();

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {

		ServletContext servletContext = arg0.getServletContext();

		initLogger4j(servletContext);
		initWinter(servletContext);
		initMybatis();

		ApplicationBeanFactory.wiredAndprintBeans();
	}

	
	private void initMybatis()   {
		
		
	
		
			 
		SqlSessionFactoryBuilderBean builderBean =null;
		HashMap<String, Object> map = ApplicationBeanFactory.getAllBeanWithoutProxy();
		
		for ( Entry<String,Object> entry : map.entrySet()) {
			
			Object bean = entry.getValue();
			if(bean instanceof  SqlSessionFactoryBuilderBean){
				
				builderBean =(SqlSessionFactoryBuilderBean) bean;
				
				break;
			}
		}
		
		if(builderBean !=null){
			
			SqlSessionFactory sqlSessionFactory = builderBean.build();
		
			
			SqlSession session = sqlSessionFactory.openSession();
			
			List<Class<?>> interfaces = ApplicationBeanFactory.getInterfaces();
			
			
			for (Class<?> clazz : interfaces) {
				
				if(clazz.getAnnotation(Repository.class) !=null){
					
					
					Object mapper = session.getMapper(clazz);
					String id = clazz.getSimpleName().substring(0, 1).toLowerCase()+clazz.getSimpleName().substring(1);
					
					ApplicationBeanFactory.getAllBeanWithoutProxy().put(id, mapper);
				}
				
				
			}
			
		
			
				
			//session.close();
		}
		
		
		
		//loadmybatis( servletContext);
		
	}

	/*private void loadmybatis(ServletContext servletContext) {
		Object bean = ApplicationBeanFactory.getBean("mybatis");
		if (bean != null) {

			try {
				@SuppressWarnings("unchecked")
				HashMap<String, Object> mybatisConfig = (HashMap<String, Object>) bean;

				// mybatis的配置文件
				String resource = mybatisConfig.get("path").toString();
				String factoryBuilderClass = mybatisConfig.get("factoryBuilderClass").toString();
				String sessionFactoryId = mybatisConfig.get("factoryId").toString();
				Class<?> clazz = null;
				try {
					clazz = Class.forName(factoryBuilderClass);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Method method = clazz.getDeclaredMethod("build", InputStream.class);
				method.setAccessible(true);
				// 使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
				InputStream is = WinterContextListner.class.getClassLoader().getResourceAsStream(resource);
				// 构建sqlSession的工厂
				
				SqlSessionFactory sessionFactory = (SqlSessionFactory) method.invoke(clazz.newInstance(), is);
				try {
					is.close();
				} catch (IOException e) {
					is = null;
					e.printStackTrace();
				}
				HashMap<String, Object> beans = ApplicationBeanFactory.getAllBeanWithoutProxy();
				beans.put(sessionFactoryId, sessionFactory);
				ApplicationBeanFactory.setSessionfactoryId(sessionFactoryId);
			} catch (NoSuchMethodException e) {
				System.out.println("mybatis 配置问题");
				e.printStackTrace();
			} catch (SecurityException e) {
				System.out.println("mybatis 配置问题");
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				System.out.println("mybatis 配置问题");
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				System.out.println("mybatis 配置问题");
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				System.out.println("mybatis 配置问题");
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				System.out.println("mybatis 配置问题");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		
	}*/

	private void initWinter(ServletContext servletContext) {
		System.out.println("init winter");
		String winterPaths = servletContext.getInitParameter("winterConfigLocation");
		String[] paths = winterPaths.split(",");
		try {
			new Winter().initContainer(paths);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void initLogger4j(ServletContext servletContext) {
		
		System.out.println("init log4g");
		String path = servletContext.getInitParameter("Log4jConfigLocation");
		
		InputStream is1 =null;
		Properties prop = new Properties();
		try {
			 is1 = WinterContextListner.class.getClassLoader().getResourceAsStream(path);
			
		prop.load(is1);	
		PropertyConfigurator.configure(prop);
		System.out.println("PropertyConfigurator init log4j");
		} catch (Exception e) {
			BasicConfigurator.configure();
			System.out.println("BasicConfigurator init log4j");
			e.printStackTrace();
		}finally {
			if(is1 !=null){
				try {
					is1.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					is1 =null;
				}
			}
		}


	}

}
