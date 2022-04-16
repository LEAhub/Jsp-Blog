package com.cos.blog.config;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class ForbiddenUrlConfig implements Filter {
    List<String> excludeUrls;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        
        // 다운 캐스팅
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        request.setCharacterEncoding("UTF-8");
        //아래의 잘못된 접근입니다. 한글이 깨지기 때문에 인코딩 설정함
        response.setContentType("text/html; charset=UTF-8");


        //uri 확인하기 위해 출력 test
        System.out.println("ForbiddenUrlConfig 접근");
        System.out.println(request.getServletPath());


        String path = request.getServletPath();

        //기본 경로와 index.jsp로 직접 입력해서 들어올 경우에는 xml에서 설정한 filter 적용 제외
        if(!excludeUrls.contains(path)){
            PrintWriter out = response.getWriter();
            out.print("잘못된 접근입니다.");
            out.flush();
        }

        filterChain.doFilter(request,response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String excludeUrlsPattern = filterConfig.getInitParameter("excludeUrls");
        excludeUrls = Arrays.asList(excludeUrlsPattern.split(","));
    }
}
