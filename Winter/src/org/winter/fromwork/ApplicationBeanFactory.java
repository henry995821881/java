package org.winter.fromwork;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.winter.fromwork.aop.AbsInterceptorListener;
import org.winter.fromwork.aop.DefaultProxyFactory;

public class ApplicationBeanFactory {
	private static HashMap<String, Object> map = new HashMap<>();

	private static AbsInterceptorListener soldier = null;
	
	
	private static String sessionfactoryId ="";
	static Logger logger = Logger.getLogger(ApplicationBeanFactory.class);
	
	private static DefaultProxyFactory proxyFactory = new DefaultProxyFactory();
	public static void setSessionfactoryId(String sessionfactoryId) {
		ApplicationBeanFactory.sessionfactoryId = sessionfactoryId;
	}

	public static Object getBean(String id) {

		Object bean = null;

		bean = map.get(id);
		
		
		if(sessionfactoryId.equals(id) ||"mybatis".equals(id)){
			return bean;
		}

		if(bean ==null){
			return bean;
		}
		return proxyFactory.getProxyInstance(bean, soldier);

	}

	private static void autowiredBean(Object bean) {

		
		if(bean ==null){
			return;
		}
		Field[] fields = bean.getClass().getDeclaredFields();

		for (Field field : fields) {
			field.setAccessible(true);

			Autowired annotation = field.getAnnotation(Autowired.class);
			if (annotation != null) {

				String beanId = annotation.beanId();
				if (beanId != null && !"".equals(beanId)) {

					Object result = ApplicationBeanFactory.getBean(beanId);
					try {
						if (result != null) {

							field.set(bean, result);
						}else{
							logger.warn(beanId+":=="+"Autowired:beanId is err or not bean in container");
							
						}
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		}

	}

	public static HashMap<String, Object> getAllBeanWithoutProxy() {

		return map;
	}

	public static void clearBeans() {
		map.clear();
	}

	public static void setBeans(HashMap<String, Object> beans, AbsInterceptorListener soldier1) {
		soldier = soldier1;
		map = beans;

	}
	
	public static void wiredAndprintBeans() {
		
		
		logger.info("beans in container----------");
		//自动注入and print
		for (Entry<String, Object> e : map.entrySet()) {

			Object bean = e.getValue();
			if(bean !=null){
				autowiredBean(bean);
			}
			
			logger.info("register: " + e.getKey() + ":" + e.getValue());
			
		}
		logger.info("----------");
	}

}
