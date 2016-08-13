/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.jhu.teamc.controller;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import edu.jhu.teamc.dao.CourseDAO;
import edu.jhu.teamc.dao.CourseDAOImpl;
import edu.jhu.teamc.dao.EnrollmentDAO;
import edu.jhu.teamc.dao.EnrollmentDAOImpl;
import edu.jhu.teamc.dao.OfferingDAO;
import edu.jhu.teamc.dao.OfferingDAOImpl;
import edu.jhu.teamc.model.CoursesSupportBean;
import edu.jhu.teamc.util.JDBCDBUtil;
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

/**
 *
 * @author Hien
 */
@WebServlet(name = "Student_servlet", urlPatterns = {"/Student_servlet"})
public class StudentController extends HttpServlet {

    OfferingDAO offeringDao = new OfferingDAOImpl(JDBCDBUtil.getConnection());

    CourseDAO courseDao = new CourseDAOImpl(JDBCDBUtil.getConnection());

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
        String requestType = request.getParameter("requesttype");
        OfferingDAO dao = new OfferingDAOImpl(JDBCDBUtil.getConnection());
        EnrollmentDAO enrDao = new EnrollmentDAOImpl(JDBCDBUtil.getConnection());
        int studentId = Integer.parseInt((String) session.getAttribute("student_id"));
        System.out.println("request type " + requestType);
        if ("CoursesList".equals(requestType)) {
            //return json string of list courses for ajax fuction
            System.out.println("CourseList");
            try {
                RequestDispatcher rd = request.getRequestDispatcher("CoursesJSP.jsp");
                String term = request.getParameter("term");
                String year = request.getParameter("year");
                List<CoursesSupportBean> courses = offeringDao.retrieveOfferingByTermYear(term, year);
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
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if ("Enroll".equals(requestType)) {
            //Student enroll a course
            int offeringId = Integer.parseInt(request.getParameter("course"));
            int numberStudentRegistered = 0;

            CoursesSupportBean course = dao.retrieveCourse(offeringId);
            numberStudentRegistered = dao.retrieveStudentsRegistered(offeringId);
            if (numberStudentRegistered < course.getCourseCapacity()) {
                if (enrDao.isEnrolled(studentId, offeringId)) {
                    request.setAttribute("error", "You already enroll this course.");
                } else {
                    dao.updateNumberStudentsRegistered(offeringId, numberStudentRegistered + 1);
                    enrDao.insertEnrollment("Active", (String) session.getAttribute("student_id"), offeringId + "");
                    request.setAttribute("message", "You have been registered to " + offeringId + " " + course.getCourseName());
                }
            } else {
                request.setAttribute("error", "Sorry, the registration to this course has been closed based on availability");
            }

            RequestDispatcher rd = request.getRequestDispatcher("registrarcourse");
            rd.forward(request, response);

        } else if ("CoursesDrop".equals(requestType) || "ViewGrade".equals(requestType)) {
            List<CoursesSupportBean> courses = enrDao.getEnrollments(studentId);
            request.setAttribute("courses", courses);
            if ("CoursesDrop".equals(requestType)) {
                //show list courses that allows to drop
                RequestDispatcher rd = request.getRequestDispatcher("dropcourse.jsp");
                rd.forward(request, response);
            } else if ("ViewGrade".equals(requestType)) {
                //show grades of courses
                RequestDispatcher rd = request.getRequestDispatcher("viewgrades.jsp");
                rd.forward(request, response);
            }
        } else if ("Drop".equals(requestType)) {
            //Drop class
            int enrollmentId = Integer.parseInt(request.getParameter("enrollmentId"));
            int offeringId = enrDao.getEnrollment(enrollmentId).getOfferingId();

            //deduct 1 from the number of student enrollment
            int numberOfStudentEnrollment = offeringDao.retrieveStudentsRegistered(offeringId);
            offeringDao.updateNumberStudentsRegistered(offeringId, numberOfStudentEnrollment - 1);

            //Update the status of the record enrollment to Drop from enrollment table
            enrDao.updateStatusEnrollment(enrollmentId, "Drop");

            //get new list of enrollment classes
            List<CoursesSupportBean> courses = enrDao.getEnrollments(studentId);
            request.setAttribute("courses", courses);

            RequestDispatcher rd = request.getRequestDispatcher("dropcourse.jsp");
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
}
