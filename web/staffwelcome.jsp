<%-- 
    Document   : studentwelcome
    Created on : Jul 20, 2016, 4:04:03 PM
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
            <%@include file="template/staffleft.jsp"%>
        </div>

        <div class ="main">
            <h2> Welcome to the site, <%= session.getAttribute("userName")%>  </h2>
            <%
                String message = (String) request.getAttribute("message");
                if (message == null || message.equals("")) { %>
            <% } else if (!message.contains("ERROR")) {%>
            <h3 style="color: green;"><b><%= message.toUpperCase()%></b></h3>
                    <% } else {%>
            <h3 style="color: red;"><b><%= message.toUpperCase()%></b></h3>
                    <% }
                    %>
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

