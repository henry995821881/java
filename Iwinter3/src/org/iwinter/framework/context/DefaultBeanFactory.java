package org.iwinter.framework.context;

import java.util.List;
import java.util.Map;

import org.iwinter.framework.reader.ClassPathXmlReader;
import org.iwinter.framework.reader.FileSystemXmlReader;
import org.iwinter.framework.reader.XmlReader;

public class DefaultBeanFactory extends BeanContext {

	/**
	 * init container
	 */
	public void init() {

		XmlReader reader = null;
		List<String> paths = null;

		if (classpathCongfigLocation.size() > 0) {

			reader = new ClassPathXmlReader();
			paths = classpathCongfigLocation;

		} else if (fileCongfigLocation.size() > 0) {

			reader = new FileSystemXmlReader();
			paths = fileCongfigLocation;

		}

		if (reader != null) {

			Map<String, Object> beans = reader.read(paths);
			this.setBeans(beans);
		}

	}

}
