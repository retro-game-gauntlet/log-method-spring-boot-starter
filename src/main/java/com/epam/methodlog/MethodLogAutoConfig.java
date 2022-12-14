package com.epam.methodlog;

import com.epam.methodlog.aspect.AspectMethodLookup;
import com.epam.methodlog.aspect.AspectMethodParametersLookup;
import com.epam.methodlog.aspect.log.AspectLoggerLookup;
import com.epam.methodlog.aspect.log.InputMethodLogAspect;
import com.epam.methodlog.aspect.log.OutputMethodLogAspect;
import com.epam.methodlog.utils.formatter.MapWithoutBracketsStringFormatter;
import com.epam.methodlog.utils.formatter.StringFormatter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Map;

@Configuration
@EnableAspectJAutoProxy
public class MethodLogAutoConfig {

    @Bean
    public AspectMethodLookup aspectMethodLookup() {
        return new AspectMethodLookup();
    }

    @Bean
    public AspectMethodParametersLookup aspectMethodParametersLookup(AspectMethodLookup aspectMethodLookup) {
        return new AspectMethodParametersLookup(aspectMethodLookup);
    }

    @Bean
    public AspectLoggerLookup aspectLoggerLookup() {
        return new AspectLoggerLookup();
    }

    @Bean
    @ConditionalOnMissingBean(value = StringFormatter.class, name = "mapStringFormatter")
    public StringFormatter<Map<String, Object>> mapStringFormatter() {
        return new MapWithoutBracketsStringFormatter<>();
    }

    @Bean
    public InputMethodLogAspect inputMethodLogAspect(AspectMethodLookup aspectMethodLookup,
                                                     AspectMethodParametersLookup aspectMethodParametersLookup,
                                                     StringFormatter<Map<String, Object>> stringFormatter,
                                                     AspectLoggerLookup aspectLoggerLookup) {
        return new InputMethodLogAspect(aspectMethodLookup, aspectMethodParametersLookup, stringFormatter, aspectLoggerLookup);
    }

    @Bean
    public OutputMethodLogAspect outputMethodLogAspect(AspectMethodLookup aspectMethodLookup,
                                                       AspectLoggerLookup aspectLoggerLookup) {
        return new OutputMethodLogAspect(aspectMethodLookup, aspectLoggerLookup);
    }
}