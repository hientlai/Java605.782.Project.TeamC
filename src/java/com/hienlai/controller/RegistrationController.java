/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hienlai.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author Hien
 */
@WebServlet(name = "RegistrationController_servlet", urlPatterns = {"/RegistrationController_servlet"})
public class RegistrationController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);
        String requestType = (request.getParameter("requesttype"));

        if ("Submit".equals(requestType)) {
            //redirect it to Login_servlet
            RequestDispatcher rd = request.getRequestDispatcher("Login_servlet");
            rd.forward(request, response);
        } else if ("Register".equals(requestType)) {
            //redirect it to Registration Form A
            RequestDispatcher rd = request.getRequestDispatcher("registrationFormA.html");
            rd.forward(request, response);
        } else if ("Continue".equals(requestType)) {
            //redirect it to Registration_servlet
            RequestDispatcher rd = request.getRequestDispatcher("Registration_servlet");
            rd.forward(request, response);
        } else if ("RegisterFormB".equals(requestType)) {
            //redirect it to Registration Form B
            RequestDispatcher rd = request.getRequestDispatcher("Registration_servlet");
            rd.forward(request, response);
        } else if ("CoursesRegister".equals(requestType)) {
            RequestDispatcher rd = request.getRequestDispatcher("CoursesJSP.jsp");
            rd.forward(request, response);
        } else if ("CoursesList".equals(requestType)) {
            //Student views a course list in order to do enrollment
            RequestDispatcher rd = request.getRequestDispatcher("Student_servlet");
            rd.forward(request, response);
        } else if ("Enroll".equals(requestType)) {
            //Student enrolls into a class
            RequestDispatcher rd = request.getRequestDispatcher("Student_servlet");
            rd.forward(request, response);
        } else if ("Logout".equals(requestType)) {
            //redirect it to Logout
            session.invalidate();
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet Login Fail</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h2><font color=\"blue\">You have logged out successfully.</font></h2>");
                out.println("</body>");
                out.println("</html>");
            }

        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
