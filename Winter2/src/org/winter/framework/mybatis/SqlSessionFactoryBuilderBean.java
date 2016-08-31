package org.winter.framework.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionFactoryBuilderBean extends SqlSessionFactoryBuilder {

	private String configLocation = "";

	public SqlSessionFactory build() {
		InputStream is = SqlSessionFactoryBuilderBean.class.getClassLoader().getResourceAsStream(configLocation);

		SqlSessionFactory factory = super.build(is);

		try {
			is.close();
		} catch (IOException e) {
		
			e.printStackTrace();
		} finally {
			is = null;
		}

		return factory;

	}

	public String getConfigLocation() {
		return configLocation;
	}

	public void setConfigLocation(String configLocation) {
		this.configLocation = configLocation;
	}

}
