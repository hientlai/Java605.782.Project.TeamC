<%--
  Created by IntelliJ IDEA.
  User: edwardkim
  Date: 7/23/16
  Time: 8:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            <%@include file="template/staffleft.jsp"%>
        </div>

        <div class ="main">
            <%
                String message = (String) request.getAttribute("message");
                if (message == null || message.equals("")) { %>
            <% } else if (message.contains("Course")) {%>
            <h3 style="color: green;"><b><%= message.toUpperCase()%></b></h3>
                    <% } else {%>
            <h3 style="color: red;"><b><%= message.toUpperCase()%></b></h3>
                    <% }
                    %>

            <h2>Override Course Capacity:</h2>

            <h4>Enter in the fields to override a course:</h4>

            <br/>

            <form id="courseForm" action="overrideStaffCourse" method="post">
                <h4>Please choose term, year, location and faculty</h4>

                <div class="row">
                    <div class="column one">Term: </div>
                    <div class="column two"> <select name="termoverride" id="termoverride">
                            <option>Spring</option>
                            <option>Summer</option>
                            <option>Fall</option>
                        </select></div>
                </div>
                <div class="row">
                    <div class="column one">Year: </div>
                    <div class="column two"><select name="yearoverride" id="yearoverride">
                            <option>2016</option>
                            <option>2017</option>
                        </select></div>
                </div>
                <div class="row">
                    <div class="column one">Location: </div>
                    <div class="column two"><select name="locationoverride" id="locationoverride">
                            <option>Online</option>
                            <option>Campus</option>
                        </select></div>
                </div>
                <div class="row">
                    <div class="column one">Faculty </div>
                    <div class="column two"><div id="facultiesdropdown"></div></div>
                </div>

                <br/>
                <h4>Available courses: </h4>
                <br/>
                <div id="coursesoverridedropdown"><select name='offeringid' id='courseoverride'></select></div>

                <br/>
                <h4>Please override the course capacity: </h4>
                <div class="row">
                    <div class="column one">Course capacity: </div>
                    <div class="column two">
                        <input name="courseCapacity"  id="courseCapacity" type="number" />
                    </div>
                </div>
                <div class="row">
                    <div class="column one"> </div>
                    <div class="column two">
                        <input type="submit" class="bold" name="requesttype" value="Override">
                    </div>
                </div>
                <br/>

            </form> 
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
