package com.henry.test.log4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class TestLog {



	
	public static void main(String[] args) {
		
		Properties prop = new Properties();
		InputStream is = TestLog.class.getClassLoader().getResourceAsStream("log4j.properties");
		try {
			prop.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PropertyConfigurator.configure(prop);
	     Logger logger = Logger.getLogger(TestLog.class);

		  logger.warn("sjdf");
		  logger.info("info");
		  logger.debug("debug");
		
	}

}
