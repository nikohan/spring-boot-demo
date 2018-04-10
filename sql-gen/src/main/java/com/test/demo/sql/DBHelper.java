package com.test.demo.sql;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBHelper {
	private final static Log log = LogFactory.getLog(DBHelper.class);

	private static final String url = "jdbc:mysql://127.0.0.1:3306/world?useSSL=false";
	private static final String name = "com.mysql.jdbc.Driver";
	private static final String user = "root";
	private static final String password = "123456";

	private Connection conn = null;

	public DBHelper() {
		try {
			Class.forName(name);
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			log.error(e);
		}
	}

	public ResultSet query(String sql) {
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			return pst.executeQuery();
		} catch (SQLException e) {
			log.error(e);
		}
		return null;
	}

	public void close() {
		try {
			this.conn.close();
		} catch (SQLException e) {
			log.error(e);
		}
	}
}
