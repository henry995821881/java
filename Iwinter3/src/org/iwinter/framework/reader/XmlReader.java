package org.iwinter.framework.reader;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public interface XmlReader {
	public static final Logger logger = Logger.getLogger(AbsXmlReader.class);
	
	public Map<String,Object> read(List<String> paths);
}
