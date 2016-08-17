package org.winter.fromwork.dbsource.util;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultHandler<T> {
	
	
	
	T handler(ResultSet rs) throws SQLException;
	

}
