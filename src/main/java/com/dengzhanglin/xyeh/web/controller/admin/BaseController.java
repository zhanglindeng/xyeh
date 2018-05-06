package com.dengzhanglin.xyeh.web.controller.admin;

import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;

public abstract class BaseController {

    protected String successView() {
        return "redirect:/admin/success";
    }

    protected String successView(Model model, String url, String message) {
        return this.successView(model, message, url, 3);
    }

    protected String successView(Model model, String url) {
        return this.successView(model, "操作成功", url, 3);
    }

    protected String successView(Model model, String url, Integer wait) {
        return this.successView(model, "操作成功", url, wait);
    }

    protected String successView(Model model) {
        return this.successView(model, "操作成功", "javascript:history.back(-1);", 3);
    }

    private String successView(Model model, String message, String url, Integer wait) {
        model.addAttribute("message", message);
        model.addAttribute("wait", wait);
        model.addAttribute("url", url);
        return "admin/success";
    }

    protected String notFound(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return "errors/404";
    }
}
