package org.winter.framework.component;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.xml.parsers.ParserConfigurationException;
import org.winter.framework.aop.AbsInterceptorListener;
import org.winter.framework.parser.XmlClassPathConfigReader;
import org.xml.sax.SAXException;


public class Winter {

	public void initContainer(String[] xmlFile) throws ParserConfigurationException, SAXException, IOException {
		
		XmlClassPathConfigReader reader = new XmlClassPathConfigReader();
		
		HashMap<String, Object> beans = new HashMap<>();
		
		HashMap<String, Object[]> refBeans =new HashMap<>();
		        List<Class<?>> interfaces = new ArrayList<>();
		
		for (String xmlpath : xmlFile) {
					
			reader.read(xmlpath);		
			
			beans.putAll(reader.getBeans());
			refBeans.putAll(reader.getRefBeans());
			//获取所有扫描的interfaces
			interfaces.addAll(reader.getInterfaces());	
		}
		
		
		
		//hander ref bean 
		handleRefBean(beans,refBeans);
		
		
		setInterceptor(beans);
		
		AbsInterceptorListener soldier = getSoldierInterceptor(beans);

		ApplicationBeanFactory.setBeans(beans, soldier);

		ApplicationBeanFactory.setInterfaces(interfaces);
	}

	private void handleRefBean(Map<String,Object> beans, Map<String,Object[]> refBeans) {

		Iterator<Entry<String, Object[]>> iterator = refBeans.entrySet().iterator();
		while (iterator.hasNext()) {

			Entry<String, Object[]> next = iterator.next();
			String ref = next.getKey();
			Object[] methedObj = next.getValue();
			Object refInstance = beans.get(ref);
			if (refInstance != null) {
				try {
					Field field = methedObj[1].getClass().getDeclaredField(methedObj[0].toString());
					field.setAccessible(true);
					field.set(methedObj[1], refInstance);
				} catch (NoSuchFieldException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			iterator.remove();
		}

		refBeans = null;

	}

	

	private AbsInterceptorListener getSoldierInterceptor(HashMap<String, Object> map) {

		@SuppressWarnings("unchecked")
		HashMap<String, Object> interceptors = (HashMap<String, Object>) map.get("interceptors");
		return (AbsInterceptorListener) interceptors.get("soldier");

	}

	
	//责任链S
	private void setInterceptor(HashMap<String, Object> map) {

		HashMap<String, Object> interceptors = new HashMap<>();
		Iterator<Entry<String, Object>> it = map.entrySet().iterator();
		AbsInterceptorListener interceptorBoss = null;
		while (it.hasNext()) {
			Entry<String, Object> entry = it.next();
			Class<?> superclass = entry.getValue().getClass().getSuperclass();

			if ("org.winter.framework.aop.AbsInterceptorListener".equals(superclass.getName())) {
				AbsInterceptorListener interceptor = (AbsInterceptorListener) entry.getValue();

				if (interceptorBoss != null) {

					interceptor.setInterceptorBoss(interceptorBoss);
				}

				interceptorBoss = interceptor;

				//
				interceptors.put(entry.getKey(), interceptor);
				it.remove();

			}

		}

		AbsInterceptorListener soldier = interceptorBoss;
		interceptors.put("soldier", soldier);

		map.put("interceptors", interceptors);
	}

}


	
