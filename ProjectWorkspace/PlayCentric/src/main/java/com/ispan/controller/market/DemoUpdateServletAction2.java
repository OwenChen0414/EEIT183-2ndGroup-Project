package com.ispan.controller.market;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.time.Instant;
import java.time.LocalDateTime;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.ispan.bean.market.Game2;
import com.ispan.bean.market.Prop;
import com.ispan.dao.market.Game2Service;
import com.ispan.dao.market.PropDao;
import com.ispan.dao.market.PropService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import com.ispan.util.member.HibernateSession;


@MultipartConfig
@WebServlet("/DemoUpdateServletAction2")
public class DemoUpdateServletAction2 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        insertProp(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        insertProp(request, response);
    }
    
    private void insertProp(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("insertProp method called");
        response.setContentType("text/html;charset=UTF-8");
        SessionFactory factory = HibernateSession.getFactory();
        Session session = null;
        
            session = factory.getCurrentSession();
            PropService pService = new PropService(session);
            Game2Service gService = new Game2Service(session);
            
            Game2 game2 = gService.findById(Integer.valueOf(request.getParameter("gameId")));
            System.out.println("--------------testgame2--------------");
            System.out.println(game2.getGameId());
            Prop prop = new Prop();
            prop.setGame2(game2);
            prop.setGameId(Integer.valueOf(request.getParameter("gameId")));
            System.out.println("--------------test--------------");
            System.out.println(prop.getGameId());
            prop.setPropId(Integer.valueOf(request.getParameter("propId")));
            prop.setPropName(request.getParameter("propName"));
            prop.setPropType(request.getParameter("propType"));
            prop.setPropRarity(request.getParameter("propRarity"));
            prop.setPropDescription(request.getParameter("propDescription"));
            

            Date updateTime = new Date();
            prop.setUpdatedTime(updateTime);
            Part part = request.getPart("propImageName");
            String filename = part.getSubmittedFileName();
            prop.setPropImageName(filename);
            String uploadDir = "D:\\EEIT183-2ndGroup-Project\\ProjectWorkspace\\PlayCentric\\src\\main\\webapp\\images\\market\\";
            part.write(uploadDir + filename);
            pService.update(prop);  
            System.out.println("看這邊");
            System.out.println(prop.getGameId());
            System.out.println(prop.getGame2());
            System.out.println(prop.getPropDescription());
            System.out.println(prop.getPropId());
            System.out.println(prop.getPropImageName());
            System.out.println(prop.getPropName());
            System.out.println(prop.getPropRarity());
            System.out.println(prop.getPropType());
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/DemoPropServletAction");
            dispatcher.forward(request, response);
    }
}
