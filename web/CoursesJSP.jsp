<%-- 
    Document   : CoursesJSP
    Created on : Jun 9, 2016, 11:36:52 AM
    Author     : Hien
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                    <form id="courseForm" action="RegistrationController_servlet" method="post">
                        <h2> Welcome to the Student Registration Site  </h2>
                        <br/>
                        <br/>
                        <h4>List of available courses for current semester</h4>
                        <select name="course">
                            <c:forEach items="${courses}" var="course">
                                <option value="${course.courseId}">${course.courseId} - ${course.course_name}</option>
                            </c:forEach>
                        </select>    
                        <br/>
                        <br/>
                        <br/>

                        <input type="submit" class="bold" name="requesttype" value="Add">

                        <br/>
                        <br/>
                        <br/>   
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
            Hien Lai (hlai12)
        </div>
    </body>
</html>
