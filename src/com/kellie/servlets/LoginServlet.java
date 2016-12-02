package com.kellie.servlets;


import com.kellie.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

/**
 * Created by kellie on 11/22/16.
 */
public class LoginServlet extends HttpServlet {

    public static HashMap<String, User> users;

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException{
        System.out.println("in loginservlet doPost!!!!");

        String password = request.getParameter("password");
        String name = request.getParameter("name");
        System.out.println("Name: "+ name);
        if(name != null && !name.isEmpty() && users.containsKey(name)) {
                User u = users.get(name);
                request.setAttribute("name",u.getName());
                request.setAttribute("message", u.getSpecialMessage());
                request.setAttribute("firstTime",u.isFirstLogin());
        }

        Connection con = (Connection) getServletContext().getAttribute("DBConnection");
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("UPDATE users set arriveTime = ? where username = ? ");

           DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            ps.setString(1, LocalDate.now().toString());
            ps.setString(2, name);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("DB Connection problem.");
        }finally {
            try {
                rs.close();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

            RequestDispatcher view = request.getRequestDispatcher("/index" + ".jsp");
            view.forward(request, response);

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException{
        System.out.println();
        doPost(request,response);

    }

    public LoginServlet() {
        System.out.println("in controller servlet contructor");
        users = new HashMap<>();
        User u1 = new User("Sam","Sam likes ham...");
        users.put(u1.getName(),u1);
        User u2 = new User("Tom","Tom is tall...");
        users.put(u2.getName(),u2);
        User u3 = new User("Terry","Terry is tenacious...");
        users.put(u3.getName(),u3);
        User u4 = new User("Sally","Sally listens to The Smiths...");
        users.put(u4.getName(),u4);
    }
}
