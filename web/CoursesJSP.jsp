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
            Project Team C
        </div>
    </body>
    <script type="text/javascript" src="Scripts/myScript.js"></script>
</html>
