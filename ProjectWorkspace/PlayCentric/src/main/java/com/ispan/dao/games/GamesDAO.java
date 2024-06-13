package com.ispan.dao.games;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.ispan.bean.announcement.AnnouncementCategory;
import com.ispan.bean.games.GamePhotos;
import com.ispan.bean.games.Game;
import com.ispan.bean.games.GameCategoryLib;
import com.ispan.bean.games.GameCategorys;

public class GamesDAO {
	
	private Session session;
	
	public GamesDAO(Session session) {
		this.session = session;
	}

	public GamesDAO() {
	}

	public int insert(Game game,List<GameCategoryLib> category) {
		int count = 0;
		game.setGameCategoryLib(category);
		session.persist(game);
		session.flush();
		count++;
		return count;
	}
	
	public List<Game> getAll() {
		Query query = session.createQuery("from game",Game.class);
		return query.list();
	}
	
	public List<Game> getAllOn() {
		Query<Game> query = session.createQuery
				("from game WHERE releaseAt IS NOT NULL",Game.class);
		return query.list();
	}
	
	public Game getOne(int id) {
		Game game = session.get(Game.class, id);
		return game;
	}
	
	public Game getWithName(String name) {
		Game game = new Game();
		return game;
	}
	
	public List<GameCategoryLib> getCategorys(int gameId) {
		Query<GameCategoryLib> query = session.createQuery("from gameCategoryLib",GameCategoryLib.class);
		return query.list();
	}
	
	
	public int updateOnMarket(int id) {
		int count = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/db16");
			connection =  dataSource.getConnection();
			preparedStatement = connection.prepareStatement(UPDATE_ONMARKET);
			preparedStatement.setInt(1,id);
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
	
	public int updateRemove(int id) {
		int count = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/db16");
			connection =  dataSource.getConnection();
			preparedStatement = connection.prepareStatement(UPDATE_REMOVE_GAME);
			preparedStatement.setInt(1,id);
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
	
	public int updateInfo(int id,Game game) {
		int count = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/db16");
			connection =  dataSource.getConnection();
			preparedStatement = connection.prepareStatement(UPDATE_INFO);
			preparedStatement.setString(1,game.getGameName());
			preparedStatement.setInt(2, game.getPrice());
			preparedStatement.setString(3, game.getDescription());
			preparedStatement.setString(4, game.getDeveloper());
			preparedStatement.setString(5, game.getPublisher());
			preparedStatement.setString(6, game.getIntroduction());
			preparedStatement.setInt(7,id);
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
	
	public int delete(int id) {
		int count = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/db16");
			connection =  dataSource.getConnection();
			preparedStatement = connection.prepareStatement(DELETE);
			preparedStatement.setInt(1,id);
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
	
	public int deleteCategory(int id) {
		int count = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/db16");
			connection =  dataSource.getConnection();
			preparedStatement = connection.prepareStatement(DELETE_CATEGORY);
			preparedStatement.setInt(1,id);
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
