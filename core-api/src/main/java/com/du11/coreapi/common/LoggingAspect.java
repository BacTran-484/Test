package com.du11.coreapi.common;

import com.du11.coreapi.config.LoggingAspectConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Slf4j
@Component
public class LoggingAspect {

    private static final String LOG_PARAM = "[%s.%s] params value: %s";
    
    @Autowired
    ObjectMapper mapper;

    @Pointcut("within(@org.springframework.stereotype.Repository *)")
    public void repositoryClassMethods() {
        //This method for pointcut declare
    }

    public Object logHeadAndTail(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();

        String className = methodSignature.getDeclaringType().getSimpleName();
        String method = methodSignature.getName();
        for (Object param : proceedingJoinPoint.getArgs()) {
            if (param instanceof Class) {
                log.info(String.format(LOG_PARAM, className, method, mapper.writeValueAsString(param)));
            } else if (null == param) {
                log.info(String.format(LOG_PARAM, className, method, "NULL"));
            } else {
                log.info(String.format(LOG_PARAM, className, method, param.toString()));
            }
        }

        log.info(String.format("[%s.%s] execution time: %s ms", className, method, stopWatch.getTotalTimeMillis()));
        if (!Repository.class.isAssignableFrom(methodSignature.getDeclaringType())) {
            log.info(String.format("[%s.%s] result: %s", className, method, mapper.writeValueAsString(result)));
        }

        return result;
    }
}
