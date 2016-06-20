/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hienlai.controller;

import com.hienlai.dao.CourseDAO;
import com.hienlai.dao.CourseDAOImpl;
import com.hienlai.dao.RegistrarDAO;
import com.hienlai.dao.RegistrarDAOImpl;
import com.hienlai.model.CoursesSupportBean;
import com.hienlai.util.JDBCDBUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Hien
 */
@WebServlet(name = "RegistrationController_servlet", urlPatterns = {"/RegistrationController_servlet"}, initParams = {
    @WebInitParam(name = "weblogic.url", value = "t3://localhost:7001"),
    @WebInitParam(name = "weblogic.user", value = "weblogic"),
    @WebInitParam(name = "weblogic.password", value = "Test0903")})
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

        //set session variables for weblogic
        String weblogicUrl = getServletConfig().getInitParameter("weblogic.url");
        String weblogicUser = getServletConfig().getInitParameter("weblogic.user");
        String weblogicPassword = getServletConfig().getInitParameter("weblogic.password");
        session.setAttribute("weblogic.url", weblogicUrl);
        session.setAttribute("weblogic.user", weblogicUser);
        session.setAttribute("weblogic.password", weblogicPassword);

        CourseDAO courseDao = null;

        courseDao = new CourseDAOImpl(JDBCDBUtil.getConnection());

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
            List<CoursesSupportBean> courses = courseDao.retrieveCourses();
            request.setAttribute("courses", courses);
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

        } else if ("Add".equals(requestType)) {
            int numberStudentRegistered = 0;

            RegistrarDAO dao = new RegistrarDAOImpl(JDBCDBUtil.getConnection());
            int courseId = Integer.parseInt(request.getParameter("course"));
            numberStudentRegistered = dao.retrieveStudentsRegistered(courseId);
            CoursesSupportBean course = courseDao.retrieveCourse(courseId);
            request.setAttribute("numberStudentRegistered", numberStudentRegistered);
            request.setAttribute("courseName", course.getCourse_name());
            request.setAttribute("courseId", courseId);
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
