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

import java.util.Map;

@Configuration
public class MethodLogAutoConfig {

    @Bean
    @ConditionalOnMissingBean
    public AspectMethodLookup aspectMethodLookup() {
        return new AspectMethodLookup();
    }

    @Bean
    @ConditionalOnMissingBean
    public AspectMethodParametersLookup aspectMethodParametersLookup(AspectMethodLookup aspectMethodLookup) {
        return new AspectMethodParametersLookup(aspectMethodLookup);
    }

    @Bean
    @ConditionalOnMissingBean
    public AspectLoggerLookup aspectLoggerLookup() {
        return new AspectLoggerLookup();
    }

    @Bean
    @ConditionalOnMissingBean
    public StringFormatter<Map<String, Object>> stringFormatter() {
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
