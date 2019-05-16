package com.imooc.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ModiPasswordForm {
    @NotEmpty(message = "邮箱不能为空")
    private String email;
    @NotEmpty(message = "验证码不能为空")
    private String authCode;
    @NotEmpty(message = "新密码不能为空")
    private String newPassword;
}
