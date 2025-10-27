package com.example.healthhub.controller;
import com.example.healthhub.repo.PersonRepo;
import com.example.healthhub.model.Person;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/person/view")
public class AdminPersonViewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        // The URL parameter is still named "id", but it holds the list index
        String idParam = req.getParameter("id"); 

        try {
            // 1. Basic Parameter Validation
            if (idParam == null || idParam.isEmpty()) {
                // If ID is missing, redirect the user back to the list
                resp.sendRedirect(req.getContextPath() + "/admin/person/list");
                return;
            }

            // 2. Parse the parameter into the list index
            int index = Integer.parseInt(idParam);
            
            // 3. Retrieve the Person object using the index
            Person p = PersonRepo.findByIndex(index);
            
            // 4. Handle Case: Person not found (e.g., index out of bounds)
            if (p == null) {
                // Set an error message and forward back to the list
                req.setAttribute("errorMessage", "Person at index " + index + " was not found.");
                req.getRequestDispatcher("/admin_person_list.jsp").forward(req, resp);
                return;
            }
            
            // 5. Success: Set attributes and forward to the view page
            req.setAttribute("person", p);
            req.setAttribute("index", index); // Pass index to the view for reference (optional)
            
            req.getRequestDispatcher("/WEB-INF/admin/admin_person_view.jsp").forward(req, resp);
            
        } catch (NumberFormatException e) {
            // Handle Case: Parameter is not a valid number (e.g., "abc")
            req.setAttribute("errorMessage", "Invalid person index format specified.");
            req.getRequestDispatcher("/admin_person_list.jsp").forward(req, resp);
        }
    }
}