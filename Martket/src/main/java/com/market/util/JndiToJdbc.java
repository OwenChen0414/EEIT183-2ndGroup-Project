package com.market.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class JndiToJdbc {
	


	    // 獲取數據庫連接的方法
	    public  Connection getConnection(String dbName) throws NamingException, SQLException {
	        Context context = new InitialContext();
	        DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/"+dbName);
	        return ds.getConnection();
	    }

	    // 關閉數據庫連接的方法
	    public  void closeConnection(Connection conn) {
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}
	
	


