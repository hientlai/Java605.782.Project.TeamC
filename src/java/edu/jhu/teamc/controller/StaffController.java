package edu.jhu.teamc.controller;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import edu.jhu.teamc.dao.CourseDAO;
import edu.jhu.teamc.dao.CourseDAOImpl;
import edu.jhu.teamc.dao.FacultyDAO;
import edu.jhu.teamc.dao.FacultyDAOImpl;
import edu.jhu.teamc.dao.OfferingDAO;
import edu.jhu.teamc.dao.OfferingDAOImpl;
import edu.jhu.teamc.model.CoursesSupportBean;
import edu.jhu.teamc.model.FacultySupportBean;
import edu.jhu.teamc.util.JDBCDBUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
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
        OfferingDAO offeringDao = new OfferingDAOImpl(JDBCDBUtil.getConnection());
        switch (requestType) {
            case "StaffHome":
                rd = request.getRequestDispatcher("staffwelcome.jsp");
                rd.forward(request, response);
            case "CreateCourse":
                System.out.println("Create course selected...");
                rd = request.getRequestDispatcher("createStaffCourse.jsp");
                session.setAttribute("staffRequestType", requestType);
                rd.forward(request, response);
                break;
            case "OverrideCourses":
                System.out.println("Override course selected...");
                session.setAttribute("overrideRequestType", requestType);
                rd = request.getRequestDispatcher("staffOverride.jsp");
                rd.forward(request, response);
                break;
            case "Logout":
                session.invalidate();
                rd = request.getRequestDispatcher("login.jsp");
                rd.forward(request, response);
                break;
            case "CourseCapacity":
                String offeringId = request.getParameter("offeringId");
                CoursesSupportBean course = offeringDao.retrieveCourse(Integer.parseInt(offeringId));
                System.out.println("course " + course.getCourseCapacity());
                try {
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

                    if (course == null) {
                        myObj.addProperty("success", false);
                    } else {
                        myObj.addProperty("success", true);
                        JsonElement coursesObj = gson.toJsonTree(course.getCourseCapacity());
                        myObj.add("coursecapacity", coursesObj);

                    }
                    out.println(myObj.toString());
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "CoursesList":
                CourseDAO courseDao = new CourseDAOImpl(JDBCDBUtil.getConnection());
                //return json string of list courses for ajax fuction
                System.out.println("CoursesList");
                try {
                    List<CoursesSupportBean> courses = courseDao.retrieveCourses();
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
            case "FacultiesList":
                //return json string of list courses for ajax fuction
                System.out.println("FacultiesList");
                FacultyDAO facultyDao = new FacultyDAOImpl(JDBCDBUtil.getConnection());
                try {

                    List<FacultySupportBean> faculties = facultyDao.retrieveFaculties();
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

                    if (faculties == null || faculties.isEmpty()) {
                        myObj.addProperty("success", false);
                    } else {
                        myObj.addProperty("success", true);
                        JsonElement facultiesObj = gson.toJsonTree(faculties);
                        myObj.add("faculties", facultiesObj);

                    }
                    out.println(myObj.toString());
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            case "OfferingList":

                //return json string of list courses for ajax fuction
                System.out.println("CoursesList");

                String term = request.getParameter("term");
                String year = request.getParameter("year");
                String location = request.getParameter("location");
                String facultyid = request.getParameter("facultyid");
                try {
                    List<CoursesSupportBean> courses = offeringDao.retrieveOfferingByTermYearLocationFaculty(term, year, location, facultyid);
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
            default:
                return;
        }

    }

}
