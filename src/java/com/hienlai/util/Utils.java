/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hienlai.util;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hien
 */
public class Utils {
    public static void showWelcomePage(String userName, HttpServletResponse response) throws IOException {
        String welcomePage = "<!DOCTYPE html>\n"
                + "<html>\n"
                + "    <head>\n"
                + "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"
                + "        <title> Hien Lai's Assignment 3 </title>\n"
                + "        <link href=\"Styles/main.css\" rel=\"stylesheet\" type=\"text/css\" />\n"
                + "    </head>\n"
                + "    <body>\n"
                + "        <div class=\"top\">\n"
                + "            <header id=\"branding\" role=\"banner\">\n"
                + "                <img id=\"logo\" src=\"Images/newlogo.png\">	\n"
                + "            </header>\n"
                + "        </div>\n"
                + "\n"
                + "        <div class=\"leftmenu\">\n"
                + "            <br>\n"
                + "        </div>\n"
                + "\n"
                + "        <div class =\"main\">\n"
                + "            <form id=\"welcomeForm\" action=\"RegistrationController_servlet\" method=\"post\">\n"
                + "                <h2> Welcome to the site,  " + userName + "</h2>\n"
                + "                \n"
                + "                <h4>Select your next action:</h4>\n"
                + "                <input type=\"radio\" name=\"requesttype\" accesskey=\"r\" value=\"CoursesRegister\" checked> <u>R</u>egister for the course<br/>\n"
                + "                <input type=\"radio\" name=\"requesttype\" accesskey=\"o\" value=\"Logout\"> L<u>o</u>gout \n"
                + "                <br/>\n"
                + "                <br/>\n"
                + "                <button type=\"submit\" name=\"submitbtn\" value=\"courseRegister\"><b>Submit</b></button>\n"
                + "            </form>    \n"
                + "        </div>\n"
                + "\n"
                + "        <div class=\"rightmenu\">\n"
                + "            <br>\n"
                + "        </div>\n"
                + "\n"
                + "        <div class=\"bottom\">\n"
                + "            Hien Lai (hlai12)\n"
                + "        </div>\n"
                + "    </body>\n"
                + "</html>\n"
                + "\n";
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println(welcomePage);
            
        }
    }
    private static String getSyntaxErrorMessage(String userid, String password,String passwordRepeat){
        String syntaxErrorMessage = getSyntaxErrorMessage(userid,password);
        if(password != null && !password.isEmpty() && password.length() == 8 && !password.equals(passwordRepeat))
            syntaxErrorMessage += "<li>Password(repeat) is not same with Password.</li>";
        return syntaxErrorMessage;
    }
    private static String getSyntaxErrorMessage(String userid, String password){
        String syntaxErrorMessage = "";      
        //Validate userid and password
        if (userid != null) {
            userid = userid.trim();
        }
        if (password != null) {
            password = password.trim();
        }
        if (userid == null || userid.isEmpty()) {
            syntaxErrorMessage += "<li>User Id is empty.</li>";
        } else if (userid.length() != 8) {
            syntaxErrorMessage += "<li>User Id has to be 8 characters.</li>";
        } else if (userid.length() == 8 && userid.contains(" ")) {
            syntaxErrorMessage += "<li>User Id cannot contain 'space'.</li>";
        }
        if (password == null || password.isEmpty()) {
            syntaxErrorMessage += "<li>Password is empty.</li>";
        } else if (password.length() != 8) {
            syntaxErrorMessage += "<li>Password has to be 8 characters.</li>";
        } else if (password.length() == 8 && password.contains(" ")) {
            syntaxErrorMessage += "<li>Password cannot contain 'space'.</li>";
        }
        return syntaxErrorMessage;
    }
    public static boolean validateUserNamePassword(String userid, String password, String passwordRepeat, HttpServletResponse response) throws IOException{
        String syntaxErrorMessage = getSyntaxErrorMessage(userid,password, passwordRepeat);
        if (!syntaxErrorMessage.isEmpty()) {
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet Login Fail</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h2><font color=\"red\">You have entered invalid data. Please try to login again.</font></h2>");
                out.println("<ul>" + syntaxErrorMessage + "</ul>");
                out.println("</body>");
                out.println("</html>");
            }
            return false;
        }else
            return true;
    }
    public static boolean validateUserNamePassword(String userid, String password, HttpServletResponse response) throws IOException{
        
        String syntaxErrorMessage = getSyntaxErrorMessage(userid,password);
        if (!syntaxErrorMessage.isEmpty()) {
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet Login Fail</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h2><font color=\"red\">You have entered invalid data. Please try to login again.</font></h2>");
                out.println("<ul>" + syntaxErrorMessage + "</ul>");
                out.println("</body>");
                out.println("</html>");
            }
            return false;
        }else
            return true;
    }
}
