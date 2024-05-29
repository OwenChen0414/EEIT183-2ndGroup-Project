package com.ispan.util;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CreateConnection implements Closeable {
	private Connection connection = null;
	
	
	//建構方法
	public CreateConnection() {
		try {
			InitialContext initialContext = new InitialContext();
			DataSource ds = (DataSource)initialContext.lookup("java:/comp/env/jdbc/servdb");
			do {
				this.connection = ds.getConnection();
			} while (this.connection == null);
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		return connection;
	}
	
	//自動關閉連線
	@Override
	public void close() {
		closeConnection(connection);
	}
	
	//關閉連線方法
	public static void closeConnection(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
