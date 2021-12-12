<%-- 
    Document   : admin
    Created on : Dec 5, 2021, 12:14:39 PM
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
        <link rel="stylesheet" type="text/css" href="css/admin.css" />
        <link rel="stylesheet" type="text/css" href="css/nav.css" />
    </head>
    <body>
        <c:if test="${modal}">
            <div class="modal-container">
                <div class="modal">
                    <div class="top-box">
                        <h2>${useradd ? "Add User" : "Edit User"}</h2>
                    </div>

                    <div class="bottom-box">
                        <form method="POST" action="admin">
                            <div class="name-box" style="display: flex; justify-content: space-between;">
                                <div class="w50">
                                    <label>First name: </label><br>
                                    <input class="textbox" type="text" name="admin-edit-fname" value="${edit_user.first_name}" required><br>
                                </div>
                                <div class="w50">
                                    <label>Last name: </label><br>
                                    <input class="textbox" type="text" name="admin-edit-lname" value="${edit_user.last_name}" required><br>
                                </div>
                            </div>
                            <label>Email: </label><br>
                            <input class="textbox" type="text" name="admin-edit-email" value="${edit_user.email}" ${useradd ? '' : "readonly"} required><br>
                            <label>Password: </label><br>
                            <input  class="textbox" type="password" name="admin-edit-password" value="${edit_user.password}" required><br>


                            <label>Role: </label> 
                            <select name="role-option">
                                <c:forEach items="${roles}" var="role">
                                    <option value="${role.role_id}" ${edit_user.role == role.role_id? "selected" : ''}>${role.role_name}</option>
                                </c:forEach>    
                            </select><br>
                            <label for="isActive">Active </label> 
                            <input style="margin-bottom: 20px;" id="isActive" type="checkbox" name="admin-edit-isActive" value="acitve" ${useradd ? 'checked = "true"' : ''} ${edit_user.active ? 'checked = "true"' : '' } ${user.role == 1 ? '' : 'disabled'}><br>
                            <input type="submit" value="save">
                            <input type="hidden" name="action" value=${useradd ? "user-add-save" : "user-edit-save"}>
                            <input type="hidden" name="editUser" value="${edit_user.email}">
                        </form>
                        <form class="cancel-container" method="POST" action="admin">
                            <input class="cancel" type="submit" value="cancel">
                            <input type="hidden" name="action" value="cancel-edit">
                        </form>
                    </div>
                </div>



            </div>
        </c:if>


        <div class="nav-container">
            <nav class="nav">
                <div class="nav-left">
                    <div class="nav-menu" id="title">HOME<span style="color: #ff9200">n</span>Ventory</div>
                    <div class="nav-menu nav-hide" id="inventory"><a href="inventory">Inventory</a></div>
                    <c:if test="${user.role == 3 || user.role == 1}">
                        <div class="nav-menu nav-hide" id="admin"><a href="admin" class="nav-seleted">Admin</a></div>
                    </c:if>
                </div>
                <div class="nav-right">
                    <div class="nav-menu nav-hide" id="logout"><a href="login">Logout</a></div>
                    <div id="account">
                        <a class="button" href="account">${user.first_name} ${user.last_name}</a>
                    </div>
                    <div class="dropdown" onclick="toggleMenu()">
                        <i class="fa fa-bars dropbtn"></i>
                        <div class="dropdown-content" style="display: none;">
                            <a href="inventory">Inventory</a>
                            <c:if test="${user.role == 3 || user.role == 1}">
                                <a href="admin" class="nav-seleted">Admin</a>
                            </c:if>
                            <a href="account">Account</a>
                            <a href="login">Logout</a>
                        </div>
                    </div>
                </div>
            </nav>
        </div>


        <div class="banner">
            <div class="banner-container">
                <h1>Administrator</h1>
                <h2>Manage Users</h2>
            </div>
        </div>
        <div class="container">

            <div class="search-container">
                <div class="add-container">
                    <div class="add-btn-container">
                        <span class="plus">+</span>
                        <form class="add-form" method="POST" action="admin">
                            <input class="add-btn" type="submit" value=""/>
                            <input type="hidden" name="action" value="user-add-modal">
                        </form>
                    </div>
                </div>

            </div>




            <div class="table-container">
                <table>  
                    <colgroup>
                        <col style="width:30%">
                        <col class ="hide" style="width:15%">
                        <col class ="hide" style="width:15%">                                       
                        <col style="width:30%">
                    </colgroup> 

                    <tr>
                        <th>Email</th>
                        <th class ="hide">First name</th>
                        <th class ="hide">Last name</th>                                      
                        <th>Edit</th>
                    </tr>    
                    <c:forEach items="${users}" var="user">
                        <tr>
                            <td>${user.email}</td>
                            <td class ="hide">${user.first_name}</td>
                            <td class ="hide">${user.last_name}</td>                                                                                  
                            <td class="btn-layout">
                                <form class="w40" method="POST" action="admin">
                                    <input class="btn" type="submit" value="Edit">
                                    <input  type="hidden" name="action" value="user-edit-modal">
                                    <input type="hidden" name="userEditEmail" value="${user.email}">
                                </form>


                                <form class="w40" method="POST" action="admin">
                                    <input class="btn" type="submit" value="Delete">
                                    <input type="hidden" name="action" value="user-delete">
                                    <input type="hidden" name="userEditEmail" value="${user.email}">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>      
            </div>
        </div>
    </body>
</html>
