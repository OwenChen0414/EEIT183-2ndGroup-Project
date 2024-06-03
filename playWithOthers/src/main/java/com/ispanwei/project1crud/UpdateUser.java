package com.ispanwei.project1crud;



import com.ispanwei.bean.PlayUserBean;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/UpdateUser")
public class UpdateUser extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String id = request.getParameter("id");
		PwDAO pwDAO = new PwDAO();
		PlayUserBean user = null;
		try {
			user = pwDAO.getOneUser(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("user", user);
		request.getRequestDispatcher("/Project1/UpdateUser.jsp").forward(request, response);
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}