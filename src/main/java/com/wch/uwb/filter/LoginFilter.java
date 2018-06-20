package com.wch.uwb.filter;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Order(1)
//重点
@WebFilter(filterName = "LoginFilter", urlPatterns = "/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURI().substring(request.getContextPath().length());
        if (url.startsWith("/") && url.length() > 1) {
            url = url.substring(1);
        }
        if(url.equals("mainManage") || url.equals("reportManage")
                || url.equals("userManage") || url.equals("editUser")
                || url.equals("free")|| url.equals("weiboManage")
                || url.equals("delWeibo")|| url.equals("reportManageGET")){
            HttpSession session = request.getSession();
            if (session.getAttribute("manageId") != null){
                // session存在
                chain.doFilter(request, response);
                return;
            } else {
                // session不存在 准备跳转失败
                RequestDispatcher dispatcher = request.getRequestDispatcher("/loginManage");
                dispatcher.forward(request, response);
                //chain.doFilter(request, response);
                return;
            }
        }if (url.equals("") || url.equals("register") ||url.equals("loginManage")){
            chain.doFilter(request, response);
            return;
        } else {
            HttpSession session = request.getSession();
            if (session.getAttribute("id") != null){
                // session存在
                chain.doFilter(request, response);
                return;
            } else {
                // session不存在 准备跳转失败
                RequestDispatcher dispatcher = request.getRequestDispatcher("/");
                dispatcher.forward(request, response);
                //chain.doFilter(request, response);
                return;
            }
        }

    }

    @Override
    public void destroy() {

    }
}
