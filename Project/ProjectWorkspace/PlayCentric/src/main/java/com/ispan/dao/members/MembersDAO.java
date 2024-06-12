package com.ispan.dao.members;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.ispan.bean.members.Members;

public class MembersDAO {

	private final String GET_ALL = "SELECT * FROM memview";
	private final String GET_BY_ACPW = "SELECT * FROM memview WHERE account = ? AND passwords = ?";
	private final String GET_NAME = "SELECT mem_name FROM memview WHERE mem_id = ?";
	
	public String getName(int memId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String name = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
			connection =  dataSource.getConnection();
			preparedStatement = connection.prepareStatement(GET_NAME);
			preparedStatement.setInt(1,memId);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				name = resultSet.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
				try {
					if (connection != null) connection.close();
					if (preparedStatement != null) preparedStatement.close();
					if (resultSet != null) resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return name;
	}
	
	public Members loginCheck(String ac,String pw) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Members member = new Members();
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
			connection =  dataSource.getConnection();
			preparedStatement = connection.prepareStatement(GET_BY_ACPW);
			preparedStatement.setString(1,ac);
			preparedStatement.setString(2,pw);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				member.setAccount(resultSet.getString("account"));
				member.setMemId(resultSet.getInt("mem_id"));
				member.setMemName(resultSet.getString("mem_name"));
				member.setPassword(resultSet.getString("passwords"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
				try {
					if (connection != null) connection.close();
					if (preparedStatement != null) preparedStatement.close();
					if (resultSet != null) resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return member;
	}
	
}
