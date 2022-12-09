package com.epam.methodlog.aspect.log;

import com.epam.methodlog.aspect.AspectMethodLookup;
import com.epam.methodlog.aspect.AspectMethodParametersLookup;
import com.epam.methodlog.utils.formatter.StringFormatter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;

import java.lang.reflect.Method;
import java.util.Map;

@Aspect
public class InputMethodLogAspect {

    private final AspectMethodLookup aspectMethodLookup;
    private final AspectMethodParametersLookup aspectMethodParametersLookup;
    private final StringFormatter<Map<String, Object>> mapStringFormatter;
    private final AspectLoggerLookup aspectLoggerLookup;

    public InputMethodLogAspect(AspectMethodLookup aspectMethodLookup,
                                AspectMethodParametersLookup aspectMethodParametersLookup,
                                StringFormatter<Map<String, Object>> mapStringFormatter,
                                AspectLoggerLookup aspectLoggerLookup) {
        this.aspectMethodLookup = aspectMethodLookup;
        this.aspectMethodParametersLookup = aspectMethodParametersLookup;
        this.mapStringFormatter = mapStringFormatter;
        this.aspectLoggerLookup = aspectLoggerLookup;
    }

    @Pointcut("@annotation(com.epam.methodlog.annotation.InputMethodLog)")
    public void anyMethodAnnotatedWithInputMethodLog() {
        // pointcut
    }

    @Before(value = "anyMethodAnnotatedWithInputMethodLog()")
    public void logMethod(JoinPoint jp) {
        Map<String, Object> args = aspectMethodParametersLookup.lookup(jp);
        String parameters = mapStringFormatter.format(args);
        Method method = aspectMethodLookup.lookup(jp);
        Logger logger = aspectLoggerLookup.lookup(jp);
        logger.info("Method: '{}' was called with parameters: {}", method.getName(), parameters);
    }
}