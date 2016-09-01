package org.iwinter.framework.context;

import java.util.Map;



public interface BaseContext {

	
	Object getBean(String id);
	
	Map<String, Object> getBeans();
	
	
}
