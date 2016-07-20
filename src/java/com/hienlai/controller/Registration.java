/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java.com.hienlai.controller;

import java.com.hienlai.dao.FacultyDAOImpl;
import java.com.hienlai.dao.StaffDAOImpl;
import java.com.hienlai.dao.StudentDAOImpl;
import java.com.hienlai.dao.UserDAO;
import java.com.hienlai.util.JDBCDBUtil;
import java.com.hienlai.util.Utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * @author Hien
 */
@WebServlet(name = "Registration_servlet", urlPatterns = {"/Registration_servlet"})
public class Registration extends HttpServlet {

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
        String requesttype = request.getParameter("requesttype");
        if ("Continue".equals(requesttype)) {
            //process for Form A
            String userid = request.getParameter("userid_reg");
            String password = request.getParameter("password_reg");
            String password_rpt = request.getParameter("password_rpt");
            String firstname = request.getParameter("firstname_reg");
            String lastname = request.getParameter("lastname_reg");
            String ssn = request.getParameter("ssn_1") + request.getParameter("ssn_2") + request.getParameter("ssn_3");
            String email = request.getParameter("email_reg");
            String role = request.getParameter("role");

            boolean validateUserPass = Utils.validateUserNamePassword(userid, password, password_rpt, response);
            if (!validateUserPass) {
                return;
            }

            //Store request parameters to session 
            session.setAttribute("userid", userid);
            session.setAttribute("password", password);
            session.setAttribute("firstname", firstname);
            session.setAttribute("lastname", lastname);
            session.setAttribute("ssn", ssn);
            session.setAttribute("email", email);
            session.setAttribute("role", role);

            //redirect to Form B
            RequestDispatcher rd = request.getRequestDispatcher("registrationFormB.html");
            rd.forward(request, response);
        } else if ("RegisterFormB".equals(requesttype)) {
            //process for Form B
            String address = request.getParameter("address_reg") + " " + request.getParameter("city_reg") + ", " + request.getParameter("state") + " " + request.getParameter("zip_reg");

            String userid = (String) session.getAttribute("userid");
            String password = (String) session.getAttribute("password");
            String firstname = (String) session.getAttribute("firstname");
            String lastname = (String) session.getAttribute("lastname");
            String ssn = (String) session.getAttribute("ssn");
            String email = (String) session.getAttribute("email");
            String role = (String) session.getAttribute("role");
            //Insert into database

            UserDAO dao = null;
            boolean isInserted = false;
            if ("Student".equals(role)) {
                dao = new StudentDAOImpl(JDBCDBUtil.getConnection());
                if (dao.getUser(userid) == null) {
                    isInserted = dao.insert(firstname, lastname, ssn, email, address, userid, password);
                    if (isInserted) {
                        Utils.showStudentWelcomePage(firstname + " " + lastname, response);
                    }
                } else {
                    //Check if the user is already exist
                    request.setAttribute("errorMessage", "The user is already exist.");
                    RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
                    rd.forward(request, response);
                }
            } else if ("Staff".equals(role)) {
                dao = new StaffDAOImpl(JDBCDBUtil.getConnection());
                if (dao.getUser(userid) == null) {
                    isInserted = dao.insert(firstname, lastname, ssn, email, address, userid, password);
                    if (isInserted) {
                        Utils.showStaffWelcomePage(firstname + " " + lastname, response);
                    }
                } else {
                    //Check if the user is already exist
                    request.setAttribute("errorMessage", "The user is already exist.");
                    RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
                    rd.forward(request, response);
                }
            } else if ("Faculty".equals(role)) {
                dao = new FacultyDAOImpl(JDBCDBUtil.getConnection());
                if (dao.getUser(userid) == null) {
                    isInserted = dao.insert(firstname, lastname, ssn, email, address, userid, password);
                    if (isInserted) {
                        Utils.showFacultyWelcomePage(firstname + " " + lastname, response);
                    }
                } else {
                    //Check if the user is already exist
                    request.setAttribute("errorMessage", "The user is already exist.");
                    RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
                    rd.forward(request, response);
                }
            }

            if (isInserted) {
                session.setAttribute("isLogged", true);
            } else {
                //Show error message to user if there is any issue with application.
                request.setAttribute("errorMessage", "Unable to register the user. Please contact the administrator");
                RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
                rd.forward(request, response);
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
