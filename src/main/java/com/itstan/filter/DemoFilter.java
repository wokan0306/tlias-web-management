package com.itstan.filter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

// @WebFilter(urlPatterns = "/*")
public class DemoFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        System.out.println("Initialization");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Demo: Intercepted Request. Logic Before Released");

        filterChain.doFilter(servletRequest, servletResponse);

        System.out.println("Demo: Intercepted Request. Logic After Released");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
        System.out.println("Destruction");
    }
}
