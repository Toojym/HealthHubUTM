<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.example.healthhub.model.Person" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>HealthHub@UTM : Admin Person List</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/admin.css">
</head>
<body>

<%
    // Retrieve attributes and parameters
    @SuppressWarnings("unchecked")
    List<Person> persons = (List<Person>) request.getAttribute("persons");
    String errorMessage = (String) request.getAttribute("errorMessage");
    String successMessage = request.getParameter("message"); // From delete redirect
    int index = 0; 
%>

<div class="card">
    <h1>Registered Person List (Admin)</h1>

    <%-- Display Messages --%>
    <% if (errorMessage != null) { %>
        <p class="error-message">Error: <%= errorMessage %></p>
    <% } %>
    <% if (successMessage != null && successMessage.equals("deleted")) { %>
        <p style="color: green; font-weight: bold;">Record successfully deleted.</p>
    <% } %>

    <% 
        if (persons == null || persons.isEmpty()) { 
    %>
            <p class="error-message">No registered persons found in the system.</p>
    <%
        } else {
    %>
            <table>
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Year of Birth</th>
                        <th>Weight (kg)</th>
                        <th>Height (m)</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        for (Person person : persons) { 
                    %>
                        <tr>
                            <td><%= person.getName() %></td>
                            <td><%= person.getYob() %></td>
                            <td><%= person.getWeight() %></td>
                            <td><%= person.getHeight() %></td>
                            <td>
                                <%-- View link using the list index as the ID parameter --%>
                                <a href="<%= request.getContextPath() %>/admin/person/view?id=<%= index %>" 
                                        class="action-link">View Details</a>
                                
                                <%-- Delete form using the list index as the ID parameter --%>
                                <form action="<%= request.getContextPath() %>/admin/person/delete" method="POST" style="display: inline;">
                                    <input type="hidden" name="id" value="<%= index %>">
                                    <button type="submit" 
                                            class="action-link" 
                                            style="background: none; border: none; color: red; cursor: pointer; padding: 0;"
                                            onclick="return confirm('Are you sure you want to delete <%= person.getName() %>?');">
                                        Delete
                                    </button>
                                </form>
                            </td>
                        </tr>
                    <% 
                        index++;
                        } 
                    %>
                </tbody>
            </table>
    <%
        } 
    %>

    <a href="<%= request.getContextPath() %>/bmi" class="back-link">Back to BMI Calculator</a>
</div>

</body>
</html>