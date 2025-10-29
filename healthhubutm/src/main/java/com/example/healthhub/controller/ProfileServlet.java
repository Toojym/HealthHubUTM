package com.example.healthhub.controller;

import com.example.healthhub.model.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

       
        HttpSession session = req.getSession(false);
        User user = null;

        if (session != null) {
            
            user = (User) session.getAttribute("user");
        }

        if (user == null) {
            
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        } else {
          
            req.getRequestDispatcher("/profile.jsp").forward(req, resp);
        }
    }
}
