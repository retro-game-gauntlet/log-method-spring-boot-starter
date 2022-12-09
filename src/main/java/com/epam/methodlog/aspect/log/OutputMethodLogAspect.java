package com.epam.methodlog.aspect.log;

import com.epam.methodlog.aspect.AspectMethodLookup;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;

import java.lang.reflect.Method;

@Aspect
public class OutputMethodLogAspect {

    private final AspectMethodLookup aspectMethodLookup;
    private final AspectLoggerLookup aspectLoggerLookup;

    public OutputMethodLogAspect(AspectMethodLookup aspectMethodLookup, AspectLoggerLookup aspectLoggerLookup) {
        this.aspectMethodLookup = aspectMethodLookup;
        this.aspectLoggerLookup = aspectLoggerLookup;
    }

    @Pointcut("@annotation(com.epam.methodlog.annotation.OutputMethodLog)")
    public void anyMethodAnnotatedWithOutputMethodLog() {
        // pointcut
    }

    @AfterReturning(value = "anyMethodAnnotatedWithOutputMethodLog()", returning = "retVal")
    public void logMethod(JoinPoint jp, Object retVal) {
        Method method = aspectMethodLookup.lookup(jp);
        Logger logger = aspectLoggerLookup.lookup(jp);
        logger.info("Method: '{}' returned: {}", method.getName(), retVal);
    }
}