package com.example.testpipeline;

import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingFilter implements Filter {
    Logger logger = Logger.getLogger(LoggingFilter.class.getName());
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper((HttpServletRequest) servletRequest);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper((HttpServletResponse) servletResponse);

        filterChain.doFilter(requestWrapper, responseWrapper);
        String requestData = getRequestData(requestWrapper);
        String responseData = getResponseData(responseWrapper);
        Map<String, String> requestHeaders = getHeadersFromHttpServletRequest(requestWrapper);
        logger.log(Level.INFO, requestData);
        logger.log(Level.INFO, responseData);
        logger.log(Level.INFO, requestWrapper.getMethod());
        logger.log(Level.INFO, requestWrapper.getServletPath());
        logger.log(Level.INFO, String.valueOf(requestWrapper.getRemotePort()));
        logger.log(Level.INFO, String.valueOf(responseWrapper.getStatus()));
        logger.log(Level.INFO, requestHeaders.toString());
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    private static String getRequestData(final HttpServletRequest httpServletRequest) throws UnsupportedEncodingException {
        String payload = "";
        ContentCachingRequestWrapper contentCachingRequestWrapper = WebUtils.getNativeRequest(httpServletRequest, ContentCachingRequestWrapper.class);
        if (contentCachingRequestWrapper != null) {
            byte[] contentByte = contentCachingRequestWrapper.getContentAsByteArray();
            if (contentByte.length > 0) {
                payload = new String(contentByte, 0, contentByte.length, contentCachingRequestWrapper.getCharacterEncoding());
            }
        }
        return payload;
    }

    private static String getResponseData(final HttpServletResponse httpServletResponse) throws IOException {
        String payload = "";
        ContentCachingResponseWrapper contentCachingResponseWrapper = WebUtils.getNativeResponse(httpServletResponse, ContentCachingResponseWrapper.class);
        if (contentCachingResponseWrapper != null) {
            byte[] contentByte = contentCachingResponseWrapper.getContentAsByteArray();
            if (contentByte.length > 0) {
                payload = new String(contentByte, 0, contentByte.length, contentCachingResponseWrapper.getCharacterEncoding());
                contentCachingResponseWrapper.copyBodyToResponse();
            }
        }
        return payload;
    }

    public static Map<String, String> getHeadersFromHttpServletRequest(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }

        return map;
    }
}
