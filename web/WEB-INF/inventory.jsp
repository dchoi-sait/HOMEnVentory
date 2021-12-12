<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <script>

            function toggleMenu() {
                const el = document.querySelector(".dropdown-content");
                el.style.display = (el.style.display === "none") ? "block" : "none";
                console.log("called");
            }

        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>HOMEnVentory</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Kanit:wght@400;500;600&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="css/inventory.css" />
        <link rel="stylesheet" type="text/css" href="css/nav.css" />
    </head>
    <body>
        <c:if test="${modal}">
            <div class="modal-container">
                <div class="modal">
                    <div class="top-box">
                        <h2>${itemadd ? "Add Item" : "Edit Item"}</h2>
                    </div>

                    <div class="bottom-box">
                        <form method="POST" action="inventory">

                            <label>Name: </label><br>
                            <input class="textbox" type="text" name="item-name" value="${edit_item.item_name}" required><br>
                            <label>Price: </label><br>
                            <input class="textbox" type="number" name="item-price" value="${edit_item.price}" required><br>
                            <label>Category: </label> 
                            <select name="cate-option">
                                <c:forEach items="${categories}" var="cate">
                                    <option value="${cate.category_id}" ${edit_item.category == cate.category_id? "selected" : ''}>${cate.category_name}</option>
                                </c:forEach>    
                            </select><br>
                            <input type="submit" value="save">
                            <input type="hidden" name="action" value=${itemadd ? "item-add-save" : "item-edit-save"}>
                            <input type="hidden" name="editItemID" value="${edit_item.item_id}">
                        </form>
                        <form class="cancel-container" method="POST" action="inventory">
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
                    <div class="nav-menu nav-hide" id="inventory"><a href="inventory" class="nav-seleted">Inventory</a></div>
                    <c:if test="${user.role == 3 || user.role == 1}">
                        <div class="nav-menu nav-hide" id="admin"><a href="admin">Admin</a></div>
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
                            <a href="inventory" class="nav-seleted">Inventory</a>
                            <c:if test="${user.role == 3 || user.role == 1}">
                                <a href="admin">Admin</a>
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
                <h1>Inventory</h1>
                <h2>Manage Your Inventory</h2>
            </div>
        </div>
        <div class="container">




            <c:if test="${items.size() <= 0}">
                <div style="color: grey; margin: 30px 0px;">Please Add Your First Item</div>
                <div class="add-container">
                    <div class="add-btn-container">
                        <span class="plus">+</span>
                        <form class="add-form" method="POST" action="inventory">
                            <input class="add-btn" type="submit" value=""/>

                            <input type="hidden" name="action" value="item-add-modal">
                        </form>
                    </div>
                </div>
            </c:if>

            <c:if test="${items.size() > 0}">
                <div class="search-container">
                    <div class="add-container">
                        <div class="add-btn-container">
                            <span class="plus">+</span>
                            <form class="add-form" method="POST" action="inventory">
                                <input class="add-btn" type="submit" value=""/>

                                <input type="hidden" name="action" value="item-add-modal">
                            </form>
                        </div>
                    </div>

                </div>

                <div class="table-container">


                    <table>  
                        <colgroup>
                            <col class="expand" style="width:25%">
                            <col class="expand" style="width:25%">
                            <col class="hide" style="width:25%">                   
                            <col class="expand" style="width:25%">
                        </colgroup> 

                        <tr>
                            <th>Category</th>
                            <th>Name</th>
                            <th class="hide">Price</th>
                            <th colspan="2">Edit</th>

                        </tr>    
                        <c:forEach items="${items}" var="item">
                            <tr>
                                <td>${categories[item.category - 1].category_name}</td>
                                <td>${item.item_name}</td>
                                <td class="hide">$${item.price}</td>

                                <td class="btn-layout">
                                    <form class="w40" method="POST" action="inventory">
                                        <input class="btn" type="submit" value="Edit">
                                        <input  type="hidden" name="action" value="item-edit-modal">
                                        <input type="hidden" name="itemID" value="${item.item_id}">
                                    </form>

                                    <form class="w40" method="POST" action="inventory">
                                        <input class="btn" type="submit" value="Delete">
                                        <input type="hidden" name="action" value="item-delete">
                                        <input type="hidden" name="itemID" value="${item.item_id}">
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>   
                </c:if>
            </div>
        </div>
    </body>
</html>
