package org.winter.framework.component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.winter.framework.annotation.Action;
import org.winter.framework.annotation.Autowired;
import org.winter.framework.annotation.Repository;
import org.winter.framework.annotation.Service;
import org.winter.framework.aop.AbsInterceptorListener;
import org.winter.framework.aop.DefaultCglibProxyFactory;
import org.winter.framework.exception.AutowiredException;

import sun.print.resources.serviceui;

public class ApplicationBeanFactory {
	private static HashMap<String, Object> map = new HashMap<>();

	private static AbsInterceptorListener soldier = null;
	

	
	static Logger logger = Logger.getLogger(ApplicationBeanFactory.class);
	
	private static DefaultCglibProxyFactory proxyFactory = new DefaultCglibProxyFactory();
	 private static  List<Class<?>> interfaces = new ArrayList<>();
	 
	 
	 

	public static List<Class<?>> getInterfaces() {
		return interfaces;
	}

	public static void setInterfaces(List<Class<?>> interfaces) {
		ApplicationBeanFactory.interfaces = interfaces;
	}

	public static Object getBean(String id) {

		Object bean = null;

		bean = map.get(id);
		
		
		

		if(bean ==null){
			return bean;
		}
		
		Object anno = bean.getClass().getAnnotation(Action.class);
		if(anno ==null){
			anno =bean.getClass().getAnnotation(Service.class);
		}
		/*if(anno ==null){
			anno =bean.getClass().getAnnotation(Repository.class);
		}*/
		
		if(anno ==null){
			
			return bean;
		}else{
			
			
			return proxyFactory.getProxyInstance(bean, soldier);
		}
		

	}
	
	public static Object getBeanWithoutProxy(String id) {

		

		return map.get(id);
		
		
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
							try {
								throw new AutowiredException(bean.getClass().getName()+":"+beanId);
							} catch (AutowiredException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							//logger.warn(beanId+":=="+"Autowired:beanId is err or not bean in container");
							
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
