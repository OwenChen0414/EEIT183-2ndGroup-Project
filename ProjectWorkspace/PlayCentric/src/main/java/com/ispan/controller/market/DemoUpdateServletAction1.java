package com.ispan.controller.market;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;

import java.io.IOException;


@WebServlet("/DemoUpdateServletAction1")
public class DemoUpdateServletAction1 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processAction(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processAction(request, response);
    }

    private void processAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	
        response.setContentType("text/html;charset=UTF-8");
		String gameId =request.getParameter("gameId");
		String propId =request.getParameter("propId");
        String propName =request.getParameter("propName");
		String propType =request.getParameter("propType");
		String propRarity =request.getParameter("propRarity");
		String propDescription =request.getParameter("propDescription");
		String propImageName =request.getParameter("propImageName");

//      把值送給jsp顯示
		request.setAttribute("gameId", gameId);
		request.setAttribute("propId", propId);
		request.setAttribute("propName", propName);
		request.setAttribute("propType", propType);
		request.setAttribute("propRarity", propRarity);
		request.setAttribute("propDescription", propDescription);
		request.setAttribute("propImagePath", propImageName);
        // 使用轉發 forward 到 index.jsp 頁面
		request.getRequestDispatcher("/dynamicView/market/updateProp.jsp").forward(request, response);
    }
}
