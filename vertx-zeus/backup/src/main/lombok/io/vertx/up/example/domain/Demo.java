package io.vertx.up.example.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Demo {

    @NotNull(message = "设置名称不可为空")
    private String name;

    @NotNull(message = "Email地址不可为空")
    private String email;
}
