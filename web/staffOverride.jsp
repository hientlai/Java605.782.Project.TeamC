<%--
  Created by IntelliJ IDEA.
  User: edwardkim
  Date: 7/23/16
  Time: 8:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Staff | Override Courses</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="Styles/main.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <div class="top">
        <%@include file="template/header.jsp"%>
    </div>

    <div class="leftmenu">
        <br>
    </div>

    <div class ="main">
        <%
            String message = (String) session.getAttribute("staffMsg");
            if (message == null || message.equals("")) { %>
        <% } else if (message.contains("Course")) { %>
        <h3 style="color: greenyellow;"><b><%= message.toUpperCase() %></b></h3>
        <% } else { %>
        <h3 style="color: red;"><b><%= message.toUpperCase() %></b></h3>
        <% }
        %>

        <h2>Override Course Capacity:</h2>

        <h4>Enter in the fields to override a course:</h4>

        <form action="/overrideStaffCourse">
            <%-- COURSE ID LOOKUP --%>
            Enter Course Id to Override:
            <input type="text" name="courseId"/>
            <br/>

            Enter Course Capacity # to Override:
            <input type="number" min="0" name="courseOverride"/>
            <br/><br/>
            <input type="submit" value="Submit"/>
        </form>

        <%-- Redirect to main staff page --%>
        <input type="button" value="Main Staff Page" onclick="location.href='${pageContext.request.contextPath}/validationFail.jsp';"/>
    </div>

    <div class="rightmenu">
        <br>
    </div>

    <div class="bottom">
        Project Team C
    </div>
</body>
</html>
