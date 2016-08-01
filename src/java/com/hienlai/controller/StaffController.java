package com.hienlai.controller;

import com.hienlai.dao.StaffDAOImpl;
import com.hienlai.model.StaffSupportBean;
import com.hienlai.util.JDBCDBUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by edwardkim on 7/23/16.
 */
@WebServlet("/staffController")
public class StaffController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processGet(request, response);
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        RequestDispatcher rd;
        String requestType = request.getParameter("requesttype");

        switch(requestType) {
            case "CreateCourse" :
                System.out.println("Create course selected...");
                rd = request.getRequestDispatcher("createStaffCourse.jsp");
                session.setAttribute("staffRequestType", requestType);
                rd.forward(request, response);
                break;
            case "OverrideCourses" :
                System.out.println("Override course selected...");
                session.setAttribute("overrideRequestType", requestType);
                rd = request.getRequestDispatcher("staffOverride.jsp");
                rd.forward(request, response);
                break;
            case "Logout" :
                session.invalidate();
                rd = request.getRequestDispatcher("login.jsp");
                rd.forward(request, response);
                break;
            default:
                return;
        }

    }

}
