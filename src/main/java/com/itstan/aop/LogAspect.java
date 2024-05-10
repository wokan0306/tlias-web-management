package com.itstan.aop;

import com.alibaba.fastjson.JSONObject;
import com.itstan.mapper.OperateLogMapper;
import com.itstan.pojo.OperateLog;
import com.itstan.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class LogAspect{
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.itstan.anno.Log)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{

        // Operator ID - Current Login Employee ID
        String jwt = request.getHeader("token");

        Claims claims = JwtUtils.parseJWT(jwt);
        Integer operateUser = (Integer) claims.get("id");

        // Current Time
        LocalDateTime operateTime = LocalDateTime.now();

        // Class Name
        String className = joinPoint.getTarget().getClass().getName();

        // Method Name
        String methodName = joinPoint.getSignature().getName();

        // Method Parameter Names
        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);

        // Execute the original targeted method
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();

        // Reture value of the original mehtod
        String returnValue = JSONObject.toJSONString(result);

        // Execution Time
        Long spendTime = endTime - startTime;

        // Record operating log
        OperateLog operateLog = new OperateLog(null, operateUser, operateTime, className, methodName, methodParams, returnValue, spendTime);
        operateLogMapper.insert(operateLog);

        log.info("AOP Record Operating Log", operateLog);
        return result;
    }
}
