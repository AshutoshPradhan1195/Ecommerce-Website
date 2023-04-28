
package controller.filters.login;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthenticationFilter
implements Filter {
    private ServletContext context;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;
        String uri = req.getRequestURI();
        boolean isLoginJsp = uri.endsWith("login.jsp");
        boolean isLoginServlet = uri.endsWith("loginServlet");
        boolean isLogoutServlet = uri.endsWith("logoutServlet");
        boolean isSignJsp = uri.endsWith("signIn.jsp");
        boolean isSignInServlet = uri.endsWith("signInServlet");
        this.context.log("Requested resource" + uri);
        HttpSession session = req.getSession(false);
        boolean loggedIn = session != null && session.getAttribute("user") != null;
        if (!(loggedIn || isLoginJsp || isLoginServlet || isSignJsp || isSignInServlet || uri.contains("css") )) {
            res.sendRedirect(String.valueOf(req.getContextPath()) + "/pages/login.jsp");
        } else if (loggedIn && isSignJsp && !isLogoutServlet) {
            res.sendRedirect(String.valueOf(req.getContextPath()) + "/pages/home.jsp");
        } else if (loggedIn && isLoginJsp && !isSignJsp) {
            res.sendRedirect(String.valueOf(req.getContextPath()) + "/pages/home.jsp");
        } else {
            chain.doFilter(request, response);
        }
    }

    public void destroy() {
    }
}