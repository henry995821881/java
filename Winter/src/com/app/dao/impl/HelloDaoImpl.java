package com.app.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.winter.fromwork.Autowired;
import org.winter.fromwork.dbsource.MyDataSource;
import org.winter.fromwork.dbsource.util.Dbutil;
import org.winter.fromwork.dbsource.util.ResultHandler;

import com.app.dao.HelloDao;

public class HelloDaoImpl  implements HelloDao{

	
 
	//@Autowired(beanId="dbutil")
	@Autowired(beanId="myDataSource")
	private MyDataSource db;
	public List<String> getKikanList()  {

		List<String> list = new ArrayList<>();

		
		Connection connection = db.getConnection();
		list.add(connection.toString());
		
		String sql="select * from ctm_code_tbl c where c.kbn =?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, "D14");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				
				String string = rs.getString("org");
				//list.add(string);
				System.out.println(string);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			
			
		}
		

		//select c.org from ctm_code_tbl c where c.kbn ='D13'
	/*	try {
			list=	dbutil.query("select * from ctm_code_tbl c where c.kbn =?", new ResultHandler<List<String>>() {

				@Override
				public List<String> handler(ResultSet rs) {
					List<String> sList = new ArrayList<>();
					try {
						while (rs.next()) {
							
							String a=  rs.getString("org");
							sList.add(a);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					return sList;
				}
			}, "D13");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		return list;
	}
}
