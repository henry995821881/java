package org.winter.framework.parser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.log4j.Logger;
import org.winter.framework.annotation.Action;
import org.winter.framework.annotation.Repository;
import org.winter.framework.annotation.Service;
import org.winter.framework.exception.BeanNameNotFoundException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XmlClassPathConfigReader {
	public static final Logger logger =Logger.getLogger(XmlClassPathConfigReader.class);

	private HashMap<String, Object> beans = null;

	private HashMap<String, Object[]> refBeans = null;
	
	private List<Class<?>> interfaces = null;

	public void read(String xmlFile) throws ParserConfigurationException, SAXException, IOException {
		
		logger.debug("readxml:"+xmlFile);
		InputStream input = this.getClass().getClassLoader().getResourceAsStream(xmlFile);
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		SaxHandler handler = new SaxHandler();
		parser.parse(input, handler);
		beans = handler.getMap();
		refBeans = handler.getRefMap();
		
		 interfaces = handler.getInterfaces();
		
	}

	
	
	public List<Class<?>> getInterfaces() {
		return interfaces;
	}



	public HashMap<String, Object> getBeans() {
		return beans;
	}

	public HashMap<String, Object[]> getRefBeans() {
		return refBeans;
	}

}

class SaxHandler extends DefaultHandler {
	
	
	

	/**
	 * dao
	 * @return
	 */
	public List<Class<?>> getInterfaces() {
		return interfaces;
	}

	/**
	 * dao
	 */
	private List<Class<?>> interfaces = new ArrayList<>();
	private HashMap<String, Object> map = null;

	public HashMap<String, Object> getMap() {
		return map;
	}

	public HashMap<String, Object[]> getRefMap() {

		return mapLastSet;
	}

	private HashMap<String, Object[]> mapLastSet = null;

	private Object newInstance = null;
	private Class<?> clazz = null;
	private String currentId = "";

	@Override
	public void startDocument() throws SAXException {
		map = new HashMap<>();
		mapLastSet = new HashMap<>();

	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attr) throws SAXException {

		if ("bean".equals(qName)) {

			currentId = attr.getValue("id");

			try {
				clazz = Class.forName(attr.getValue("class"));

				newInstance = clazz.newInstance();

			} catch (ClassNotFoundException e) {

				try {
					throw new BeanNameNotFoundException(attr.getValue("class"));
				} catch (BeanNameNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if ("property".equals(qName)) {

			String propertyName = attr.getValue("name");
			String propertyValue = attr.getValue("value");
			String ref = attr.getValue("ref");
			Object propertyValue1 = null;
			if (ref == null) {
				try {
					Field field = clazz.getDeclaredField(propertyName);
					field.setAccessible(true);
					String type = field.getType().getName();

					if ("int".equals(type)) {
						propertyValue1 = Integer.valueOf(propertyValue).intValue();
					} else if ("java.lang.String".equals(type)) {
						propertyValue1 = propertyValue;
					} else if ("boolean".equals(type)) {
						propertyValue1 = Boolean.valueOf(propertyValue);
					} else if ("float".equals(type)) {
						propertyValue1 = Float.valueOf(propertyValue);
					} else if ("long".equals(type)) {
						propertyValue1 = Long.valueOf(propertyValue);
					} else if ("double".equals(type)) {
						propertyValue1 = Double.valueOf(propertyValue);
					} else if ("short".equals(type)) {
						propertyValue1 = Short.valueOf(propertyValue);
					}

					field.set(newInstance, propertyValue1);
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

			} else {

				mapLastSet.put(ref, new Object[] { propertyName, newInstance });
			}
		} else if ("scan".equals(qName)) {
			String packageName = attr.getValue("package");

			// webrootpath
			// String rootPath = "D:\\new-workSpace\\text-sax\\src";
			String rootPath = this.getClass().getClassLoader().getResource("/").getPath();

			String secondPath = packageName.replace(".", File.separator);
			String path = rootPath + File.separator + secondPath;

			File folder = new File(path);

			if (!folder.exists()) {
				XmlClassPathConfigReader.logger.info("package !exists");
			
				return;
			}
			file2ClassName(packageName, folder, map);

		}/* else if ("mybatis".equals(qName)) {

			HashMap<String, Object> mybatisConfig = new HashMap<>();
			mybatisConfig.put("factoryId", attr.getValue("factoryId"));
			mybatisConfig.put("path", attr.getValue("path"));
			mybatisConfig.put("factoryBuilderClass", attr.getValue("factoryBuilderClass"));
			map.put("mybatis", mybatisConfig);
		}*/

	}

	private void file2ClassName(String packageName, File folder, HashMap<String, Object> map) {

		List<File> list = new ArrayList<>();
		AllFileToList(folder, list);
		
		

		for (File file : list) {

			String qPackageName = file.getAbsolutePath().replace(File.separator, ".");

			if (qPackageName.endsWith(".class")) {
				// .class
				qPackageName = qPackageName.substring(0, qPackageName.lastIndexOf(".class"));
				String packageClassName = qPackageName.substring(qPackageName.indexOf(packageName));
				String className = packageClassName.substring(packageClassName.lastIndexOf(".") + 1);

				String id = className.substring(0, 1).toLowerCase() + className.substring(1);

				try {

					Class<?> clzzForScan = Class.forName(packageClassName);
					if (!clzzForScan.isInterface() && !clzzForScan.isEnum()) {

						newComponent(map, clzzForScan, id);

					}else if(clzzForScan.isInterface()){
						
						interfaces.add(clzzForScan);
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private void newComponent(HashMap<String, Object> map, Class<?> clazz, String id) {

		Action action = clazz.getAnnotation(Action.class);
		Service service = clazz.getAnnotation(Service.class);
		Repository repository = clazz.getAnnotation(Repository.class);
		if (action != null) {

			put2Map(map, id, clazz, action.value());

		} else if (service != null) {

			put2Map(map, id, clazz, service.value());

		} else if (repository != null) {

			put2Map(map, id, clazz, repository.value());

		}

	}

	private void put2Map(HashMap<String, Object> map, String id, Class<?> clazz, String beanId) {

		Object instance = null;
		try {
			instance = clazz.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (!"".equals(beanId)) {

			id = beanId;
		}

		if (instance != null) {
			map.put(id, instance);
		}

	}

	public void AllFileToList(File folder, List<File> list) {

		if (folder.isFile()) {
			list.add(folder);
		} else {
			File[] listFiles = folder.listFiles();
			for (File file : listFiles) {

				AllFileToList(file, list);
			}
		}

	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {

		if ("bean".equals(qName)) {

			map.put(currentId, newInstance);
			newInstance = null;
			clazz = null;
			currentId = "";
		}
	}

	@Override
	public void endDocument() throws SAXException {
		XmlClassPathConfigReader.logger.info("endDocument");
		

	}

}
