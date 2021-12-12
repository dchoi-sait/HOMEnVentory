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
public class SignupServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/signup.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountService as = new AccountService();
        String fname = request.getParameter("signup-firstname");
        boolean active = true;
        String lname = request.getParameter("signup-lastname");
        String email = request.getParameter("signup-email");
        String password = request.getParameter("signup-password");
        int role = 2;

        if (!fname.equals("") && fname != null && !lname.equals("") && lname != null && !password.equals("") && password != null) {
            User user = new User(email, active, fname, lname, password, role);
            boolean userAdded = as.insert(user);
            if (userAdded) {
                HttpSession session = request.getSession();
                session.setAttribute("email", email);
                response.sendRedirect("inventory");
                return;
            }
        }
        getServletContext().getRequestDispatcher("/WEB-INF/signup.jsp").forward(request, response);
        return;

    }

}
