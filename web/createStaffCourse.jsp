<%--
  Created by IntelliJ IDEA.
  User: edwardkim
  Date: 7/23/16
  Time: 11:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="Styles/main.css" rel="stylesheet" type="text/css" />
    <title>Staff | Create Course</title>
</head>
<body>

    <div class="top">
        <%@include file="template/header.jsp"%>
    </div>

    <div class="leftmenu">
        <br>
    </div>

    <div class ="main">
        <h2>Course Add Template:</h2>

        <h4>Enter in the fields to register a new course:</h4>

        <form action="/createStaffCourse">
            Enter Course Id:
            <input type="text" name="courseId"/>
            <br/>
            Enter Course Title:
            <input type="text" name="courseTitle" />
            <br/>
            Enter Course Credit:
            <input type="number" name="credit" min="0" max="3"/>
            <br/><br/>
            <input type="submit" value="Submit"/>
        </form>

        <%-- Redirect to main staff page --%>
        <input type="button" value="Main Staff Page" onclick="location.href='${pageContext.request.contextPath}/mainStaffPage.jsp';"/>
    </div>

    <div class="rightmenu">
        <br>
    </div>

    <div class="bottom">
        <%@include file="template/bottom.jsp"%>
    </div>
</body>
</html>
