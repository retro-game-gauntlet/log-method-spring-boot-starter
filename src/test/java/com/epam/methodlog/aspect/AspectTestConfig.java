package com.epam.methodlog.aspect;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan("com.epam")
@Configuration
@EnableAspectJAutoProxy
public class AspectTestConfig {
}