package org.winter.fromwork;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.winter.fromwork.aop.AbsInterceptorListener;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import org.xml.sax.helpers.DefaultHandler;

public class Winter {

	public void initContainer(String xmlFile) throws ParserConfigurationException, SAXException, IOException {
		InputStream input = this.getClass().getClassLoader().getResourceAsStream(xmlFile);
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		SaxHandler handler = new SaxHandler();
		parser.parse(input, handler);
		HashMap<String, Object> map = handler.getMap();
		setInterceptor(map);
		
		AbsInterceptorListener soldier = getSoldierInterceptor(map);

		ApplicationBeanFactory.setBeans(map, soldier);

	}

	

	private AbsInterceptorListener getSoldierInterceptor(HashMap<String, Object> map) {

		HashMap<String, Object> interceptors = (HashMap<String, Object>) map.get("interceptors");
		return (AbsInterceptorListener) interceptors.get("soldier");

	}

	private void setInterceptor(HashMap<String, Object> map) {

		HashMap<String, Object> interceptors = new HashMap<>();
		Iterator<Entry<String, Object>> it = map.entrySet().iterator();
		AbsInterceptorListener interceptorBoss = null;
		while (it.hasNext()) {
			Entry<String, Object> entry = it.next();
			Class<?> superclass = entry.getValue().getClass().getSuperclass();

			if ("org.winter.fromwork.aop.AbsInterceptorListener".equals(superclass.getName())) {
				AbsInterceptorListener interceptor = (AbsInterceptorListener) entry.getValue();

				if (interceptorBoss != null) {

					interceptor.setInterceptorBoss(interceptorBoss);
				}

				interceptorBoss = interceptor;

				//
				interceptors.put(entry.getKey(), interceptor);
				it.remove();

			}

		}

		AbsInterceptorListener soldier = interceptorBoss;
		interceptors.put("soldier", soldier);

		map.put("interceptors", interceptors);
	}

}

class SaxHandler extends DefaultHandler {

	private HashMap<String, Object> map = null;

	public HashMap<String, Object> getMap() {
		return map;
	}

	private HashMap<String, Object[]> mapLastSet = null;

	private Object newInstance = null;
	private Class clazz = null;
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

				e.printStackTrace();
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
				System.out.println("!exists");
				return;
			}
			handlerFile(packageName, folder, map);

		}else if("mybatis".equals(qName)){
			
			HashMap<String, Object> mybatisConfig =new HashMap<>();		
			mybatisConfig.put("factoryId", attr.getValue("factoryId"));
			mybatisConfig.put("path",  attr.getValue("path"));			
			mybatisConfig.put("factoryBuilderClass",  attr.getValue("factoryBuilderClass"));			
			map.put("mybatis", mybatisConfig);
		}

	}

	private void handlerFile(String packageName, File folder, HashMap<String, Object> map) {

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

						Object newObject = null;
						try {
							newObject = clzzForScan.newInstance();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						map.put(id, newObject);
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
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
			Object newInstance = null;
			Class clazz = null;
			currentId = "";
		}
	}

	@Override
	public void endDocument() throws SAXException {

		handlerSecond(map, mapLastSet);
	}

	public void handlerSecond(Map container, Map mapLastSet) {

		Iterator<Entry<String, Object[]>> iterator = mapLastSet.entrySet().iterator();
		while (iterator.hasNext()) {

			Entry<String, Object[]> next = iterator.next();
			String ref = next.getKey();
			Object[] methedObj = next.getValue();
			Object refInstance = container.get(ref);
			if (refInstance != null) {
				try {
					Field field = methedObj[1].getClass().getDeclaredField(methedObj[0].toString());
					field.setAccessible(true);
					field.set(methedObj[1], refInstance);
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
			}

			iterator.remove();
		}

		mapLastSet = null;

	}

}