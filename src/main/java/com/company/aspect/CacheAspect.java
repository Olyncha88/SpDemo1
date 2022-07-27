package com.company.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Aspect
@Component
public class CacheAspect {
    private final static Logger LOGGER =
            (Logger) LoggerFactory.getLogger(com.company.aspect.CacheAspect.class);
    private final static Map<String,Object> cache = new HashMap<>();

    @Pointcut("@annotation(com.company.anotat.Cache)")
    public void runMethodPointcut(){}

    @Around("runMethodPointcut()")
    public Object callCache(ProceedingJoinPoint joinPoint) throws Throwable {
        String signatureKey = createKey(joinPoint);
        LOGGER.info("Run method {}! ", signatureKey);
        Object returnValue;
        if(cache.containsKey(signatureKey)){
            returnValue = cache.get(signatureKey);
            LOGGER.info("Get value {} from cache! ", returnValue);
            return returnValue;
            }
        returnValue = joinPoint.proceed();
        cache.put(signatureKey, returnValue);
        LOGGER.info("Get value {} from method! ", returnValue);
        return returnValue;
    }
    private String createKey(JoinPoint point){
        String args = Arrays.stream(point.getArgs()).map(Object::toString).
                collect(Collectors.joining(", "));
        StringBuilder builder = new StringBuilder();
        Signature signature = point.getSignature();
        builder.append(signature.getDeclaringType());
        builder.append(" . ");
        builder.append(signature.getName());
        builder.append("(");
        builder.append(args);
        builder.append(")");
        return builder.toString();
    }
}
