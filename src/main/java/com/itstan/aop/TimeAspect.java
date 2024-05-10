package com.itstan.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class TimeAspect {
//    @Pointcut("execution(public void com.itstan.service.impl.DeptServiceImpl(..))")
    @Pointcut("@annotation(com.itstan.anno.MyLog)")
    private void pt() {}

    @Order(4)
//    @Around("pt()")
    public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {

        long begin = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        log.info("recordTime AOP function proceeded");
//        log.info(joinPoint.getTarget().getClass().getName());
//        log.info(joinPoint.getSignature().getName());
//        log.info(Arrays.toString(joinPoint.getArgs()));
        log.info("Method execution spend: {} ms", end - begin);
        return result;
    }
}
