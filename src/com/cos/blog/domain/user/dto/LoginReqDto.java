package com.cos.blog.domain.user.dto;

import lombok.Data;

@Data
public class LoginReqDto {
    private String username;
    protected String password;
}
