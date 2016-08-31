package org.winter.framework.test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Properties;

public class MyDataSource_cp {

	private String url;
	private String driver;
	private String user;
	private String password;

	private int maxConn = 10;
	private int minConn = 3;

	private LinkedList<Connection> connList = new LinkedList<>();

	

	public MyDataSource_cp(String propertiesPath) {

		String path = "db.properties";
		if (propertiesPath != null) {

			path = propertiesPath;
		}

		InputStream is = MyDataSource_cp.class.getClassLoader().getResourceAsStream(path);

		Properties pro = new Properties();
		String max = null;
		String min = null;
		try {
			pro.load(is);

			url = pro.getProperty("url");
			driver = pro.getProperty("driver");
			user = pro.getProperty("user");
			password = pro.getProperty("password");
			max = pro.getProperty("maxConn");
			min = pro.getProperty("minConn");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (max != null && !"".equals(max)) {
			maxConn = Integer.valueOf(max.trim());
		}
		if (min != null && !"".equals(min)) {
			minConn = Integer.valueOf(min.trim());
		}

		for (int i = 0; i < 3; i++) {

			addConn();

		}

	}

	public synchronized Connection getConnection() {

		if (connList.size() < minConn) {

			for (int i = 0; i < 3; i++) {

				addConn();

			}

		}

		Connection conn = connList.remove(0);

		Connection proxy = (Connection) new ProxyFactory().getProxyInstance(conn);


		return proxy;
	}

	class ProxyFactory implements InvocationHandler {

		Object target;

		public Object getProxyInstance(Object target1) {

			target = target1;
			return Proxy.newProxyInstance(target1.getClass().getClassLoader(),
					target.getClass().getInterfaces(), ProxyFactory.this);

		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			// TODO Auto-generated method stub

			if ("close".equals(method.getName())) {

				 returnConn((Connection) target);

				return null;

			} else {

				return method.invoke(target, args);
			}

		}

	}

	private synchronized void returnConn(Connection conn) {

		try {
			if (conn != null && !conn.isClosed()) {

				if (connList.size() < maxConn) {

					connList.add(conn);
				} else {

					conn = null;
					
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ������ӵ�����
	private void addConn() {

		Connection conn = null;
		try {

			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		connList.add(conn);

	}

	public void setMaxConn(int maxConn) {
		this.maxConn = maxConn;
	}

	public void setMinConn(int minConn) {
		this.minConn = minConn;
	}

}
