<%-- 
    Document   : account
    Created on : Dec 4, 2021, 10:29:32 PM
    Author     : 775262
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <script>

            function toggleMenu() {
                const el = document.querySelector(".dropdown-content");
                el.style.display = (el.style.display === "none") ? "block" : "none";
            }

        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>HOMEnVentory</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Kanit:wght@400;500;600&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="css/account.css" />
        <link rel="stylesheet" type="text/css" href="css/nav.css" />
    </head>
    <body>

        <div class="nav-container">
            <nav class="nav">
                <div class="nav-left">
                    <div class="nav-menu" id="title">HOME<span style="color: #ff9200">n</span>Ventory</div>
                    <div class="nav-menu nav-hide" id="inventory"><a href="inventory">Inventory</a></div>
                    <c:if test="${user.role == 3 || user.role == 1}">
                        <div class="nav-menu nav-hide" id="admin"><a href="admin">Admin</a></div>
                        <div class="nav-menu nav-hide" id="category"><a href="category">Category</a></div>
                    </c:if>
                </div>
                <div class="nav-right">
                    <div class="nav-menu nav-hide" id="logout"><a href="login">Logout</a></div>
                    <div id="account">
                        <a class="button" href="account">${user.first_name} ${user.last_name}</a>
                    </div>
                    <div class="dropdown" onclick="toggleMenu()">
                        <i class="fa fa-bars dropbtn"></i>
                        <div class="dropdown-content" style="display:none">
                            <a href="inventory">Inventory</a>
                            <c:if test="${user.role == 3 || user.role == 1}">
                                <a href="admin">Admin</a>
                                <a href="category">Category</a>
                            </c:if>
                            <a href="account">Account</a>
                            <a href="login">Logout</a>
                        </div>
                    </div>
                </div>
            </nav>
        </div>


        <div class="banner">
            <div class="banner-container" style="max-width: 95%; width: 500px;">
                <h1 style="margin-bottom: 2px; margin-top: 0px;">Account</h1>
                <h2 style="margin-top: 0px;">Manage HOMEnVentory Account</h2>
            </div>
        </div>

        <div class="container">
            <div class="signup-container">
                <div class="top-box">
                    <h2>Account</h2>
                    <h3>Your Profile</h3>
                </div>
                <div class="bottom-box">
                    <form method="POST" action="account">

                        <div class="sp-name">
                            <div class="fname w50">
                                <lable>First name</lable><br>
                                <input class="textbox" type="text" name="account-firstname" value="${user.first_name}">
                            </div>
                            <div class="lname w50">
                                <lable>Last name</lable><br>
                                <input class="textbox" type="text" name="account-lastname" value="${user.last_name}">
                            </div>
                        </div>

                        <div class="sp-email">
                            <lable>Email</lable><br>
                            <input class="textbox" type="text" name="account-email" value="${user.email}" readonly>
                        </div>

                        <div class="sp-password">
                            <div class="password">
                                <lable>Password</lable><br>
                                <input class="textbox" type="password" name="account-password" value="${user.password}">
                            </div>
                        </div>

                        <div class="sp-confirm">  
                            <span class="w50" style="text-align: center">
                                <c:if test="${message}">
                                    Profile updated successfully.
                                </c:if>
                            </span>
                            <input class="submitbtn w50" type="submit" value="Next">
                            <input type="hidden" name="action" value="updateAccount">
                        </div>
                    </form>
                    <form method="POST" action="account">
                        <div style="display: flex; flex-direction: row-reverse">
                            <input id="clearbtn" type="submit" value="Deactivate Account">
                            <input type="hidden" name="action" value="deactivate">
                        </div>
                    </form>


                </div>
            </div>
        </div>
    </body>
</html>
