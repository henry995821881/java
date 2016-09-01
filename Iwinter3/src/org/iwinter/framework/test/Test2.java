package org.iwinter.framework.test;



import java.util.Map;

import org.iwinter.framework.context.DefaultBeanFactory;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

public class Test2 {

	
	
	
	@Test
	public void test() {
		
		DefaultBeanFactory app = new DefaultBeanFactory();
		app.setFileXMlLocation("D:/new-workSpace/Iwinter3/config/winter/app.xml");
		//app.setClassPathXMlLocation("winter/app.xml");
		app.init();
		
		Map<String, Object> beans = app.getBeans();
		
		System.out.println(beans);
		System.out.println(JSON.toJSONString(beans));
	}

}
