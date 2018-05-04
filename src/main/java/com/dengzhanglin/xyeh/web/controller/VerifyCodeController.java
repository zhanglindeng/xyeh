package com.dengzhanglin.xyeh.web.controller;

import com.dengzhanglin.xyeh.service.AwsSesService;
import com.dengzhanglin.xyeh.util.RandomUtil;
import com.dengzhanglin.xyeh.web.payload.SendEmailVerifyCodePayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/verifyCode")
public class VerifyCodeController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private AwsSesService awsSesService;

    @Autowired
    public VerifyCodeController(AwsSesService awsSesService) {
        this.awsSesService = awsSesService;
    }

    @ResponseBody
    @PostMapping(value = "/email")
    public Object email(@RequestBody SendEmailVerifyCodePayload payload) {

        final String logPrefix = "[VerifyCodeController:email]";

        this.logger.info(logPrefix + payload.toString());

        final String verifyCode = RandomUtil.number(6);

        if (!this.awsSesService.send(payload.getEmail(), "验证码",
                "您的验证码是：<strong>" + verifyCode + "</strong>，15 分钟内有效。")) {
            return this.failed("发送验证码失败");
        }

        // todo 保存到数据库

        return this.success();
    }
}
