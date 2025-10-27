package com.example.healthhub.controller;
import com.example.healthhub.repo.PersonRepo;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/person/list")
public class AdminPersonListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        //get person list from repo and add this list to req;      
        req.setAttribute("persons", PersonRepo.getAll());

        //get person list from repo and add this list to req;
        req.getRequestDispatcher("/WEB-INF/admin/admin_person_list.jsp").forward(req, resp);
    }
}