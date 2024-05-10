package com.itstan.filter;

import com.alibaba.fastjson.JSONObject;
import com.itstan.pojo.Result;
import com.itstan.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j
//@WebFilter("/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String url = req.getRequestURL().toString();
        log.info("URL: " + url);

        if (url.contains("login")) {
            log.info("Login Operation. Released...");
            chain.doFilter(request, response);
            return;
        }

        String jwt = req.getHeader("token");

        if (!StringUtils.hasLength(jwt)) {
            log.info("Request null token, return not logged in content");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            res.getWriter().write(notLogin);
            return;
        }

        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("Failed to interpret the token, return non-logged in error message");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            res.getWriter().write(notLogin);
            return;
        }

        log.info("Valid token. Passed");
        chain.doFilter(request, response);

    }
}
