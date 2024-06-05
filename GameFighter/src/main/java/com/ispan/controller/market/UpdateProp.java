package com.market.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.market.bean.PropBean;
import com.market.dao.PropDao;
import com.market.util.JndiToJdbc;

@WebServlet("/UpdateProp")
@MultipartConfig
public class UpdateProp extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        PropBean prop = new PropBean();
        prop.setPropId(Integer.valueOf(request.getParameter("propId")));
        prop.setPropName(request.getParameter("propName"));
        prop.setPropType(request.getParameter("propType"));
        prop.setPropRarity(request.getParameter("propRarity"));
        prop.setPropDescription(request.getParameter("propDescription"));
        
        Part part = request.getPart("propImagePath");
        String filename = part.getSubmittedFileName();
        prop.setPropImagePath(filename);
        String uploadDir = "D:\\ServletProject2Workspace\\Martket\\src\\main\\webapp\\images\\";

        try {
            if (!filename.isEmpty()) {
                part.write(uploadDir + filename);
                PropDao.updateProp(prop);	
            }

            Thread.sleep(2000);
            String gameId = request.getParameter("gameId");
            response.getWriter().write(gameId);
            request.setAttribute("gameIdForUpdate", gameId);
            request.getRequestDispatcher("/GetSelectedProps").forward(request, response);
        } catch ( SQLException | InterruptedException e) {
            e.printStackTrace();
            // Add error handling logic here, e.g., set an error message in the request and forward to an error page
            // 待完成
//            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}