package com.dengzhanglin.xyeh.web.controller;

import com.alibaba.fastjson.JSONObject;

abstract class BaseController {
    protected Object success() {
        return new JSONObject() {{
            put("code", 0);
            put("message", "OK");
        }};
    }

    protected Object failed(String message) {
        return new JSONObject() {{
            put("code", 1);
            put("message", message);
        }};
    }
}
