package com.dengzhanglin.xyeh.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController {

    // 首页
    @GetMapping(value = "")
    public String index() {
        return "index";
    }

    // 显示注册页面
    @GetMapping(value = "/register")
    public String register() {
        return "register";
    }

    // 处理注册请求
    @PostMapping(value = "/register")
    public String doRegister() {
        return "registerSuccess";
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
