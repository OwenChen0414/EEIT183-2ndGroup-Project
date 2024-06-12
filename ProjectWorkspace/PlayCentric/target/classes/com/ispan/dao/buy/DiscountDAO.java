package com.ispan.dao.buy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.ispan.bean.buy.Discount;

public class DiscountDAO {

	private final String INSERT = "INSERT INTO discount(gameId,startAt,endAt,discountRate)"
			+ " VALUES (?,?,?,?)";
	private final String UPDATE = "UPDATE discount SET startAt=?,endAt=?,discountRate=? WHERE gameId=?";
	private final String GET_BY_GAME = "SELECT * FROM discount WHERE gameId=?";
	private final String DELETE = "DELETE FROM discount WHERE gameId = ?";
	
	public int delete(int gameId) {
		int count = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
			connection =  dataSource.getConnection();
			preparedStatement = connection.prepareStatement(DELETE);
			preparedStatement.setInt(1,gameId);
			count = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
				try {
					if (connection != null) connection.close();
					if (preparedStatement != null) preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return count;
	}
	
	public Discount getByGame(int gameId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Discount discount = new Discount();
		int id = 0;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
			connection =  dataSource.getConnection();
			preparedStatement = connection.prepareStatement(GET_BY_GAME);
			preparedStatement.setInt(1, gameId);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				discount.setDiscountId(resultSet.getInt(1));
				discount.setGameId(resultSet.getInt(2));
				discount.setStartAt(resultSet.getDate(3));
				discount.setEndAt(resultSet.getDate(4));
				discount.setDiscountRate(resultSet.getDouble(5));
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
		return discount;
	}
	
	public int insert(Discount discount) {
		int count = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
			connection =  dataSource.getConnection();
			preparedStatement = connection.prepareStatement(INSERT);
			preparedStatement.setInt(1,discount.getGameId());
			preparedStatement.setDate(2, discount.getStartAt());
			preparedStatement.setDate(3, discount.getEndAt());
			preparedStatement.setDouble(4, discount.getDiscountRate());
			count = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
				try {
					if (connection != null) connection.close();
					if (preparedStatement != null) preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return count;
	}
	
	public int update(Discount discount) {
		int count = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
			connection =  dataSource.getConnection();
			preparedStatement = connection.prepareStatement(UPDATE);
			preparedStatement.setInt(4,discount.getGameId());
			preparedStatement.setDate(1, discount.getStartAt());
			preparedStatement.setDate(2, discount.getEndAt());
			preparedStatement.setDouble(3, discount.getDiscountRate());
			count = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
				try {
					if (connection != null) connection.close();
					if (preparedStatement != null) preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return count;
	}
	
}
