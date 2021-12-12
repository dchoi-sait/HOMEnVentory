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
public class AccountServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        User user = new AccountService().getUser(email);
        request.setAttribute("user", user);
        getServletContext().getRequestDispatcher("/WEB-INF/account.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        String action = request.getParameter("action");
        AccountService as = new AccountService();
        User user = as.getUser(email);
        if (user == null) {
            getServletContext().getRequestDispatcher("/WEB-INF/account.jsp").forward(request, response);
            return;
        }
        
        if (action.equals("deactivate")) {            
            user.setActive(false);
            as.updateUser(user);                     
        }
        
        if (action.equals("updateAccount")) {
            String fname = request.getParameter("account-firstname");
            String lname = request.getParameter("account-lastname");
            String password = request.getParameter("account-password");           
            user.setFirst_name(fname);
            user.setLast_name(lname);
            user.setPassword(password); 
            
            as.updateUser(user);
            request.setAttribute("message", true);
        }
        request.setAttribute("user", user);  
        getServletContext().getRequestDispatcher("/WEB-INF/account.jsp").forward(request, response);
    }
    
}
