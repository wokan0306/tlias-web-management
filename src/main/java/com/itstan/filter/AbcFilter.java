package com.itstan.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

// @WebFilter("/*")
public class AbcFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("ABC: Intercepted Request. Logic Before Released");

        filterChain.doFilter(servletRequest, servletResponse);

        System.out.println("ABC: Intercepted Request. Logic After Released");

    }
}
