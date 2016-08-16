package org.winter.fromwork;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map.Entry;

import org.winter.fromwork.aop.AbsInterceptorListener;
import org.winter.fromwork.aop.DefaultProxyFactory;

public class ApplicationBeanFactory {
	private static HashMap<String, Object> map = new HashMap<>();

	private static AbsInterceptorListener soldier = null;

	public static Object getBean(String id) {

		Object bean = null;

		bean = map.get(id);
		// autoWired
		autowiredBean(bean);

		return new DefaultProxyFactory().getProxyInstance(bean, soldier);

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
							System.out.println("beanId is err");
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

}
