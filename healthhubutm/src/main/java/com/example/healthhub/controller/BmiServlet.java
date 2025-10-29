package com.example.healthhub.controller;

import com.example.healthhub.model.Person;
import com.example.healthhub.repo.PersonRepo;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/bmi")
public class BmiServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
       
        req.getRequestDispatcher("/form.html").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        String name = req.getParameter("name");
        String yobStr = req.getParameter("yob");
        String weightStr = req.getParameter("weight");
        String heightStr = req.getParameter("height");

        
        if (name == null || name.trim().isEmpty() || yobStr == null || yobStr.trim().isEmpty() || 
            weightStr == null || weightStr.trim().isEmpty() || heightStr == null || heightStr.trim().isEmpty()) {
            
            req.setAttribute("errorMessage", "All fields are mandatory.");
           
            req.getRequestDispatcher("/form.html").forward(req, resp);
            return;
        }

        try {
            int yob = Integer.parseInt(yobStr);
            double weight = Double.parseDouble(weightStr);
            double height = Double.parseDouble(heightStr);

            
            if (yob <= 0 || weight <= 0 || height <= 0) {
                throw new IllegalArgumentException("Year of birth, weight, and height must be positive.");
            }
            
           
            Person newPerson = new Person(name, yob, weight, height);
            
           
            PersonRepo.save(newPerson);

           
            req.setAttribute("person", newPerson);
            
            
            req.getRequestDispatcher("/result.jsp").forward(req, resp);
            
        } catch (NumberFormatException e) {
            req.setAttribute("errorMessage", "Year, weight, and height must be valid numbers.");
           
            req.getRequestDispatcher("/form.html").forward(req, resp);
        } catch (IllegalArgumentException e) {
            req.setAttribute("errorMessage", e.getMessage());
            
            req.getRequestDispatcher("/form.html").forward(req, resp);
        }
    }
}
