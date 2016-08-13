<%--
  Created by IntelliJ IDEA.
  User: edwardkim
  Date: 7/23/16
  Time: 11:19 PM
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
            <div id="tabs">
                <ul>
                    <li><a href="#tabs-1">Add a new course</a></li>
                    <li><a href="#tabs-2">Add a new offering</a></li>
                </ul>
                <div id="tabs-1">
                    <%
                        String message = (String) request.getAttribute("message");
                        if (message != null) {
                            if (!message.contains("ERROR")) {%>
                                <h3 style="color: green;"><b><%= message.toUpperCase()%></b></h3>
                            <% } else {%>
                                <h3 style="color: red;"><b><%= message.toUpperCase()%></b></h3>
                            <% }
                        }
                    %>
                    <h2>Add a new course</h2>

                    <h4>Enter in the fields to register a new course:</h4>

                    <form action="createStaffCourse" method="post">
                        <input type="hidden" name="requesttype" value="addCourse">
                        <table>
                            <tr>
                                <td>Course Id</td>
                                <td><input type="text" name="courseId"/></td>
                            </tr>
                            <tr>
                                <td>Course Title</td>
                                <td><input type="text" name="courseTitle" /></td>
                            </tr>
                            <tr>
                                <td>Course Credit</td>
                                <td><input type="number" name="credit" min="0" max="3"/>
                                </td>
                            </tr>
                        </table>

                        <br/><br/>
                        <input type="submit" value="Submit"/>
                    </form>

                </div>
                <div id="tabs-2">

                    <br/>

                    <form id="courseForm" action="createStaffCourse" method="post">
                        <h4>Please choose term, year, location and course capacity</h4>

                        <div class="row">
                            <div class="column one">Term: </div>
                            <div class="column two"> <select name="terms" id="terms">
                                    <option>Spring</option>
                                    <option>Summer</option>
                                    <option>Fall</option>
                                </select></div>
                        </div>
                        <div class="row">
                            <div class="column one">Year: </div>
                            <div class="column two"><select name="years" id="years">
                                    <option>2016</option>
                                    <option>2017</option>
                                </select></div>
                        </div>
                        <div class="row">
                            <div class="column one">Location: </div>
                            <div class="column two"><select name="locations" id="locations">
                                    <option>Online</option>
                                    <option>Campus</option>
                                </select></div>
                        </div>
                        <div class="row">
                            <div class="column one">Course capacity: </div>
                            <div class="column two">
                                <input name="courseCapacity" type="number" value="20"/>
                            </div>
                        </div>
                        <br/>
                        <h4>Available courses: </h4>
                        <br/>
                        <div id="coursesdropdown"></div>
                        <br/>
                        <h4>Faculty</h4>
                        <div id="facultiesdropdown"></div>
                        <br/>

                        <br/>

                        <input type="submit" class="bold" name="requesttype" value="Assign">

                        <br/>
                        <br/>
                        <br/>   
                    </form> 
                </div>

            </div>

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
