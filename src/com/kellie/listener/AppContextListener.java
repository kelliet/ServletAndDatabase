package com.kellie.listener;

import com.kellie.database.DBConnection;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;

/**
 * Created by kellie on 12/2/16.
 */
@WebListener
public class AppContextListener implements ServletContextListener {

        public void contextInitialized(ServletContextEvent servletContextEvent) {
            ServletContext ctx = servletContextEvent.getServletContext();

            String url = ctx.getInitParameter("DBURL");
            String u = ctx.getInitParameter("DBUSER");
            String p = ctx.getInitParameter("DBPWD");

            //create database connection from init parameters and set it to context
            try {
                DBConnection dbManager = new DBConnection("nothing for now");
                ctx.setAttribute("DBManager", dbManager);
            } catch (Exception e) {
            }

            System.out.println("Database connection initialized for Application.");
        }

        public void contextDestroyed(ServletContextEvent servletContextEvent) {
            ServletContext ctx = servletContextEvent.getServletContext();
            DBConnection dbManager = (DBConnection) ctx.getAttribute("DBManager");
            try {
                dbManager.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Database connection closed for Application.");

        }

    }

