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
                        <h2>${cateadd ? "Add Category" : "Edit Category"}</h2>
                    </div>

                    <div class="bottom-box">
                        <form method="POST" action="category">

                            <label>Category Name: </label><br>
                            <input class="textbox" type="text" name="cate-edit-name" value="${edit_cate.category_name}" required><br>


                            <input type="submit" value="save">
                            <input type="hidden" name="action" value=${cateadd ? "cate-add-save" : "cate-edit-save"}>
                            <input type="hidden" name="editCate" value="${edit_cate.category_id}">
                        </form>
                        <form class="cancel-container" method="POST" action="category">
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
                        <div class="nav-menu nav-hide" id="admin"><a href="admin">Admin</a></div>
                        <div class="nav-menu nav-hide" id="category"><a href="category" class="nav-seleted">Category</a></div>
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
            <div class="banner-container">
                <h1>Administrator</h1>
                <h2>Manage Categories</h2>
            </div>
        </div>
        <div class="container">

            <div class="search-container">
                <div class="add-container">
                    <div class="add-btn-container">
                        <span class="plus">+</span>
                        <form class="add-form" method="POST" action="category">
                            <input class="add-btn" type="submit" value=""/>
                            <input type="hidden" name="action" value="cate-add-modal">
                        </form>
                    </div>
                </div>

            </div>




            <div class="table-container">
                <table>  
                    <colgroup>
                        <col style="width:50%">                                      
                        <col style="width:50%">
                    </colgroup> 

                    <tr>
                        <th>Category</th>                                 
                        <th>Edit</th>
                    </tr>    
                    <c:forEach items="${categories}" var="category">
                        <tr>
                            <td>${category.category_name}</td>                                                                                                      
                            <td class="btn-layout">
                                <form class="w40" method="POST" action="category">
                                    <input class="btn" type="submit" value="Edit">
                                    <input  type="hidden" name="action" value="cate-edit-modal">
                                    <input type="hidden" name="cateEditId" value="${category.category_id}">
                                </form>


                                <form class="w40" method="POST" action="category">
                                    <input class="btn" type="submit" value="Delete">
                                    <input type="hidden" name="action" value="cate-delete">
                                    <input type="hidden" name="cateEditId" value="${category.category_id}">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>      
            </div>
        </div>
    </body>
</html>
