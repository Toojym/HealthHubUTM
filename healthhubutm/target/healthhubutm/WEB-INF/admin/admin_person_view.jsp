<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.healthhub.model.Person" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>HealthHub@UTM : Person Details</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/admin.css">
    </head>
<body>

<%
    // Retrieve attributes set by AdminPersonViewServlet
    Person person = (Person) request.getAttribute("person");
    Integer index = (Integer) request.getAttribute("index"); // The 'ID' substitute
    
    if (person == null) {
        // Redirect if person object is missing (should be caught by servlet, but safe)
        response.sendRedirect(request.getContextPath() + "/admin/person/list");
        return;
    }
    
    // Calculate values for display
    double bmi = person.calcBMI();
    String bmiFormatted = String.format("%.2f", bmi);
    String category = person.getCategory();
%>

<div class="card" style="max-width: 600px;">
    
    <h1>Details for: <%= person.getName() %></h1>

    <div class="detail-row">
        <span class="detail-label">ID:</span>
        <span class="detail-value"><%= index %></span>
    </div>
    
    <div class="detail-row">
        <span class="detail-label">Name:</span>
        <span class="detail-value"><%= person.getName() %></span>
    </div>

    <div class="detail-row">
        <span class="detail-label">Year of Birth:</span>
        <span class="detail-value"><%= person.getYob() %></span>
    </div>
    
    <div class="detail-row">
        <span class="detail-label">Height (m):</span>
        <span class="detail-value"><%= person.getHeight() %></span>
    </div>

    <div class="detail-row">
        <span class="detail-label">Weight (kg):</span>
        <span class="detail-value"><%= person.getWeight() %></span>
    </div>
    
    <div class="detail-row" style="border-top: 2px solid #007bff; padding-top: 15px;">
        <span class="detail-label">BMI:</span>
        <span class="detail-value"><%= bmiFormatted %></span>
    </div>
    
    <div class="detail-row">
        <span class="detail-label">Category:</span>
        <span class="detail-value"><%= category %></span>
    </div>

    <a href="<%= request.getContextPath() %>/admin/person/list" class="back-link">Back to List</a>
</div>

</body>
</html>