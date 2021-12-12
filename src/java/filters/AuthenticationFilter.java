/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
import service.AccountService;

/**
 *
 * @author 775262
 */
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //any code before chain.doFilter will be executed before the servlet

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String email = (String) session.getAttribute("email");
        AccountService as = new AccountService();
        if (email == null) {
            httpResponse.sendRedirect("login");
            return;
        }

        User user = as.getUser(email);
        if (user == null) {
            httpResponse.sendRedirect("login");
            return;
        }

        if (!user.isActive()) {
            httpResponse.sendRedirect("login");
            return;
        }

        //This will either call upon the next filter in the chain,
        //or it will load the requested servlet
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
}
