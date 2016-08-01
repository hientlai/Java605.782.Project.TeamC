package com.hienlai.controller;

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

/**
 * Created by edwardkim on 7/27/16.
 */
@WebServlet("/overrideStaffCourse")
public class StaffOverrideCourse extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String msg;
        RequestDispatcher rd;

        int courseId = Integer.parseInt(request.getParameter("courseId"));
        int courseOverride = Integer.parseInt(request.getParameter("courseOverride"));

        OfferingDAO offeringDAO = new OfferingDAOImpl(JDBCDBUtil.getConnection());
        if (courseId > 0 && courseOverride > 0) {
            if (offeringDAO.checkCourseExists(courseId)) {
                boolean status = offeringDAO.overrideSelectedCourseCapacty(courseId, courseOverride);

                if (status) {
                    msg = "Course successfully modified!";
                    session.setAttribute("staffMsg", msg);
                    rd = request.getRequestDispatcher("mainStaffPage.jsp");
                    session.setAttribute("staffMsg", msg);
                    rd.forward(request, response);
                } else {
                    msg = "Failed to add course. Please try again";
                    session.setAttribute("staffMsg", msg);
                    rd = request.getRequestDispatcher("staffOverride.jsp");
                    session.setAttribute("staffMsg", msg);
                    rd.forward(request, response);
                }
            } else {
                msg = "Failed to add course. Please try again";
                session.setAttribute("staffMsg", msg);
                rd = request.getRequestDispatcher("staffOverride.jsp");
                session.setAttribute("staffMsg", msg);
                rd.forward(request, response);
            }
        }
    }

}
