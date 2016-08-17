package org.winter.fromwork;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.xml.parsers.ParserConfigurationException;

import org.winter.fromwork.dbsource.MyDataSource;
import org.winter.fromwork.test.MyDataSource_cp;
import org.xml.sax.SAXException;

public class WinterContextListner implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

		ApplicationBeanFactory.clearBeans();

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {

		ServletContext servletContext = arg0.getServletContext();
		String configePath = servletContext.getInitParameter("contextConfigLocation");
		
		
		try {
			new Winter().initContainer(configePath);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
