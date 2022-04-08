package com.cos.blog.test;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/test")
public class ApiServerTest extends HttpServlet {

    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html;charset=utf-8");
        String food = request.getParameter("food");
        String method = request.getParameter("method");
        PrintWriter out = response.getWriter();
        out.println(food);
        out.println(method);

        
    }
}
