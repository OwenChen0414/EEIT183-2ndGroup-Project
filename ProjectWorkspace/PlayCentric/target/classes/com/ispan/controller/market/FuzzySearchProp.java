package com.ispan.controller.market;
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
import com.ispan.bean.market.PropBean;
import com.ispan.util.market.JndiToJdbc;
@WebServlet("/FuzzySearchProp")

public class FuzzySearchProp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchPropName =request.getParameter("searchPropName");
		HttpSession session = request.getSession();
        List<PropBean> props = (List<PropBean>) session.getAttribute("props");
        List<PropBean> searchOKprops = props.stream().filter(prop -> prop.getPropName().contains(searchPropName)).collect(Collectors.toList());
        request.setAttribute("searchOKprops", searchOKprops);
		request.getRequestDispatcher("/dynamicView/market/fuzzySearchOkProp.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
