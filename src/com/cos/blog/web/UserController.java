package com.cos.blog.web;

import com.cos.blog.domain.user.User;
import com.cos.blog.domain.user.UserDao;
import com.cos.blog.domain.user.dto.JoinReqDto;
import com.cos.blog.domain.user.dto.LoginReqDto;
import com.cos.blog.service.UserService;
import com.cos.blog.util.Script;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
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
        response.setContentType("text/html; charset=UTF-8");
        String cmd = request.getParameter("cmd");
        UserService service = new UserService();

        //http://localhost:8080/user?cmd=loginForm
        if(cmd.equals("loginForm")){
            RequestDispatcher dis =
                    request.getRequestDispatcher("/user/loginForm.jsp");
            dis.forward(request,response);
//            response.sendRedirect("user/loginForm.jsp");
        }else if(cmd.equals("login")){
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            LoginReqDto dto = new LoginReqDto();
            dto.setUsername(username);
            dto.setPassword(password);

            User userEntity = service.로그인(dto);
            if(userEntity != null){
                HttpSession session = request.getSession();
                session.setAttribute("principal", userEntity);
                response.sendRedirect("index.jsp");
            }else{
                Script.back(response, "다시 시도해주세요");
            }
        }else if(cmd.equals("joinForm")){
//            RequestDispatcher dis =
//                    request.getRequestDispatcher("/user/joinForm.jsp");
//            dis.forward(request,response);
            response.sendRedirect("/user/joinForm.jsp");
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
            int result = service.회원가입(dto);
            if(result == 1){
                response.sendRedirect("index.jsp");
            }else{
                String msg = "다시 시도해주세요!";
                Script.back(response, msg);
            }
        }else if(cmd.equals("usernameCheck")){
            BufferedReader br = request.getReader();
            String username = br.readLine();
            System.out.println(username);
            int result  = service.유저네임중복체크(username);
            PrintWriter out = response.getWriter();
            if(result == 1){
                out.print("ok");
            }else{
                out.print("no");
            }
            out.flush();
        }else if(cmd.equals("logout")){
            HttpSession session = request.getSession();
            session.invalidate();
            response.sendRedirect("index.jsp");
        }
    }
}
