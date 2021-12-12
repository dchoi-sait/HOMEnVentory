/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
import service.AccountService;

/**
 *
 * @author 775262
 */
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        User user = new AccountService().getUser(email);
        request.setAttribute("user", user);
        new AccountService().setAttributes(request);
        getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        String action = request.getParameter("action");

        AccountService as = new AccountService();
        User adminUser = as.getUser(email);
        request.setAttribute("user", adminUser);

        if (action == null) {
            as.setAttributes(request);
            getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
            return;
        }

        if (action.equals("user-edit-modal")) {
            String editEmail = request.getParameter("userEditEmail");
            User user = as.getUser(editEmail);
            request.setAttribute("edit_user", user);
            request.setAttribute("modal", true);
        }

        if (action.equals("cancel-edit")) {
            request.setAttribute("modal", false);
        }

        if (action.equals("user-add-modal")) {
            request.setAttribute("useradd", true);
            request.setAttribute("modal", true);
        }

        if (action.equals("user-edit-save")) {
            String editUserEmail = request.getParameter("editUser");
            String fname = request.getParameter("admin-edit-fname");
            String lname = request.getParameter("admin-edit-lname");
            String password = request.getParameter("admin-edit-password");
            String role = request.getParameter("role-option");
            boolean active = request.getParameter("admin-edit-isActive") != null;
            if (fname != null && !fname.equals("") && lname != null && !lname.equals("") && role != null && !role.equals("") && password != null && !password.equals("")) {
                User userToEdit = as.getUser(editUserEmail);
                if (userToEdit != null) {
                    if (adminUser.getRole() == 1) {
                        userToEdit.setActive(active);
                    }
                    userToEdit.setFirst_name(fname);
                    userToEdit.setLast_name(lname);
                    userToEdit.setPassword(password);
                    userToEdit.setRole(Integer.parseInt(role));
                    as.updateUser(userToEdit);
                }
            } else {
                request.setAttribute("message", "Invalid Entry, Please Try Again");
                request.setAttribute("modal", true);
            }
        }

        if (action.equals("user-add-save")) {
            String newEmail = request.getParameter("admin-edit-email");
            String fname = request.getParameter("admin-edit-fname");
            String lname = request.getParameter("admin-edit-lname");
            String role = request.getParameter("role-option");
            String password = request.getParameter("admin-edit-password");
            boolean active = request.getParameter("admin-edit-isActive") != null;
            if (newEmail != null && !newEmail.equals("") && fname != null && !fname.equals("") && lname != null && !lname.equals("") && role != null && !role.equals("") && password != null && !password.equals("")) {
                User newUser = new User();
                newUser.setEmail(newEmail);
                newUser.setActive(active);
                newUser.setFirst_name(fname);
                newUser.setLast_name(lname);
                newUser.setPassword(password);
                newUser.setRole(Integer.parseInt(role));
                as.insert(newUser);

            } else {
                request.setAttribute("useradd", true);
                request.setAttribute("message", "Invalid Entry, Please Try Again");
                request.setAttribute("modal", true);
            }
        }

        if (action.equals("user-delete")) {
            String deleteEmail = request.getParameter("userEditEmail");
            if (deleteEmail != null) {
                as.deleteUser(deleteEmail);
            }
        }

        as.setAttributes(request);
        getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
    }

}
