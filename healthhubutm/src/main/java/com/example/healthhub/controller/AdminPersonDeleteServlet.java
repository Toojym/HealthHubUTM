package com.example.healthhub.controller;
import com.example.healthhub.repo.PersonRepo;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/person/delete")
public class AdminPersonDeleteServlet extends HttpServlet {
    @Override // Good practice to add @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        // The parameter is named "id" but holds the list index.
        String idParam = req.getParameter("id");
        
        // 1. Check for missing ID (index)
        if (idParam == null || idParam.isEmpty()) {
            // Redirect back to the list if ID is missing
            resp.sendRedirect(req.getContextPath() + "/admin/person/list?error=missing_id");
            return;
        }

        try {
            // 2. Safely parse the ID (index)
            int index = Integer.parseInt(idParam);
            
            // 3. Execute the delete operation using the index
            // PersonRepo.deleteByIndex handles bounds checking internally (as shown previously).
            PersonRepo.deleteByIndex(index);
            
            // 4. PRG Pattern: Redirect back to the list view after a successful POST operation.
            // This prevents duplicate deletion on browser refresh.
            resp.sendRedirect(req.getContextPath() + "/admin/person/list?message=deleted");
            
        } catch (NumberFormatException e) {
            // Handle non-numeric ID input
            System.err.println("Invalid index format for deletion: " + idParam);
            
            // Redirect back to the list page with an error flag
            resp.sendRedirect(req.getContextPath() + "/admin/person/list?error=invalid_id");
            
        } catch (Exception e) {
            // Catch other unexpected errors
            throw new ServletException("Failed to delete person at index " + idParam, e);
        }
    }
}