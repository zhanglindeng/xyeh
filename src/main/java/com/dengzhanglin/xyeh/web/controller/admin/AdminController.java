package com.dengzhanglin.xyeh.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/admin")
public class AdminController extends BaseController {

    @GetMapping(value = "")
    public String index() {
        return "admin/index";
    }

    @GetMapping(value = "/success")
    public String success() {
        return "admin/success";
    }
}
