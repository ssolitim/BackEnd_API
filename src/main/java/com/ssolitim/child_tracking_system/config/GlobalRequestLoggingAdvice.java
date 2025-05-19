package com.ssolitim.child_tracking_system.config;

import java.nio.charset.StandardCharsets;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.WebUtils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalRequestLoggingAdvice {

    @ModelAttribute
    public void logRequest(WebRequest request) {
        log.info("---------------모든 요청 로깅---------------");
        HttpServletRequest servletRequest = ((ServletWebRequest) request).getRequest();
        log.info("Request URI: {} {}", servletRequest.getMethod(), servletRequest.getRequestURI());
        requestLogging(servletRequest);
        log.info("------------------------------------------");
    }

    private void requestLogging(HttpServletRequest request) {
        log.info("request query string: {}", getQueryString(request));
        log.info("request body: {}", getRequestBody(request));
    }

    private String getQueryString(HttpServletRequest httpRequest) {
        String queryString = httpRequest.getQueryString();
        if (queryString == null) {
            return " - ";
        }
        return queryString;
    }

    private String getRequestBody(HttpServletRequest request) {
        ContentCachingRequestWrapper wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
        if (wrapper == null) return " - (no wrapper)";
        byte[] buf = wrapper.getContentAsByteArray();
        if (buf.length == 0) return " - (empty or not yet read)";
        try {
            String encoding = wrapper.getCharacterEncoding() != null ? wrapper.getCharacterEncoding() : StandardCharsets.UTF_8.name();
            return new String(buf, encoding);
        } catch (Exception e) {
            return " - (decoding error)";
        }
    }
}