package com.dengzhanglin.xyeh.web.controller.admin;

public abstract class BaseController {

    protected String successView() {
        return "redirect:/admin/success";
    }
}
