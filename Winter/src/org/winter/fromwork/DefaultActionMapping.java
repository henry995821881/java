package org.winter.fromwork;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class DefaultActionMapping {

	public Object[] getActionAndMethod(String requestURI, String contextPath) {
		
		
		String relativePath = requestURI.substring(contextPath.length());
		HashMap<String, Object> beans = ApplicationBeanFactory.getAllBeanWithoutProxy();
		
		
		HashMap<String, Object> actionList =new HashMap<>();
		for(Entry<String, Object> entry :beans.entrySet()){
			Object obj = entry.getValue();
			String key = entry.getKey();
			if(obj.getClass().getAnnotation(Action.class) != null){
				actionList.put(key, obj);
				
			}
		}
		
		for (Entry<String, Object> entry : actionList.entrySet()) {
			Object action = entry.getValue();
			String key = entry.getKey();
			
			Method[] methods = action.getClass().getDeclaredMethods();
			for (Method method : methods) {
				
				ActionMethod actionMethod = method.getAnnotation(ActionMethod.class);
				if(actionMethod !=null && relativePath.equals(actionMethod.url())){
					
					Object actionProxy = ApplicationBeanFactory.getBean(key);
					Object[] actionAndMethod = new Object[]{actionProxy,method};
					//Object[] actionAndMethod = new Object[]{action,method};
					
					return actionAndMethod;
				}
				
			}
			
			
			
			
		}

		return null;
		
	}

}
