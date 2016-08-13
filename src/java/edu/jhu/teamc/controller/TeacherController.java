package edu.jhu.teamc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import edu.jhu.teamc.dao.EnrollmentDAO;
import edu.jhu.teamc.dao.EnrollmentDAOImpl;
import edu.jhu.teamc.dao.OfferingDAO;
import edu.jhu.teamc.dao.OfferingDAOImpl;
import edu.jhu.teamc.model.CoursesSupportBean;
import edu.jhu.teamc.model.StudentGradeBean;
import edu.jhu.teamc.model.User;
import edu.jhu.teamc.util.JDBCDBUtil;

/**
 * Servlet implementation class TeacherController
 */
@WebServlet("/TeacherController")
public class TeacherController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeacherController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processGet(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     *
     * Done when updating grades
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);

    }

    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String selection = request.getParameter("course");
        String requestType = request.getParameter("requesttype");
        RequestDispatcher rd = null;
        // get list of courses
        if (selection == null) {
            session.setAttribute("edit", false);
            switch (requestType) {
                case "FacultyHome":
                    rd = request.getRequestDispatcher("facultywelcome.jsp");
                    rd.forward(request, response);
                case "Logout":
                    session.invalidate();
                    rd = request.getRequestDispatcher("login.jsp");
                    rd.forward(request, response);
                    break;
                case "EditGrade":
                    session.setAttribute("edit", true);
                case "Grade":
                    User user = (User) session.getAttribute("facultyUser");
                    OfferingDAO offeringDao = new OfferingDAOImpl(JDBCDBUtil.getConnection());
                    List<CoursesSupportBean> courses = offeringDao.retrieveCurrentOfferingByTeacherId(user.getId());
                    System.out.println("courses np " + courses.size());
                    session.setAttribute("courses", courses);
                    rd = request.getRequestDispatcher("grades.jsp");
                    rd.forward(request, response);

                    break;
                case "UpdateGrade":
                    EnrollmentDAO enrollDao = new EnrollmentDAOImpl(JDBCDBUtil.getConnection());
                    Enumeration<String> names = request.getParameterNames();
                    String name = "";
                    String grade = "";
                    String enrollId = "";
                    boolean success = false;
                    while (names.hasMoreElements()) {
                        name = names.nextElement();
                        if (!"requesttype".equals(name)) {
                            enrollId = name;
                            grade = request.getParameter(name);
                            success = enrollDao.updateEnrollementGrade(enrollId, grade);
                            if (!success) {
                                request.setAttribute("failed", true);
                            }
                        }
                    }
                    rd = request.getRequestDispatcher("grades.jsp");
                    rd.forward(request, response);
                default:
                    return;
            }
        } // get list of students and grades
        else {
            Gson gson = new Gson();
            EnrollmentDAO enrollDao = new EnrollmentDAOImpl(JDBCDBUtil.getConnection());
            List<StudentGradeBean> grades = enrollDao.getStudentGrades(selection);
            JsonObject jsonObj = new JsonObject();
            Boolean isEdit = (Boolean) session.getAttribute("edit");
            if (isEdit == null) {
                isEdit = false;
            }
            jsonObj.addProperty("edit", isEdit);
            jsonObj.add("grades", gson.toJsonTree(grades));
            String json = new Gson().toJson(jsonObj);
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.println(json);
            out.close();
        }
    }

}
