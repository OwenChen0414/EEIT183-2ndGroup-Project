package com.ispan.dao.games;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.ispan.bean.games.GamePhotoLib;
import com.ispan.bean.games.GamePhotos;

public class GamePhotosDAO {
	
	private final String GET_PHOTO_ID = "SELECT photoId FROM gamePhotoLib WHERE photoPath = ?";
	private final String INSERT_LIB = "INSERT INTO gamePhotoLib(photoPath) VALUES (?)";
	private final String INSERT = "INSERT INTO gamePhotos VALUES (?,?)";
	private final String GET_PHOTOS = "SELECT photoId FROM gamePhotos WHERE gameId = ?";
	private final String DELETE_PHOTOS = "DELETE FROM gamePhotos WHERE gameId = ?";
	private final String DELETE_PHOTO_PATH = "DELETE FROM gamePhotoLib WHERE photoPath = ?";
	
	public int deletePhotoLib(String photoPath) {
		int count = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/db16");
			connection =  dataSource.getConnection();
			preparedStatement = connection.prepareStatement(DELETE_PHOTO_PATH);
			preparedStatement.setString(1,photoPath);
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
	
	public int deletePhotos(int gameId) {
		int count = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/db16");
			connection =  dataSource.getConnection();
			preparedStatement = connection.prepareStatement(DELETE_PHOTOS);
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
	
	public int getOneId(String name) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int id = 0;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/db16");
			connection =  dataSource.getConnection();
			preparedStatement = connection.prepareStatement(GET_PHOTO_ID);
			preparedStatement.setString(1, name);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				id = resultSet.getInt(1);
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
		return id;
	}
	
	public int insertLib(GamePhotoLib photoLib) {
		int count = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/db16");
			connection =  dataSource.getConnection();
			preparedStatement = connection.prepareStatement(INSERT_LIB);
			preparedStatement.setString(1,photoLib.getPhotoPath());
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
	
	public int insert(GamePhotos photos) {
		int count = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/db16");
			connection =  dataSource.getConnection();
			preparedStatement = connection.prepareStatement(INSERT);
			preparedStatement.setInt(1,photos.getPhotoId());
			preparedStatement.setInt(2,photos.getGameId());
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
	
	public int update(GamePhotos photos,GamePhotos newPhotos) {
		int count = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/db16");
			connection =  dataSource.getConnection();
			preparedStatement = connection.prepareStatement(INSERT);
			preparedStatement.setInt(1,photos.getGameId());
			preparedStatement.setInt(1,photos.getPhotoId());
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
