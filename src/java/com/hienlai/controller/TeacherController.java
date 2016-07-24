package com.hienlai.controller;

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
import javax.websocket.server.PathParam;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hienlai.dao.EnrollmentDAO;
import com.hienlai.dao.EnrollmentDAOImpl;
import com.hienlai.dao.OfferingDAO;
import com.hienlai.dao.OfferingDAOImpl;
import com.hienlai.model.CoursesSupportBean;
import com.hienlai.model.StudentGradeBean;
import com.hienlai.model.User;
import com.hienlai.util.JDBCDBUtil;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * Done when updating grades
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EnrollmentDAO enrollDao = new EnrollmentDAOImpl(JDBCDBUtil.getConnection());
		Enumeration<String> names = request.getParameterNames();
		String name = "";
		String grade = "";
		String enrollId = "";
		boolean success;
		while (names.hasMoreElements()) {
			name = names.nextElement();
			enrollId = name;
			grade = request.getParameter(name);
			success = enrollDao.updateEnrollement(enrollId, grade);
			if (!success) {
				request.setAttribute("failed" , true );
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("grades.jsp");
		rd.forward(request, response);
		
		
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
			case "Logout": 
				session.invalidate();
				rd = request.getRequestDispatcher("login.html");
				rd.forward(request, response);
				break;
			case "EditGrade":
				session.setAttribute("edit", true);
			case "Grade":
				User user = (User) session.getAttribute("user");
				OfferingDAO offeringDao = new OfferingDAOImpl(JDBCDBUtil.getConnection());
				List<CoursesSupportBean> courses = offeringDao.retrieveCurrentOfferingByTeacherId(user.getId());
				System.out.println("courses np " + courses.size());
				session.setAttribute("courses", courses);
				rd = request.getRequestDispatcher("grades.jsp");
				rd.forward(request, response);
	            
	            break;
			default: return;
			}
		}
		// get list of students and grades
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
