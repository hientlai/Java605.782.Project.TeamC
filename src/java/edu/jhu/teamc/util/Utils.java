/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.jhu.teamc.util;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hien
 */
public class Utils {

    private static String getSyntaxErrorMessage(String userid, String password, String passwordRepeat) {
        String syntaxErrorMessage = getSyntaxErrorMessage(userid, password);
        if (password != null && !password.isEmpty() && password.length() == 8 && !password.equals(passwordRepeat)) {
            syntaxErrorMessage += "<li>Password(repeat) is not same with Password.</li>";
        }
        return syntaxErrorMessage;
    }

    private static String getSyntaxErrorMessage(String userid, String password) {
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
        } else if (userid.length() < 8) {
            syntaxErrorMessage += "<li>User Id has to be at least 8 characters.</li>";
        } else if (userid.length() >= 8 && userid.contains(" ")) {
            syntaxErrorMessage += "<li>User Id cannot contain 'space'.</li>";
        }
        if (password == null || password.isEmpty()) {
            syntaxErrorMessage += "<li>Password is empty.</li>";
        } else if (password.length() < 8) {
            syntaxErrorMessage += "<li>Password has to be at least 8 characters.</li>";
        } else if (password.length() >= 8 && password.contains(" ")) {
            syntaxErrorMessage += "<li>Password cannot contain 'space'.</li>";
        }
        return syntaxErrorMessage;
    }

    public static boolean validateUserNamePassword(String userid, String password, String passwordRepeat, HttpServletResponse response) throws IOException {
        String syntaxErrorMessage = getSyntaxErrorMessage(userid, password, passwordRepeat);
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
        } else {
            return true;
        }
    }

    public static boolean validateUserNamePassword(String userid, String password, HttpServletResponse response) throws IOException {

        String syntaxErrorMessage = getSyntaxErrorMessage(userid, password);
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
        } else {
            return true;
        }
    }
}
