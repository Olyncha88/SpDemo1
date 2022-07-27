package com.company.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import java.util.stream.Collectors;

import org.aspectj.lang.annotation.Pointcut;

@Aspect
@Component
public class LoggingAspect {
    private final static Logger LOGGER =
            (Logger) LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("within(com.company.service.*)")
    public void allLogMethods(){
            }

    @Before("allLogMethods()")
    public void callBefore(JoinPoint joinPoint){
        String args = Arrays.stream(joinPoint.getArgs()).map(Object::toString).
                collect(Collectors.joining(", "));
        LOGGER.info("Before {}, args[{}]",
                joinPoint.getSignature().getName(), args);
    }
    @After("allLogMethods()")
    public void callAfter(JoinPoint joinPoint){
        LOGGER.info("After {}", joinPoint);
    }
}
