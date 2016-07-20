<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Team C's Project</title>
        <link href="Styles/main.css" rel="stylesheet" type="text/css" />
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
        <script type="text/javascript" src="Scripts/myScript.js"></script>
    </head>
    <body>
        <div class="top">
            <%@include file="template/header.jsp"%>
        </div>

        <div class="leftmenu">

        </div>

        <div class ="main">
            <form id="loginForm" action="RegistrationController_servlet" method="post">
                <h2> Welcome to the University Registration System  </h2>
                <h4>If you already have an account, please log in</h4>
                <table >
                    <tr>
                        <td><b><u>U</u>ser Id: </b></td>
                        <td><input type="text" accesskey="u" name="userid_login" size="8"></td>
                    </tr>
                    <tr>
                        <td><b><u>P</u>assword: </b></td>
                        <td><input type="password" accesskey="p" name="password_login" size="8">
                            <a href="">Forgot your password?</a></td>
                    </tr>
                </table> 
                <br/>
                <input type="submit" class="bold" name="requesttype" value="Submit">
                <input type="reset" class="bold" name="resetbtn" value="Reset">
                <br/>
                <br/>
                <br/>

                <h4>For new users, please register first</h4>
                <button type="submit" style="font-weight:bold" name="requesttype" value="Register">Register</button>
            </form>    
        </div>

        <div class="rightmenu">
            <br>
        </div>

        <div class="bottom">
            <%@include file="template/bottom.jsp"%>
        </div>
    </body>
</html>


