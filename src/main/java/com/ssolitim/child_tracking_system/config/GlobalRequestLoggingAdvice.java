package com.ssolitim.child_tracking_system.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalRequestLoggingAdvice {

    @ModelAttribute
    public void logRequest(HttpServletRequest request) {
        log.info("Request URI: {}", request.getRequestURI());
    }
}