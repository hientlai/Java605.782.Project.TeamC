<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Teacher Grades</title>
<link href="Styles/main.css" rel="stylesheet" type="text/css" />
<script src="https://code.jquery.com/jquery-3.1.0.min.js"   integrity="sha256-cCueBR6CsyA4/9szpPfrX3s49M9vUU5BgtiJj06wt/s="   crossorigin="anonymous"></script>
</head>
<body>
<%@ page import="com.hienlai.model.CoursesSupportBean" %>
<%@ page import="java.util.List" %>
<div class="top">
            <header id="branding" role="banner">
                <img id="logo" src="Images/newlogo.png">	
            </header>
        </div>

        <div class="leftmenu">
            <br>
        </div>

        <div class ="main">
        <%List<CoursesSupportBean> courses = (List<CoursesSupportBean>) session.getAttribute("courses"); 
        	if (courses.size() < 1) {
        		%> 
        		<H1>You are not the teacher for any class at this time</H1>
        		<%
        	}
        	else {
        		%> 
        		Please select a course:
        		<select name="courseSelection" id="courseSelection"> <% 
        		for (CoursesSupportBean course: courses) { %>
        		
        		<option value=<%=course.getOffering_id()%>> <%= course.getCourse_name() %></option>
        		<%
        		}
        		%> 
        		</select>
        		<%
        	}
        		
        %>
        </div>

        <div class="rightmenu">
            <br>
        </div>

        <div class="bottom">
            Project Team C
        </div>
        
       <script type="text/javascript" src="Scripts/myScript.js"></script>
</body>
</html>