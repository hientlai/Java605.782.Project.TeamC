package edu.jhu.teamc.controller;

import edu.jhu.teamc.dao.OfferingDAO;
import edu.jhu.teamc.dao.OfferingDAOImpl;
import edu.jhu.teamc.util.JDBCDBUtil;

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

        int offeringId = Integer.parseInt(request.getParameter("offeringid"));
        int courseOverride = Integer.parseInt(request.getParameter("courseCapacity"));

        OfferingDAO offeringDAO = new OfferingDAOImpl(JDBCDBUtil.getConnection());
        if (offeringId > 0 && courseOverride > 0) {
            if (offeringDAO.checkCourseExists(offeringId)) {
                boolean status = offeringDAO.overrideSelectedCourseCapacty(offeringId, courseOverride);

                if (status) {
                    msg = "Course capacity is successfully modified!";
                    session.setAttribute("message", msg);
                    rd = request.getRequestDispatcher("staffwelcome.jsp");
                    request.setAttribute("message", msg);
                    rd.forward(request, response);
                } else {
                    msg = "Failed to modify course capacity. Please try again";
                    request.setAttribute("message", msg);
                    rd = request.getRequestDispatcher("staffOverride.jsp");
                    rd.forward(request, response);
                }
            } else {
                msg = "Failed to add course. Please try again";
                request.setAttribute("message", msg);
                rd = request.getRequestDispatcher("staffOverride.jsp");
                rd.forward(request, response);
            }
        }
    }

}
