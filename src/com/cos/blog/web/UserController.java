package com.cos.blog.web;

import com.cos.blog.domain.user.dto.JoinReqDto;
import com.cos.blog.domain.user.dto.LoginReqDto;
import com.cos.blog.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/user")
public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPrecess(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPrecess(request, response);
    }

    //http://localhost:8080/user?cmd=
    protected void doPrecess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String cmd = request.getParameter("cmd");
        UserService service = new UserService();

        //http://localhost:8080/user?cmd=loginForm
        if(cmd.equals("loginForm")){
            response.sendRedirect("user/loginForm.jsp");
        }else if(cmd.equals("login")){
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            LoginReqDto dto = new LoginReqDto();
            dto.setUsername(username);
            dto.setPassword(password);

            service.로그인(dto);
        }else if(cmd.equals("joinForm")){
            response.sendRedirect("user/joinForm.jsp");
        }else if(cmd.equals("join")){
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            JoinReqDto dto = new JoinReqDto();
            dto.setUsername(username);
            dto.setPassword(password);
            dto.setEmail(email);
            dto.setAddress(address);
            System.out.println(dto);
            service.회원가입(dto);
        }
    }
}
