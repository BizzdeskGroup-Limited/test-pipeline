package com.example.testpipeline.interceptor;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Enumeration;

@Component
public class LoggingInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        System.out.println(request.getServerPort());
        System.out.println(IOUtils.toString(request.getReader()));
        //ServletOutputStream servletOutputStream = response.getOutputStream();
        System.out.println(getRawHeaders(response));
    }

    private String getRawHeaders(HttpServletResponse response){
        StringBuffer rawHeaders = new StringBuffer();
        Enumeration headerNames = Collections.enumeration(response.getHeaderNames());
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = response.getHeader(key);
            rawHeaders.append(key).append(":").append(value).append("\n");
        }

        return rawHeaders.toString();
    }

}
