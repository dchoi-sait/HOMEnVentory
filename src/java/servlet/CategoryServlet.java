/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Category;
import model.User;
import service.AccountService;
import service.CategoryService;

/**
 *
 * @author 775262
 */
public class CategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        User user = new AccountService().getUser(email);
        request.setAttribute("user", user);
        new CategoryService().setAttributes(request);
        getServletContext().getRequestDispatcher("/WEB-INF/category.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        User user = new AccountService().getUser(email);
        request.setAttribute("user", user);
        String action = request.getParameter("action");
        Logger.getAnonymousLogger().log(Level.SEVERE, action);

        CategoryService cs = new CategoryService();

        if (action == null) {
            cs.setAttributes(request);
            getServletContext().getRequestDispatcher("/WEB-INF/category.jsp").forward(request, response);
            return;
        }

        if (action.equals("cate-edit-modal")) {
            int editCategory = Integer.parseInt(request.getParameter("cateEditId"));
            Category category = cs.get(editCategory);
            request.setAttribute("edit_cate", category);
            request.setAttribute("modal", true);
        }

        if (action.equals("cancel-edit")) {
            request.setAttribute("modal", false);
        }

        if (action.equals("cate-add-modal")) {
            request.setAttribute("cateadd", true);
            request.setAttribute("modal", true);
        }

        if (action.equals("cate-edit-save")) {
            String editCate = request.getParameter("editCate");
            String cateName = request.getParameter("cate-edit-name");
            if (!editCate.equals("") || editCate != null || !cateName.equals("") || cateName != null) {
                Category cateToEdit = cs.get(Integer.parseInt(editCate));
                if (cateToEdit != null) {
                    cateToEdit.setCategory_name(cateName);
                    cs.update(cateToEdit);
                }
            } else {
                request.setAttribute("message", "Invalid Entry, Please Try Again");
                request.setAttribute("modal", true);
            }
        }

        if (action.equals("cate-add-save")) {
            String cateName = request.getParameter("cate-edit-name");
            if (cateName != null || !cateName.equals("")) {
                cs.insert(cateName);
            } else {
                request.setAttribute("cateadd", true);
                request.setAttribute("message", "Invalid Entry, Please Try Again");
                request.setAttribute("modal", true);
            }
        }

        if (action.equals("cate-delete")) {
            String deleteId = request.getParameter("cateEditId");
            if (deleteId != null) {
                int id = Integer.parseInt(deleteId);
                Category category = cs.get(id);
                cs.delete(category);
            }
        }

        cs.setAttributes(request);
        getServletContext().getRequestDispatcher("/WEB-INF/category.jsp").forward(request, response);
    }
}
