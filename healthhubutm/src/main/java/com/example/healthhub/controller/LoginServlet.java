package com.example.healthhub.controller;

import com.example.healthhub.model.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        
        // 1. Basic Validation
        if (username == null || username.trim().isEmpty() || password == null || password.isEmpty()) {
            req.setAttribute("errorMessage", "Username and password are required.");
            // Forward back to login.jsp to preserve the error message (Forward)
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }

        // 2. Mock Authentication (Replace with actual database logic later)
        String role = null;
        if ("admin".equals(username) && "admin".equals(password)) {
            role = "admin";
        } else if ("user".equals(username) && "user".equals(password)) {
            role = "member";
        }

        if (role != null) {
            // 3. Authentication Successful: Create Session
            HttpSession session = req.getSession(true); // Create new session if one doesn't exist
            User authenticatedUser = new User(username, role);
            
            // Store the User object in the session scope
            session.setAttribute("user", authenticatedUser);
            

            // 4. Redirect to the profile page (Redirect)
            // Redirect is used to prevent form resubmission on refresh
            resp.sendRedirect(req.getContextPath() + "/profile");
            
        } else {
            // 5. Authentication Failed
            req.setAttribute("errorMessage", "Invalid username or password.");
            // Forward back to login.jsp to show the error (Forward)
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Handle direct access to /login by showing the form
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }
}
