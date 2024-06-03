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
import java.util.stream.Collectors;

import javax.naming.NamingException;
import com.market.bean.PropBean;
import com.market.util.JndiToJdbc;
@WebServlet("/SearchProp")

public class SearchProp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchPropName =request.getParameter("searchPropName");
		HttpSession session = request.getSession();
        List<PropBean> props = (List<PropBean>) session.getAttribute("props");
        List<PropBean> searchOKprops = props.stream().filter(prop -> prop.getPropName().contains(searchPropName)).collect(Collectors.toList());
        request.setAttribute("searchOKprops", searchOKprops);
		request.getRequestDispatcher("/jsp/SearchOkProp.jsp").forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
