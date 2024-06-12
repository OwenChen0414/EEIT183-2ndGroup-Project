package com.ispan.listener;

import com.ispan.util.member.HibernateSession;

import jakarta.servlet.ServletContextEvent;

public class ServletContextListener implements jakarta.servlet.ServletContextListener {

    public void contextInitialized(ServletContextEvent sce)  { 
    	HibernateSession.initialize();
    }

    public void contextDestroyed(ServletContextEvent sce)  { 
    	HibernateSession.closeFactory();
    }
	
}
