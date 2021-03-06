package com.cos.blog.service;

import com.cos.blog.domain.user.UserDao;
import com.cos.blog.domain.user.dto.JoinReqDto;
import com.cos.blog.domain.user.dto.LoginReqDto;
import com.cos.blog.domain.user.User;
import com.cos.blog.domain.user.dto.UpdateReqDto;

public class UserService {

    private UserDao userDao;

    public UserService() {
        userDao = new UserDao();
    }

    //회원 가입, 회원 수정, 회원 로그인, 로그아웃, 아이디 중복 체크

    public int 회원가입(JoinReqDto dto){
        int result =  userDao.save(dto);
        return result;
    }

    public User 로그인(LoginReqDto dto){
        return userDao.findByNameAndPassword(dto);
    }

    public int 회원수정(UpdateReqDto dto){
        return -1;
    }

    public int 유저네임중복체크(String username){
        int result = userDao.findById(username);
        return result;
    }
}
