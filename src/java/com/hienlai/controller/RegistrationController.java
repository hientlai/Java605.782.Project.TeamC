/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hienlai.controller;

import com.hienlai.model.CoursesSupportBean;
import com.hienlai.util.JDBCDBUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hienlai.dao.CourseDAO;
import com.hienlai.dao.CourseDAOImpl;
import com.hienlai.dao.EnrollmentDAO;
import com.hienlai.dao.EnrollmentDAOImpl;
import com.hienlai.dao.OfferingDAO;
import com.hienlai.dao.OfferingDAOImpl;
import javax.servlet.annotation.WebInitParam;

/**
 *
 * @author Hien
 */
@WebServlet(name = "RegistrationController_servlet", urlPatterns = {"/RegistrationController_servlet"}, initParams = {
    @WebInitParam(name = "coursecapacity", value = "8")})
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

        OfferingDAO offeringDao = new OfferingDAOImpl(JDBCDBUtil.getConnection());
        
        CourseDAO courseDao = new CourseDAOImpl(JDBCDBUtil.getConnection());;
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
            RequestDispatcher rd = request.getRequestDispatcher("CoursesJSP.jsp");
            String term = request.getParameter("term");
            String year = request.getParameter("year");
            List<CoursesSupportBean> courses = offeringDao.retrieveOfferingByTermYear(term, year);
            System.out.println("courses np " + courses.size());
            Gson gson = new Gson();
            JsonObject myObj = new JsonObject();
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            response.setHeader("Cache-control", "no-cache, no-store");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "-1");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "POST");
            response.setHeader("Access-Control-Allow-Headers", "Content-Type");
            response.setHeader("Access-Control-Max-Age", "86400");

            if (courses == null || courses.isEmpty()) {
                myObj.addProperty("success", false);
            } else {
                myObj.addProperty("success", true);
                JsonElement coursesObj = gson.toJsonTree(courses);
                myObj.add("courses", coursesObj);

            }
            out.println(myObj.toString());
            out.close();
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

        } else if ("Add".equals(requestType)) {
            
            //Student register a course
            int offeringId = Integer.parseInt(request.getParameter("course"));
            
            int numberStudentRegistered = 0;

            OfferingDAO dao = new OfferingDAOImpl(JDBCDBUtil.getConnection());
            EnrollmentDAO enrDao = new EnrollmentDAOImpl(JDBCDBUtil.getConnection());
            CoursesSupportBean course = dao.retrieveCourse(offeringId);
            numberStudentRegistered = dao.retrieveStudentsRegistered(offeringId);
            if(numberStudentRegistered < Integer.parseInt(getServletConfig().getInitParameter("coursecapacity"))){
                dao.updateNumberStudentsRegistered(offeringId, numberStudentRegistered + 1 );
                enrDao.insertEnrollment("Active", (String)session.getAttribute("student_id"), offeringId + "");
                request.setAttribute("message", "You have been registered to " + offeringId + " " +  course.getCourse_name() );
            }else{
                request.setAttribute("error", "Sorry, the registration to this course has been closed based on availability");
            }

            RequestDispatcher rd = request.getRequestDispatcher("registrarcourse");
            rd.forward(request, response);

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
