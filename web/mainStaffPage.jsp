<%--
  Created by IntelliJ IDEA.
  User: edwardkim
  Date: 7/24/16
  Time: 8:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main Staff Page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
            <%
                String message = (String) session.getAttribute("staffMsg");
                if (message == null || message.equals("")) { %>
            <% } else if (message.contains("Course")) { %>
            <h3 style="color: greenyellow;"><b><%= message.toUpperCase() %></b></h3>
            <% } else { %>
            <h3 style="color: red;"><b><%= message.toUpperCase() %></b></h3>
            <% }
            %>

            <form id="welcomeForm" action="/staffController" method="post">
                <h4>Select your next action:</h4>
                    <input type="radio" name="requesttype" accesskey="r" value="CreateCourse" checked> <u>C</u>create course<br/>
                    <input type="radio" name="requesttype" accesskey="r" value="OverrideCourses" checked> <u>O</u>verride course capacity<br/>
                    <input type="radio" name="requesttype" accesskey="o" value="Logout"> L<u>o</u>gout
                    <br/>
                    <br/>
                    <button type="submit" name="submitbtn" value="courseRegister"><b>Submit</b></button>
            </form>
        </div>

        <div class="rightmenu">
            <br>
        </div>

        <div class="bottom">
            Project Team C
        </div>
</body>
</html>
