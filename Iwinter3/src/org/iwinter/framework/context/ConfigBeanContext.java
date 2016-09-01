package org.iwinter.framework.context;

public interface ConfigBeanContext {

	
	void setClassPathXMlLocation(String path);
	void setClassPathXmlLocation(String[] paths);

	void setFileXMlLocation(String path);
	void setFileXmlLocation(String[] paths);

}
