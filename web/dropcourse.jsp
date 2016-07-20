
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
        <script src="https://code.jquery.com/jquery-3.1.0.min.js"   integrity="sha256-cCueBR6CsyA4/9szpPfrX3s49M9vUU5BgtiJj06wt/s="   crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
        <script type="text/javascript" src="Scripts/myScript.js"></script>
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
                    <h2> Welcome to the Student Registration Site  </h2>
                    <br/>
                    <br/>
                    <br/>
                    <table>
                        <tr>
                            <th>Course</th>
                            <th>Enrollment date</th> 
                            <th>Drop</th>
                        </tr>
                        <c:forEach items="${courses}" var="course">
                            <tr>

                                <td>${course.courseId} - ${course.courseName}</td>
                                <td>${course.enrollmentDate}</td>
                                <td><form id="courseForm" action="Student_servlet" method="post">
                                        <input type="hidden" name="enrollmentId" value="${course.enrollmentId}">
                                        <input type="hidden" name="requesttype" value="Drop">
                                        <input type="button" value="Drop" id="dropbutton" >
                                    </form></td>

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
            Project Team C
        </div>
    </body>
    <div id="dialog-confirm" title="Drop the course?">
        <p><span class="ui-icon ui-icon-alert" style="float:left; margin:12px 12px 20px 0;"></span>This course will be dropped. Are you sure?</p>
    </div>
    <script type="text/javascript" src="Scripts/myScript.js"></script>
</html>
