<%-- 
    Document   : RegistrarCourse
    Created on : Jun 10, 2016, 3:01:02 PM
    Author     : Hien
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.hienlai.util.JDBCDBUtil"%>
<%@page import="com.hienlai.dao.RegistrarDAOImpl"%>
<%@page import="com.hienlai.dao.RegistrarDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Team C's Project</title>
        <link href="Styles/main.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <div class="top">
            <header id="branding" role="banner">
                <img id="logo" src="Images/newlogo.png">	
            </header>
        </div>

        <div class="leftmenu">
            <br>
        </div>

        <div class ="main">
            <c:choose>
                <c:when test="${sessionScope.isLogged == true}">
                    <form id="registrarCourseForm" action="RegistrationController_servlet" method="post">
                        <%
                            RegistrarDAO dao = new RegistrarDAOImpl(JDBCDBUtil.getConnection());
                            int numberStudentsRegistered = (Integer) request.getAttribute("numberStudentRegistered");
                            int courseId = (Integer) request.getAttribute("courseId");
                            String courseName = (String) request.getAttribute("courseName");
                            if(numberStudentsRegistered == 0){
                                dao.insertStudentRegistered(courseId, 1);
                                out.print("<h3><font color='blue'>You have been registered to " + courseId + " " + courseName + "</font></h3>");
                            }else if (numberStudentsRegistered < Integer.parseInt(config.getInitParameter("coursecapacity"))) {
                                numberStudentsRegistered++;
                                out.print("<h3><font color='blue'>You have been registered to " + courseId + " " + courseName + "</font></h3>");
                                dao.updateNumberStudentsRegistered(courseId, numberStudentsRegistered);
                            } else {
                                out.print(" <h3><font color='red'>Sorry, the registration to this course has been closed based on availability</font></h3>");
                            }
                        %>


                    </form> 
                </c:when>
                <c:otherwise>
                    <h2>Please log in.</h2>
                </c:otherwise>
            </c:choose>

        </div>

        <div class="rightmenu">
            <br>
        </div>

        <div class="bottom">
            Project Team C
        </div>
    </body>
</html>