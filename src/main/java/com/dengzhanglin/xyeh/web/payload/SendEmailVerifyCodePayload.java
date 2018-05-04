package com.dengzhanglin.xyeh.web.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SendEmailVerifyCodePayload {
    @NotBlank
    @Email
    @Size(min = 3, max = 50)
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "SendEmailVerifyCodePayload{" +
                "email='" + email + '\'' +
                '}';
    }
}
