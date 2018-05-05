package com.dengzhanglin.xyeh.web.controller.admin;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * @link https://docs.spring.io/spring-framework/docs/4.1.6.RELEASE/spring-framework-reference/html/mvc.html#mvc-ann-controller-advice
 */
@ControllerAdvice(basePackages = "com.dengzhanglin.xyeh.web.controller.admin")
public class AdminPackageAdvice {

    @ModelAttribute(value = "userName")
    public String userName() {
        return "Admin Name";
    }
}
