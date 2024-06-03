package com.ispanwei.project1crud;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PwUtil {

	public static Connection getConnection() throws SQLException {
		DataSource dataSource = null;
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/db38");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return dataSource.getConnection();
	}

	public static void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}