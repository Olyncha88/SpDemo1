package com.company.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimerAspect {

    private final static Logger LOGGER =
                (Logger) LoggerFactory.getLogger(com.company.aspect.TimerAspect.class);

    @Pointcut("execution(* *..run*(int,..))")
    public void runMethodPointcut(){}

    @Around("runMethodPointcut()")
    public Object callTimer(ProceedingJoinPoint joinPoint) throws Throwable {
     long start = System.currentTimeMillis();
     Object returnValue = joinPoint.proceed();
     long end = System.currentTimeMillis();
    LOGGER.info("Method {} work {} mil", joinPoint.getSignature().getName(),
    end - start);
    return returnValue;
    }
}
