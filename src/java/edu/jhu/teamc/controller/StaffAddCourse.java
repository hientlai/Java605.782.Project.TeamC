package edu.jhu.teamc.controller;

import edu.jhu.teamc.dao.CourseDAO;
import edu.jhu.teamc.dao.CourseDAOImpl;
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
 * Created by edwardkim on 7/24/16.
 */
@WebServlet(name = "createStaffCourse", urlPatterns = {"/createStaffCourse"})
public class StaffAddCourse extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String msg = "";
        RequestDispatcher rd;
        String requesttype = request.getParameter("requesttype");
        System.out.println("requesttype " + requesttype);
        if ("Assign".equals(requesttype)) {
            String term = request.getParameter("terms");
            String years = request.getParameter("years");
            String courseId = request.getParameter("course");
            String facultyId = request.getParameter("faculty");
            String location = request.getParameter("locations");
            String courseCapacity = request.getParameter("courseCapacity");
            String staffId = (String) session.getAttribute("staff_id");
            OfferingDAO offeringDAO = new OfferingDAOImpl(JDBCDBUtil.getConnection());
            boolean exists = offeringDAO.checkOfferingExists(term, years, location, facultyId, courseId);
            if (exists) {
                request.setAttribute("message", "ERROR: The offering is exists.");
            } else {
                boolean status = offeringDAO.insert(term, years, location, courseCapacity, courseId, facultyId, staffId);
                if (status) {
                    request.setAttribute("message", "The offering is assigned successfully.");
                } else {
                    request.setAttribute("message", "ERROR: The offering is not assigned yet.");
                }
            }
            rd = request.getRequestDispatcher("staffwelcome.jsp");
            rd.forward(request, response);
        } else if ("addCourse".equals(requesttype)) {
            String courseIdStr = request.getParameter("courseId");
            if (courseIdStr == null || courseIdStr.isEmpty()) {
                msg = "ERROR: Please enter a course id";
            }

            String courseTitle = request.getParameter("courseTitle");
            if (courseTitle == null || courseTitle.isEmpty()) {
                msg += "<br/>ERROR: Please enter a course name";
            }

            String creditStr = request.getParameter("credit");
            if (creditStr == null || creditStr.isEmpty()) {
                msg += "<br/>ERROR: Please enter number of credit";
            }
            if (!msg.isEmpty()) {
                request.setAttribute("message", msg);
                rd = request.getRequestDispatcher("createStaffCourse.jsp");
                rd.forward(request, response);
                return;
            }
            try {
                int courseId = Integer.parseInt(courseIdStr);
                int credits = Integer.parseInt(creditStr);
                CourseDAO dao = new CourseDAOImpl(JDBCDBUtil.getConnection());
                if (courseId > 0) {
                    if (!dao.isCourseExist(courseId)) {
                        boolean status = dao.insertCourse(courseId, courseTitle, credits);

                        if (status) {
                            msg = "Course successfully added!";
                            request.setAttribute("message", msg);
                            rd = request.getRequestDispatcher("createStaffCourse.jsp");
                            rd.forward(request, response);

                        } else {
                            msg = "Failed to add course. Please try again";
                            request.setAttribute("message", msg);
                            rd = request.getRequestDispatcher("createStaffCourse.jsp");
                            rd.forward(request, response);
                        }
                    } else {
                        msg = "The course is exist. Please try again";
                        request.setAttribute("message", msg);
                        rd = request.getRequestDispatcher("createStaffCourse.jsp");
                        rd.forward(request, response);
                    }
                } else if (courseId < 1) {
                    msg = "ERROR: Course ID must be greater than 0";
                    request.setAttribute("message", msg);
                    rd = request.getRequestDispatcher("createStaffCourse.jsp");
                    rd.forward(request, response);
                }
            } catch (NumberFormatException e) {
                msg = "ERROR: Please enter a number for course id or credit";
                request.setAttribute("message", msg);
                rd = request.getRequestDispatcher("createStaffCourse.jsp");
                rd.forward(request, response);
            }

        }
    }

}
