<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Team C's Project</title>
        <link href="../Styles/main.css" rel="stylesheet" type="text/css" />
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
    </head>
    <body>
        <div class="top">
            <%@include file="../template/header.jsp"%>
        </div>

        <div class="leftmenu">

        </div>

        <div class ="main">
            <form id="registerFormA" action="../RegistrationController_servlet" method="post">
                <h2> Registration Form A  </h2>

                <table >
                    <tr>
                        <td><b><u>R</u>ole: </b></td>
                        <td><input type="radio" checked name="role" value="Student"> Student<br>
                            <input type="radio" name="role" value="Faculty"> Faculty<br>
                            <input type="radio" name="role" value="Staff"> Staff</td>
                    </tr>
                    <tr>
                        <td><b><u>U</u>ser Id: </b></td>
                        <td><input type="text" accesskey="u" name="userid_reg"></td>
                    </tr>
                    <tr>
                        <td><b><u>P</u>assword: </b></td>
                        <td><input type="password" accesskey="p" name="password_reg"></td>
                    </tr>
                    <tr>
                        <td><b><u>P</u>assword (repeat): </b></td>
                        <td><input type="password" accesskey="p" name="password_rpt"></td>
                    </tr>
                    <tr>
                        <td><b><u>F</u>irst name: </b></td>
                        <td><input type="text" accesskey="f" name="firstname_reg"></td>
                    </tr>
                    <tr>
                        <td><b><u>L</u>ast name: </b></td>
                        <td><input type="text" accesskey="l" name="lastname_reg"></td>
                    </tr>
                    <tr>
                        <td><b><u>S</u>ocial Security Number: </b></td>
                        <td><input type="text" accesskey="s" name="ssn_1" maxlength=3 size=3> -
                            <input type="text" name="ssn_2" maxlength=2 size=2> -
                            <input type="text" name="ssn_3" maxlength=4 size=4> 
                        </td>
                    </tr>
                    <tr>
                        <td><b><u>E</u>mail: </b></td>
                        <td><input type="text" accesskey="p" name="email_reg"></td>
                    </tr>
                </table> 
                <br/>
                <input type="submit" class="bold" name="requesttype" value="Continue">
                <br/>
                <br/>
                <br/>
            </form>
        </div>

        <div class="rightmenu">
            <br>
        </div>

        <div class="bottom">
            <%@include file="../template/bottom.jsp"%>
        </div>
    </body>
    <script type="text/javascript" src="../Scripts/myScript.js"></script>  
</html>



