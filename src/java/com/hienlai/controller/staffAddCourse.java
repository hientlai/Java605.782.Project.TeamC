package com.hienlai.controller;

import com.hienlai.dao.CourseDAO;
import com.hienlai.dao.CourseDAOImpl;
import com.hienlai.dao.OfferingDAO;
import com.hienlai.dao.OfferingDAOImpl;
import com.hienlai.util.JDBCDBUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by edwardkim on 7/24/16.
 */
@WebServlet("/createStaffCourse")
public class staffAddCourse extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String msg;
        RequestDispatcher rd;

        int courseId = Integer.parseInt(request.getParameter("courseId"));
        String courseTitle = request.getParameter("courseTitle");
        int credits = Integer.parseInt(request.getParameter("credit"));

        CourseDAO dao = new CourseDAOImpl(JDBCDBUtil.getConnection());
        if (courseId > 0 && courseTitle != null) {
            if (!dao.isCourseExist(courseId)) {
                boolean status = dao.insertCourse(courseId, courseTitle, credits);

                if (status) {
                    msg = "Course successfully added!";
                    session.setAttribute("staffMsg", msg);
                    rd = request.getRequestDispatcher("mainStaffPage.jsp");
                    rd.forward(request, response);

                } else {
                    msg = "Failed to add course. Please try again";
                    session.setAttribute("staffMsg", msg);
                    rd = request.getRequestDispatcher("createStaffCourse.jsp");
                    rd.forward(request, response);
                }
            }
        } else {
            if (courseId < 1) {
                msg = "Course ID must be greater than 0";
                session.setAttribute("staffMsg", msg);
                rd = request.getRequestDispatcher("createStaffCourse.jsp");
                rd.forward(request, response);
            } else if (courseTitle == null || courseTitle.equals("")) {
                msg = "Please enter the course title";
                session.setAttribute("staffMsg", msg);
                rd = request.getRequestDispatcher("createStaffCourse.jsp");
                rd.forward(request, response);
            } else {
                msg = "UNKNOWN ERROR: Please retry the form.";
                session.setAttribute("staffMsg", msg);
                rd = request.getRequestDispatcher("createStaffCourse.jsp");
                rd.forward(request, response);
            }
        }
    }

}
