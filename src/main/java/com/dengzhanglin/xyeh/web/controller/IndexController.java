package com.dengzhanglin.xyeh.web.controller;

import com.dengzhanglin.xyeh.web.form.RegisterForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class IndexController {

    // 首页
    @GetMapping(value = "")
    public String index() {
        return "index";
    }

    // 显示注册页面
    @GetMapping(value = "/register")
    public String register(Model model, RegisterForm registerForm) {
        model.addAttribute("registerForm", registerForm);
        return "register";
    }

    // 处理注册请求
    @PostMapping(value = "/register")
    public String doRegister(@Valid RegisterForm registerForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        return "index";
    }

    // 显示登录页面
    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    // 处理登录请求
    @PostMapping(value = "/login")
    public String doLogin() {
        return "loginSuccess";
    }
}
