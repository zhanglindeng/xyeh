package com.dengzhanglin.xyeh.exception;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class AppExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object errorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {

        e.printStackTrace();

        this.logger.error("url: " + request.getRequestURL().toString());
        this.logger.error("message: " + e.getMessage());
        this.logger.error("status: " + response.getStatus());

        response.setStatus(500);

        if (this.isAjax(request)) {
            // JSON
            return new JSONObject() {{
                put("code", -1);
                put("message", "Internal Server Error");
            }};
        }

        // view
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("errors/500");

        return modelAndView;
    }

    private Boolean isAjax(HttpServletRequest request) {
        // X-Requested-With: XMLHttpRequest
        return request.getHeader("X-Requested-With") != null &&
                "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }
}
