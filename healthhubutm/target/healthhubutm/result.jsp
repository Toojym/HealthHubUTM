<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page isELIgnored="false" %>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>HealthHub@UTM : BMI Result</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f6f8fb;
        margin: 0;
        padding: 40px;
    }
    .card {
        max-width: 500px;
        margin: auto;
        background: #fff;
        padding: 20px 24px;
        border-radius: 12px;
        box-shadow: 0 5px 20px rgba(0,0,0,0.1);
    }
    h1 {
        text-align: center;
        font-size: 1.4em;
        color: #333;
    }
    .result-item {
        margin-bottom: 15px;
        padding: 10px;
        border-bottom: 1px solid #eee;
    }
    .result-item strong {
        display: block;
        color: #555;
        font-size: 0.9em;
        margin-bottom: 2px;
    }
    .result-value {
        font-size: 1.1em;
        font-weight: bold;
        color: #007bff; /* Highlight result values */
    }
    .back-link {
        display: block;
        text-align: center;
        margin-top: 20px;
        color: #007bff;
        text-decoration: none;
        font-weight: bold;
    }
    /* Specific style for BMI category */
    .bmi-category-normal { color: green; }
    .bmi-category-overweight { color: orange; }
    .bmi-category-obese { color: red; }
    
</style>
</head>
<body>

<%-- Retrieve the Person object from the request scope set by the BmiServlet --%>
<jsp:useBean id="person" scope="request" type="com.example.healthhub.model.Person" />

<div class="card">
    <h1>BMI Calculation Result</h1>

    <div class="result-item">
        <strong>Name:</strong>
        <span class="result-value">${person.name}</span>
    </div>

    <div class="result-item">
        <strong>Age:</strong>
        <span class="result-value"><%= java.time.Year.now().getValue() - person.getYob() %> years old</span>
    </div>
    
    <div class="result-item">
        <strong>Height (m):</strong>
        <span class="result-value">${person.height}</span>
    </div>
    
    <div class="result-item">
        <strong>Weight (kg):</strong>
        <span class="result-value">${person.weight}</span>
    </div>

    <div class="result-item" style="border-top: 2px solid #007bff; padding-top: 15px;">
        <strong>Body Mass Index (BMI):</strong>
        <% 
            // 1. Calculate BMI (Example calculation, assuming person has getHeight and getWeight)
            double height = person.getHeight();
            double weight = person.getWeight();
            double bmi = weight / (height * height);
            
            // 2. Determine Category (Simplified for example)
            String category = "Unknown";
            String categoryClass = "";
            if (bmi < 18.5) {
                category = "Underweight";
            } else if (bmi < 25) {
                category = "Normal";
                categoryClass = "bmi-category-normal";
            } else if (bmi < 30) {
                category = "Overweight";
                categoryClass = "bmi-category-overweight";
            } else {
                category = "Obese";
                categoryClass = "bmi-category-obese";
            }
        %>
        
        <span class="result-value">
            <%= String.format("%.2f", bmi) %> 
        </span>
    </div>
    
    <div class="result-item">
        <strong>BMI Category:</strong>
        <span class="result-value <%= categoryClass %>">
            <%= category %>
        </span>
    </div>

    <a href="<%= request.getContextPath() %>/bmi" class="back-link">Calculate Another BMI</a>
    
</div>
<%-- NEW ADMIN BUTTON OUTSIDE THE CARD --%>
<a href="<%= request.getContextPath() %>/admin/person/list" class="back-link">
    Go to Admin Page
</a>
</body>
</html>