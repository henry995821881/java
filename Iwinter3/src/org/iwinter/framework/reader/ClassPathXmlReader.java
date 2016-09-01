package org.iwinter.framework.reader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class ClassPathXmlReader extends AbsXmlReader {

	@Override
	public Map<String, Object> read(List<String> paths) {

		Map<String, Object> beans = new HashMap<>();

		List<File> files = new ArrayList<>();
		for (String path : paths) {

			logger.debug("readxml:" + path);

			URL url = ClassPathXmlReader.class.getClassLoader().getResource(path);
			File file = new File(url.getPath());

			if (file.exists()) {

				files.add(file);
			}

		}
		
		try {
			 beans = handleXML(files);
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

		return beans;
	}

}
