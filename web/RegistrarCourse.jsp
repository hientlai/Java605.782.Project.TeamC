<%-- 
    Document   : RegistrarCourse
    Created on : Jun 10, 2016, 3:01:02 PM
    Author     : Hien
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%@page import="java.com.hienlai.util.JDBCDBUtil"%> --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Team C's Project</title>
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
            <c:choose>
                <c:when test="${sessionScope.isLogged == true}">
                    <form id="registrarCourseForm" action="RegistrationController_servlet" method="post">
                        <c:if test="${message !=null }"><h3><font color='blue'>${message}</font></h3></c:if>
                        <c:if test="${error !=null }"><h3><font color='red'>${error}</font></h3></c:if>
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
</html>