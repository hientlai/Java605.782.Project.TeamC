
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
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
    </head>
    <body>
        <div class="top">
            <%@include file="template/header.jsp"%>
        </div>

        <div class="leftmenu">
            <%@include file="template/left.jsp"%>
        </div>

        <div class ="main">
            <c:choose>
                <c:when test="${sessionScope.isLogged == true}">

                    <h2> Welcome to the Student Registration Site  </h2>
                    <br/>
                    <br/>

                    <h4>Your grades: </h4>
                    <br/>
                    <table>
                        <tr>
                            <th>Course</th>
                            <th>Credits</th> 
                            <th>Grade</th>
                        </tr>

                        <c:forEach items="${courses}" var="course">
                            <tr>
                                <td>${course.courseId} - ${course.courseName}</td>
                                <td>${course.credits}</td>
                                <td>${course.grade}</td>
                            </tr>
                        </c:forEach>

                    </table>
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
            <%@include file="template/bottom.jsp"%>
        </div>
    </body>
    <script type="text/javascript" src="Scripts/myScript.js"></script>
</html>
