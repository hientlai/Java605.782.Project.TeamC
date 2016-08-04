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
            <%@include file="template/studentleft.jsp"%>
        </div>

        <div class ="main">
            <c:choose>
                <c:when test="${sessionScope.isLogged == true}">

                    <h2> Welcome to the Student Registration Site  </h2>
                    <br/>
                    <br/>
                    <h4>Please choose term and year.</h4>
                    <form id="courseForm">
                        <input type="hidden" name="requesttype" value="CoursesList">
                        <div class="row">
                            <div class="column one">Term: </div>
                            <div class="column two"> <select name="term" id="term">
                                    <option>Spring</option>
                                    <option>Summer</option>
                                    <option>Fall</option>
                                </select></div>
                        </div>
                        <div class="row">
                            <div class="column one">Year: </div>
                            <div class="column two"><select name="year" id="year">
                                    <option>2016</option>
                                    <option>2017</option>
                                </select></div>
                        </div>
                    </form>
                    <br/>
                    <h4>Available courses: </h4>
                    <br/>
                    <form id="courseForm" action="RegistrationController_servlet" method="post">
                        <div id="coursedropdown"></div>

                        <br/>
                        <br/>
                        <br/>

                        <input type="submit" class="bold" name="requesttype" value="Enroll">

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
            <%@include file="template/bottom.jsp"%>
        </div>
    </body>
    <script type="text/javascript" src="Scripts/myScript.js"></script>
</html>
