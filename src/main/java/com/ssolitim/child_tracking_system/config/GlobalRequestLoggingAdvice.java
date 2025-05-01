package com.ssolitim.child_tracking_system.config;

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
        var wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
        if (wrapper == null) {
            return " - ";
        }
        try {
            // body가 읽히지 않고 예외처리 되는 경우에 캐시하기 위함
            wrapper.getInputStream().readAllBytes();
            byte[] buf = wrapper.getContentAsByteArray();
            if (buf.length == 0) {
                return " - ";
            }
            return new String(buf, wrapper.getCharacterEncoding());
        } catch (Exception e) {
            return " - ";
        }
    }
}