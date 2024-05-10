package com.itstan.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.itstan.pojo.Result;
import com.itstan.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {

        String url = req.getRequestURL().toString();
        log.info("URL: " + url);

        if (url.contains("login")) {
            log.info("Login Operation. Released...");
            return true;
        }

        String jwt = req.getHeader("token");

        if (!StringUtils.hasLength(jwt)) {
            log.info("Request null token, return not logged in content");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            res.getWriter().write(notLogin);
            return false;
        }

        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("Failed to interpret the token, return non-logged in error message");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            res.getWriter().write(notLogin);
            return false;
        }

        log.info("Valid token. Passed");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle...");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion...");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
