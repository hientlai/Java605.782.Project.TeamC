/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.jhu.teamc.controller;

import edu.jhu.teamc.dao.FacultyDAO;
import edu.jhu.teamc.dao.FacultyDAOImpl;
import edu.jhu.teamc.dao.StaffDAO;
import edu.jhu.teamc.dao.StaffDAOImpl;
import edu.jhu.teamc.dao.StudentDAO;
import edu.jhu.teamc.dao.StudentDAOImpl;
import edu.jhu.teamc.model.User;
import edu.jhu.teamc.util.JDBCDBUtil;
import edu.jhu.teamc.util.Utils;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
@WebServlet(name = "Login_servlet", urlPatterns = {"/Login_servlet"}, initParams = {
    @WebInitParam(name = "attemptno", value = "3")})
public class Login extends HttpServlet {

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
        ServletContext sc = this.getServletContext();
        HttpSession session = request.getSession(true);
        String userName = null;
        String userid = request.getParameter("userid_login");
        String password = request.getParameter("password_login");
        boolean validateUserPass = Utils.validateUserNamePassword(userid, password, response);
        if (!validateUserPass) {
            return;
        }
        //check user id and password in database
        boolean isSuccess = false;
        String role = null;
        StudentDAO studentdao = new StudentDAOImpl(JDBCDBUtil.getConnection());
        if (studentdao.isUserIdPasswordMatch(userid, password)) {
            session.setAttribute("isLogged", true);
            isSuccess = true;
            final User user = studentdao.getUser(userid);
            if (user != null) {
                String firstName = user.getFirstName();
                String lastName = user.getLastName();
                if (firstName != null && lastName != null) {
                    userName = firstName + " " + lastName;
                } else {
                    userName = null;
                }
                session.setAttribute("student_id", user.getId());
                role = STUDENT;
            }
        }

        StaffDAO staffdao = new StaffDAOImpl(JDBCDBUtil.getConnection());
        if (staffdao.isUserIdPasswordMatch(userid, password)) {
            session.setAttribute("isLogged", true);
            isSuccess = true;
            final User user = staffdao.getUser(userid);
            String firstName = user.getFirstName();
            String lastName = user.getLastName();
            if (firstName != null && lastName != null) {
                userName = firstName + " " + lastName;
                session.setAttribute("staff_id", user.getId());
            } else {
                userName = null;
            }
            role = STAFF;
        }

        FacultyDAO facultydao = new FacultyDAOImpl(JDBCDBUtil.getConnection());
        if (facultydao.isUserIdPasswordMatch(userid, password)) {
            session.setAttribute("isLogged", true);
            isSuccess = true;
            User facultyUser = facultydao.getUser(userid);
            String firstName = facultyUser.getFirstName();
            String lastName = facultyUser.getLastName();
            session.setAttribute("facultyUser", facultyUser);
            if (firstName != null && lastName != null) {
                userName = firstName + " " + lastName;
            } else {
                userName = null;
            }
            role = FACULTY;
        }
        if (isSuccess == true) {
            session.setAttribute("userName", userName);
        }
        if (isSuccess == false) {

            int attemptno = Integer.parseInt(getServletConfig().getInitParameter("attemptno"));
            Integer counter = (Integer) session.getAttribute("countAttempt");
            System.out.println("attemptno " + attemptno + " " + counter);
            if (counter == null || counter == 0) {
                session.setAttribute("countAttempt", 1);
            } else {
                if (counter >= attemptno) {
                    //Terminate session 
                    session.invalidate();
                    try (PrintWriter out = response.getWriter()) {
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Servlet Login Fail</title>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1><font color=\"red\">Sorry, you have tried to attempt login exceed " + attemptno + " times.</font></h1>");
                        out.println("</body>");
                        out.println("</html>");
                    }
                    return;
                }
                session.setAttribute("countAttempt", counter + 1);
            }
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet Login Fail</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1><font color=\"red\">Sorry, you don't have an account. You must register first.</font></h1>");
                out.println("</body>");
                out.println("</html>");
            }
        } else if (STUDENT.equals(role)) {
            RequestDispatcher rd = request.getRequestDispatcher("studentwelcome.jsp");
            rd.forward(request, response);
        } else if (STAFF.equals(role)) {
            RequestDispatcher rd = request.getRequestDispatcher("staffwelcome.jsp");
            rd.forward(request, response);
        } else if (FACULTY.equals(role)) {
            RequestDispatcher rd = request.getRequestDispatcher("facultywelcome.jsp");
            rd.forward(request, response);
        }

    }
    public static final String FACULTY = "Faculty";
    public static final String STAFF = "Staff";
    public static final String STUDENT = "Student";

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
