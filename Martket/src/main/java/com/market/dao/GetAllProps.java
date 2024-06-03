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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import com.market.bean.PropBean;
import com.market.util.JndiToJdbc;
@WebServlet("/GetAllProps")

public class GetAllProps extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		game參數為selceted時所設的session，在update時調用game會是空值，所以另外寫gamdId讓update調用
		String selectedGameIdCheck = request.getParameter("game");
		int selectedGameId = 0;
		if (selectedGameIdCheck != null) {
			selectedGameId = Integer.valueOf( request.getParameter("game"));
		}else {
			selectedGameId = Integer.valueOf( request.getParameter("gameId"));
		}
//        response.getWriter().write(selectedGameId);
      
        JndiToJdbc jndiToJdbc = new JndiToJdbc();
        try {
			Connection conn = jndiToJdbc.getConnection("db37");
			String sql = "SELECT * FROM allProps";
			PreparedStatement stmt =conn.prepareStatement(sql);
			ResultSet rs =stmt.executeQuery();
			
			List<PropBean> props= new ArrayList<>();
			PropBean prop =null;
			while (rs.next()) {
				if(selectedGameId == rs.getInt(1)) {
				prop = new PropBean();
				prop.setGameId(rs.getInt(1));
				prop.setPropId(rs.getInt(2));
				prop.setPropName(rs.getString(3));;
				prop.setPropType(rs.getString(4));
				prop.setPropRarity(rs.getString(5));
				prop.setPropDescription(rs.getString(6));
				prop.setPropImagePath(rs.getString(7));
				prop.setCreatedTime(rs.getTimestamp(8));
				prop.setUpdatedTime(rs.getTimestamp(9));
				props.add(prop);
				}
			}			
			HttpSession session = request.getSession();
            session.setAttribute("selectedGameIdForMarket", selectedGameIdCheck);
			session.setAttribute("props", props);
			stmt.close();
			conn.close();
			request.getRequestDispatcher("/jsp/getAllProps.jsp").forward(request, response);
        
        } catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
