<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.healthhub.model.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Profile</title>
    <style>
        body { font-family: 'Arial', sans-serif; background-color: #f4f7f9; display: flex; flex-direction: column; justify-content: center; align-items: center; min-height: 100vh; margin: 0; }
        .profile-card { background: white; padding: 30px 40px; border-radius: 12px; box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1); width: 400px; text-align: center; }
        h1 { color: #28a745; margin-bottom: 25px; font-size: 2em; }
        .detail-row { display: flex; justify-content: space-between; padding: 10px 0; border-bottom: 1px solid #eee; }
        .detail-row:last-child { border-bottom: none; }
        .label { font-weight: bold; color: #555; }
        .value { color: #007bff; font-weight: 600; }
        .actions { margin-top: 30px; }
        .btn { padding: 10px 20px; border: none; border-radius: 6px; cursor: pointer; font-size: 1em; margin: 5px; text-decoration: none; display: inline-block;}
        .btn-logout { background-color: #dc3545; color: white; transition: background-color 0.2s; }
        .btn-logout:hover { background-color: #c82333; }
        .btn-home { background-color: #6c757d; color: white; transition: background-color 0.2s; }
        .btn-home:hover { background-color: #5a6268; }
    </style>
</head>
<body>
    <% 
        // Retrieve the User object from the session
        User user = (User) session.getAttribute("user");
        
        // This check is mainly for redundancy, as ProfileServlet should handle redirection
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
    %>
    <div class="profile-card">
        <h1>Welcome, <%= user.getUsername() %>!</h1>
        <p style="color: #666; font-size: 1.1em; margin-bottom: 25px;">Your HealthHub Profile</p>

        <div class="detail-row">
            <span class="label">Username:</span>
            <span class="value"><%= user.getUsername() %></span>
        </div>
        <div class="detail-row">
            <span class="label">Access Role:</span>
            <span class="value"><%= user.getRole().toUpperCase() %></span>
        </div>

        <div class="actions">
            <!-- Link to the public BMI Form -->
            <a href="<%= request.getContextPath() %>/bmi" class="btn btn-home">Go to BMI Calculator</a>
            
            <!-- Logout link -->
            <a href="<%= request.getContextPath() %>/logout" class="btn btn-logout">Log Out</a>
        </div>
    </div>
</body>
</html>
