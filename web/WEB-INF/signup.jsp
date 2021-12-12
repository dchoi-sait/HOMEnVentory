<%-- 
    Document   : signup
    Created on : Nov 29, 2021, 1:49:47 AM
    Author     : 775262
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Sign Up</title>
       <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Kanit:wght@400;500;600&display=swap" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="css/signup.css" />
    </head>
    <body>
        <div class="container">
            <div class="signup-container">
                <div class="top-box">
                    <h2 style="margin: 0px; color: #7645D9">HOME<span style="color: #ff9200">n</span>Ventory</h2>
                    <h3>Create your Account</h3>
                </div>
                <div class="bottom-box">
                    <form method="POST" action="signup">

                        <div class="sp-name">
                            <div class="fname w50">
                                <lable>First name</lable><br>
                                <input class="textbox" type="text" name="signup-firstname" required>
                            </div>
                            <div class="lname w50">
                                <lable>Last name</lable><br>
                                <input class="textbox" type="text" name="signup-lastname" required>
                            </div>
                        </div>

                        <div class="sp-email">
                            <lable>Email</lable><br>
                            <input class="textbox" type="text" name="signup-email" required>
                        </div>

                        <div class="sp-password">
                            <div class="password">
                                <lable>Password</lable><br>
                                <input class="textbox" type="password" name="signup-password" required>
                            </div>
                        </div>

                        <div class="sp-confirm">
                            <a href="login">Sign in instead</a>
                            <input class="w50" type="submit" value="Next">
                        </div>
                    </form>

                </div>
            </div>
        </div>
    </body>
</html>
