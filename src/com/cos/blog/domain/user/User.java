package com.cos.blog.domain.user;

import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String address;

    //admin : 관리자
    //user : 일반회원
    //권한
    private String userRole;
    private Timestamp createDate;
}
