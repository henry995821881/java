package org.iwinter.framework.reader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.iwinter.framework.type.ObjFieldId;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public abstract class AbsXmlReader implements XmlReader {

	@Override
	public abstract Map<String, Object> read(List<String> paths);

	/**
	 * ref 的id obj field
	 */
	protected List<ObjFieldId> objFieldIds = null;

	protected Map<String, Object> handleXML(List<File> files)
			throws ParserConfigurationException, SAXException, IOException {

		objFieldIds = new ArrayList<>();

		Map<String, Object> map = new HashMap<>();

		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();

		for (File file : files) {

			parser.parse(file, new SaxHandler(map));
		}

		handleRefBean(objFieldIds, map);

		return map;
	}

	protected void handleRefBean(List<ObjFieldId> objFieldIds, Map<String, Object> map) {

		Field field = null;
		String id = null;
		Object obj = null;
		for (ObjFieldId objFieldId : objFieldIds) {

			field = objFieldId.getField();
			id = objFieldId.getId();
			obj = objFieldId.getObj();

			try {
				field.setAccessible(true);
				field.set(obj, map.get(id));
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		objFieldIds = null;
	}

	/**
	 * 
	 * 
	 * @author admin
	 *
	 */
	class SaxHandler extends DefaultHandler {
		Map<String, Object> map = null;

		public SaxHandler(Map<String, Object> map) {
			this.map = map;
		}

		/**************************************************/

		private Object newInstance = null;
		private String currentId = null;
		private Class<?> clazz = null;

		@Override
		public void startElement(String uri, String localName, String qName, Attributes attr) throws SAXException {

			if ("bean".equals(qName)) {

				currentId = attr.getValue("id");

				try {
					clazz = Class.forName(attr.getValue("class"));

					newInstance = clazz.newInstance();

				} catch (ClassNotFoundException e) {

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
				if (ref == null && clazz != null && newInstance != null) {
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

				} else if (ref != null && clazz != null && newInstance != null) {

					try {
						ObjFieldId objFieldId = new ObjFieldId();
						objFieldId.setField(clazz.getDeclaredField(propertyName));
						objFieldId.setId(ref);
						objFieldId.setObj(newInstance);
						objFieldIds.add(objFieldId);
					} catch (NoSuchFieldException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			} else if ("scan".equals(qName)) {

				String packageName = attr.getValue("package");
				List<String> classNames = scanPackage2ClassName(packageName);

				String id = null;
				for (String className : classNames) {

					try {
						id = className.substring(className.lastIndexOf(".")+1);
						id = id.substring(0, 1).toLowerCase() + id.substring(1);
						Class<?> c = Class.forName(className);

						if (!c.isInterface() && !c.isEnum() && !c.isAnnotation()) {

							Object o = c.newInstance();

							map.put(id, o);
						}

					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}

		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {

			if ("bean".equals(qName)) {

				map.put(currentId, newInstance);

			}

			// clear
			if (!"property".equals(qName)) {

				newInstance = null;
				currentId = null;
				clazz = null;

			}

		}

		@Override
		public void startDocument() throws SAXException {

			System.out.println("SaxHandler.startDocument()");
		}

		@Override
		public void endDocument() throws SAXException {

			System.out.println("SaxHandler.endDocument()");

		}

	}

	public List<String> scanPackage2ClassName(String packageName) {

		String rootPath = SaxHandler.class.getClassLoader().getResource("").getPath();

		List<String> classNames = new ArrayList<>();
		String secondPath = packageName.replace(".", File.separator);
		String path = rootPath + File.separator + secondPath;

		File folder = new File(path);

		if (!folder.exists()) {
			logger.debug("package !exists");

			return null;
		}

		List<File> files = new ArrayList<>();

		AllFileToList(folder, files);

		for (File file : files) {

			String qPackageName = file.getAbsolutePath().replace(File.separator, ".");

			if (qPackageName.endsWith(".class")) {
				// .class
				qPackageName = qPackageName.substring(0, qPackageName.lastIndexOf(".class"));
				String className = qPackageName.substring(qPackageName.indexOf(packageName));

				classNames.add(className);
			}
		}

		return classNames;
	}

	// 递归
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

}
