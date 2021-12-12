<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>HOMEnVentory</title>
       <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Kanit:wght@400;500;600&display=swap" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="css/login.css" />
    </head>
    <body>
        <div class="container">
            <div class="login-container">
                <div class="top-box">
                    <h2 style="margin: 0px; color: #7645D9">HOME<span style="color: #ff9200">n</span>Ventory</h2>
                    <h3 class="z-margin">Login</h3>
                </div>
                <div class="bottom-box">
                    <form action="login" method="post">
                        <label>Email: </label><br><input class="textbox" type="text" name="email" required><br>
                        <label>Password:</label><br> <input class="textbox" type="password" name="password" required><br>
                        <br>
                        <input type="submit" value="Log in">
                    </form>
                    <c:if test="${message != null}">
                        <p> ${message}<p>
                        </c:if>
                        <div style="text-align: center"><p>Don't have an account? <a href="signup">Sign up</a></p></div>
                </div>
            </div>
        </div>
    </body>
</html>
