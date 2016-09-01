package org.iwinter.framework.context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BeanContext implements BaseContext, ConfigBeanContext {

	protected  List<String> classpathCongfigLocation = null;
	protected  List<String> fileCongfigLocation = null;
	private    Map<String, Object> beans = null;

	public BeanContext() {

		beans = new HashMap<>();
		classpathCongfigLocation = new ArrayList<>();
		fileCongfigLocation = new ArrayList<>();
	}

	protected  void setBeans(Map<String, Object> beans) {
		this.beans = beans;

	}

	@Override
	public Object getBean(String id) {

		return beans.get(id);
	}

	@Override
	public Map<String, Object> getBeans() {
		// TODO Auto-generated method stub
		return beans;
	}

	@Override
	public void setClassPathXMlLocation(String path) {

		classpathCongfigLocation.add(path);
	}

	@Override
	public void setClassPathXmlLocation(String[] paths) {

		for (String path : paths) {

			classpathCongfigLocation.add(path);

		}

	}

	@Override
	public void setFileXMlLocation(String path) {

		fileCongfigLocation.add(path);
	}

	@Override
	public void setFileXmlLocation(String[] paths) {
		for (String path : paths) {
			fileCongfigLocation.add(path);
		}

	}

}
