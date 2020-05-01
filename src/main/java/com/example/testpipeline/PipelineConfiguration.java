package com.example.testpipeline;

import com.example.testpipeline.interceptor.LoggingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

@Configuration
public class PipelineConfiguration implements WebMvcConfigurer {

    @Autowired
    LoggingInterceptor loggingInterceptor;

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(loggingInterceptor);
//    }

    @Bean
    public Filter LogsFilter() {
        return new LoggingFilter();
    }
}
