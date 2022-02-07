package com.projectthymeleaf.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class RequestTimingInterceptor implements HandlerInterceptor {

    private static final String REQUEST_TIME = "startRequestTime";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        request.setAttribute(REQUEST_TIME, System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        request.setAttribute("TIMING_LOGGED", "true");
        logExecutionTiming(request);
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) {
        if (request.getAttribute("TIMING_LOGGED") == null) {
            logExecutionTiming(request);
        }
    }

    private void logExecutionTiming(HttpServletRequest request) {
        Long requestEndTime = System.currentTimeMillis();
        Long requestStartTime = (Long) request.getAttribute(REQUEST_TIME);


            log.info(
                    "Request processing time for URI {} , Method {} , is {} ms ",
                    request.getRequestURI(),
                    request.getMethod(),
                    (requestEndTime - requestStartTime));

    }
}
