package org.winter.framework.dbsource.util;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.winter.framework.dbsource.MyDataSource;

public class Dbutil {

	private MyDataSource dataSource;

	
	
	public MyDataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(MyDataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * 
	 * ������ݿ�
	 * 
	 * @param sql
	 *            ��� ���� update user set name =? where id<?
	 * @param params�ɱ����
	 * @return Ӱ�������
	 * @throws SQLException
	 */
	public int update(String sql, Object... params) throws SQLException {

		Connection conn = dataSource.getConnection();

		PreparedStatement ps = conn.prepareStatement(sql);
		// ��ȡ����Ԫ���
		ParameterMetaData metaData = ps.getParameterMetaData();
		// ��ȡ�������
		int count = metaData.getParameterCount();

		for (int i = 1; i <= count; i++) {

			ps.setObject(i, params[i - 1]);
		}
		// ִ�� ���²���
		int num = ps.executeUpdate();

		close(conn, ps, null);

		return num;
	}

	/**
	 * �õ���Statement ���� �����ǲ�ͬ��䣬���磺select��update ��delete��insert
	 * 
	 * @param conn���Զ��ر�
	 * @param isAutoCommit
	 *            �Ƿ�
	 * @param FillSQL
	 *            Ҫ�������sql����String���飬���ܴ��У�
	 * @return int[]ÿ��Ӱ�������
	 * @throws SQLException
	 */
	public int[] BatchUpdata(Connection conn, boolean isAutoCommit, String... FillSQL) throws SQLException {

		conn.setAutoCommit(isAutoCommit);

		Statement st = conn.createStatement();

		for (int i = 0; i < FillSQL.length; i++) {

			st.addBatch(FillSQL[i]);
		}

		int[] executeBatch = st.executeBatch();

		close(null, st, null);
		return executeBatch;
	}

	/**
	 * 
	 * ֻ������һ���sql���
	 * 
	 * @param conn
	 * @param isAutoCommit
	 *            ʦ���Զ��ύ
	 * @param sql
	 *            ֻ����һ��ռλ������
	 * @param params
	 *            ��Ҫ���¶�������¼��ÿ���������һ����¼
	 * @return ���µ�Ӱ�����������
	 * @throws SQLException
	 */
	public int[] BatchUpdata(Connection conn, boolean isAutoCommit, String sql, Object... params) throws SQLException {

		conn.setAutoCommit(isAutoCommit);

		PreparedStatement ps = conn.prepareStatement(sql);

		for (int i = 0; i < params.length; i++) {

			ps.setObject(1, params[i]);
			ps.addBatch();
		}

		int[] executeBatch = ps.executeBatch();

		close(null, ps, null);
		return executeBatch;
	}

	/**
	 * 
	 * 
	 * @param conn
	 *            ���Զ��ر�
	 * @param sql
	 *            ��� ���� update user set name =? where id<?
	 * @param isAutoCommit
	 *            �Ƿ��Զ��ύ
	 * @param params
	 *            ���õļ���
	 * @return Ӱ�������
	 * @throws SQLException
	 */
	public int update(Connection conn, String sql, boolean isAutoCommit, Object... params) throws SQLException {

		conn.setAutoCommit(isAutoCommit);

		PreparedStatement ps = conn.prepareStatement(sql);
		// ��ȡ����Ԫ���
		ParameterMetaData metaData = ps.getParameterMetaData();
		// ��ȡ�������
		int count = metaData.getParameterCount();

		for (int i = 1; i <= count; i++) {

			ps.setObject(i, params[i - 1]);
		}
		// ִ�� ���²���
		int num = ps.executeUpdate();

		close(null, ps, null);

		return num;
	}

	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * ���Բ�ѯ�������ܶ�����¼
	 * 
	 * @param sql
	 *            ��� ���� select * from user where id<?
	 * @param rh
	 *            ������ص��ӿ�
	 * @param params
	 *            �ɱ����
	 * @return
	 * @throws SQLException
	 */
	public <T> T query(String sql, ResultHandler<T> rh, Object... params) throws SQLException {

		Connection conn = dataSource.getConnection();

		PreparedStatement ps = conn.prepareStatement(sql);
		// ��ȡ����Ԫ���
		ParameterMetaData metaData = ps.getParameterMetaData();
		// ��ȡ�������
		int count = metaData.getParameterCount();

		for (int i = 1; i <= count; i++) {

			ps.setObject(i, params[i - 1]);
		}
		// ִ�� ���²�����ý��

		ResultSet rs = ps.executeQuery();
		// �ص�������
		T t = rh.handler(rs);

		close(conn, ps, rs);

		// ���ؽ��
		return t;
	}

	/**
	 * 
	 * @param conn���Զ��ر�
	 * @param T
	 *            ����
	 * @param sql������
	 *            ���� select * from user where id<?
	 * @param isAutoCommit
	 *            �Ƿ��Զ��ύ
	 * @param rh
	 *            �ص��ӿڴ���ResultSet
	 * @param params
	 * @return���� ResultHandler����Ľ�� @throws SQLException
	 */
	public <T> T query(Connection conn, String sql, boolean isAutoCommit, ResultHandler<T> rh, Object... params)
			throws SQLException {

		conn.setAutoCommit(isAutoCommit);

		PreparedStatement ps = conn.prepareStatement(sql);
		// ��ȡ����Ԫ���
		ParameterMetaData metaData = ps.getParameterMetaData();
		// ��ȡ�������
		int count = metaData.getParameterCount();

		for (int i = 1; i <= count; i++) {

			ps.setObject(i, params[i - 1]);
		}
		// ִ�� ���²�����ý��

		ResultSet rs = ps.executeQuery();
		// �ص�������
		T t = rh.handler(rs);

		close(null, ps, rs);

		// ���ؽ��
		return t;
	}

	/**
	 * 
	 * 
	 * �ر���ݿ����ӵ���Դ
	 * 
	 * @param conn
	 * @param ps
	 * @param rs
	 */
	public void close(Connection conn, Statement ps, ResultSet rs) {

		if (rs != null) {
			try {
				rs.close();
				rs = null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		if (ps != null) {
			try {
				ps.close();
				ps = null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		dataSource.returnConn(conn);

	}

}
