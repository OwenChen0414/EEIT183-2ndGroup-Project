package com.ispan.dao.buy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.ispan.bean.buy.Discount;
import com.ispan.bean.buy.GameCart;
import com.ispan.bean.buy.GameWithCart;
import com.ispan.bean.games.Game;

public class BuyGameDAO {
	
	private final String INSERT_CART = "INSERT INTO gameCart VALUES (?,?,FORMAT(DATEADD(hh,8,SYSDATETIME()), 'yyyy-MM-dd HH:mm:ss'))";
	private final String GET_CART_GAMES = "SELECT * FROM getAllOnGamesInfo gi JOIN gameCart gc ON gi.gameId = gc.gameId WHERE gc.memId = ?";
	
	public List<GameWithCart> getCart(int memId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<GameWithCart> carts = new ArrayList<GameWithCart>();
		int id = 0;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
			connection =  dataSource.getConnection();
			preparedStatement = connection.prepareStatement(GET_CART_GAMES);
			preparedStatement.setInt(1, memId);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				GameWithCart cart = new GameWithCart();
				cart.setGameId(resultSet.getInt("gameId"));
				cart.setGameName(resultSet.getString("gameName"));
				cart.setPrice(resultSet.getInt("price"));
				cart.setDiscountRate(resultSet.getDouble("discountRate"));
				cart.setPhotoPath(resultSet.getString("photoPath"));
				cart.setMemId(resultSet.getInt("memId"));
				carts.add(cart);
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
		return carts;
	}
	
	public int insertCart(GameCart gameCart) {
		int count = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
			connection =  dataSource.getConnection();
			preparedStatement = connection.prepareStatement(INSERT_CART);
			preparedStatement.setInt(1,gameCart.getMemId());
			preparedStatement.setInt(2, gameCart.getGameId());
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
