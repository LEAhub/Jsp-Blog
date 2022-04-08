package com.cos.blog.service;

import com.cos.blog.domain.user.dto.JoinReqDto;
import com.cos.blog.domain.user.dto.LoginReqDto;
import com.cos.blog.domain.user.User;
import com.cos.blog.domain.user.dto.UpdateReqDto;

public class UserService {

    //회원 가입, 회원 수정, 회원 로그인, 로그아웃, 아이디 중복 체크

    public int 회원가입(JoinReqDto dto){
        return -1;
    }

    public User 로그인(LoginReqDto dto){
        return null;
    }

    public int 회원수정(UpdateReqDto dto){
        return -1;
    }

    public int 아이디중복체크(String username){
        return -1;
    }
}
