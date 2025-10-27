package com.example.healthhub.controller;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import com.example.healthhub.model.*;
import com.example.healthhub.repo.PersonRepo;


@WebServlet("/bmi")

public class BmiServlet extends HttpServlet {
    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp)
        throws javax.servlet.ServletException, java.io.IOException{

            String name = req.getParameter("name");
            int yob = Integer.parseInt(req.getParameter("yob"));
            double weight = Double.parseDouble(req.getParameter("weight"));
            double height = Double.parseDouble(req.getParameter("height"));

            Person person = new Person(name,yob,weight,height);
            //PersonRepo.save(person);
            //req.setAttribute("person", person);
            HttpSession session = req.getSession();
            session.setAttribute("person", person);
            //getServletContext().setAttribute("person", person);
            //req.getRequestDispatcher("/result.jsp").forward(req, resp);
            resp.sendRedirect("result.jsp");
            }
        
    

    protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp)
        throws javax.servlet.ServletException, java.io.IOException{

    
          req.getRequestDispatcher("form.html").forward(req, resp);  
        
    }
}
