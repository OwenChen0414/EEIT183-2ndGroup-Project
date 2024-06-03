package com.market.dao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

import com.market.bean.MarketOrderBean;
import com.market.bean.PropBean;
import com.market.util.JndiToJdbc;
@WebServlet("/GetSelectedMarketOrders")

public class GetSelectedMarketOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession();
	    String selectedGameId = (String) session.getAttribute("selectedGameIdForMarket");
//	    response.getWriter().write(selectedGameId);
		
      
        JndiToJdbc jndiToJdbc = new JndiToJdbc();
        try {
			Connection conn = jndiToJdbc.getConnection("db37");
			String sql = "SELECT allProps.gameId,"
					+ " marketOrders.orderId,"
					+ " allProps.propName,"
					+ " marketOrders.uniqueId,"
					+ " allProps.propType,"
					+ " allProps.propImagePath,"
					+ " marketOrders.price,"
					+ " marketOrders.sellerId,"
					+ " marketOrders.expirationTime,"
					+ " marketOrders.orderStatus"
					+ " FROM allProps RIGHT JOIN marketOrders "
					+ "ON allProps.propId = marketOrders.propId WHERE allProps.gameId =?";
			PreparedStatement stmt =conn.prepareStatement(sql);
			stmt.setString(1, selectedGameId);
			ResultSet rs =stmt.executeQuery();
			
			List<MarketOrderBean> SelectedMarketOrders = new ArrayList<>();
			List<PropBean> SelectedProps = new ArrayList<>();
			MarketOrderBean marketOrder  =null;
			PropBean prop =null;
			while (rs.next()) {
				marketOrder = new MarketOrderBean();
				prop = new PropBean();
				marketOrder.setGameId(rs.getInt(1));
				marketOrder.setOrderId(rs.getInt(2));    
				prop.setPropName(rs.getString(3));
				marketOrder.setUniqueId(rs.getInt(4));    
				prop.setPropType(rs.getString(5));
				prop.setPropImagePath(rs.getString(6));
				marketOrder.setPrice(rs.getInt(7));
				marketOrder.setSellerId(rs.getInt(8));
				marketOrder.setExpirationTime(rs.getTimestamp(9));
				marketOrder.setOrderStatus(rs.getString(10));
				SelectedMarketOrders.add(marketOrder);
				SelectedProps.add(prop);
			}		
			HttpSession SessionOfMarket = request.getSession();
			session.setAttribute("SeletedGameMrketOrders", SelectedMarketOrders);
			session.setAttribute("SelectedProps", SelectedProps);
			stmt.close();
			conn.close();
			request.getRequestDispatcher("/jsp/GetSelectedMarketOrders.jsp").forward(request, response);
        
        } catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
