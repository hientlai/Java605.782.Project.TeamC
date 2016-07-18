package com.hienlai.controller;

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
import javax.websocket.server.PathParam;

import com.google.gson.Gson;
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
		// TODO Auto-generated method stub
		processGet(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String selection = request.getParameter("course");
		String requestType = request.getParameter("requesttype");
		RequestDispatcher rd = null;
		// get list of courses
		if (selection == null) {
			switch (requestType) {
			case "Logout": 
				session.invalidate();
				rd = request.getRequestDispatcher("login.html");
				rd.forward(request, response);
				break;
			case "EditGrade":
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
			EnrollmentDAO enrollDao = new EnrollmentDAOImpl(JDBCDBUtil.getConnection());
			List<StudentGradeBean> grades = enrollDao.getStudentGrades(selection);
			String json = new Gson().toJson(grades);
			response.setContentType("application/json");
			response.getWriter().println(json);
		}
	}

}
