package com.ucb.mercadoapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index.html");
        registry.addViewController("/home").setViewName("forward:/index.html");
        registry.addViewController("/login").setViewName("forward:/login.html");
        registry.addViewController("/cadastro").setViewName("forward:/cadastro.html");
    }
}