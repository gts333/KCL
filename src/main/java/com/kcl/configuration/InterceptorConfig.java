package com.kcl.configuration;

import com.kcl.controller.interceptor.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    private UserInterceptor userInterceptor;

    @Autowired
    public InterceptorConfig(UserInterceptor userInterceptor) {
        this.userInterceptor = userInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> pathPatterns = new ArrayList<>();
        pathPatterns.add("/admin/**");
        pathPatterns.add("/student/**");
        pathPatterns.add("/teachingAssistant/**");
        pathPatterns.add("/privileged/**");
        registry.addInterceptor(userInterceptor)
                .addPathPatterns(pathPatterns);
    }
}
