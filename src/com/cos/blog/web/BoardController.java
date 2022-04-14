package com.cos.blog.web;

import com.cos.blog.domain.board.dto.SavaReqDto;
import com.cos.blog.domain.user.User;
import com.cos.blog.service.BoardService;
import com.cos.blog.util.Script;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
@WebServlet("/board")
public class BoardController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPrecess(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPrecess(request, response);
    }

    protected void doPrecess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String cmd = request.getParameter("cmd");

        BoardService boardService = new BoardService();
        HttpSession session = request.getSession();
        if (cmd.equals("saveForm")) {
            User principal = (User)session.getAttribute("principal");
            if(principal != null) {
                response.sendRedirect("board/saveForm.jsp");
            }else{
                response.sendRedirect("user/loginForm");
            }
        } else if (cmd.equals("save")) {
            int userId = Integer.parseInt(request.getParameter("userId"));
            String title = request.getParameter("title");
            String content = request.getParameter("content");

            SavaReqDto dto = new SavaReqDto();
            dto.setTitle(title);
            dto.setContent(content);
            dto.setUserId(userId);

            int result = boardService.글쓰기(dto);

            if(result == 1){
                response.sendRedirect("index.jsp");
            }else{
                response.setContentType("text/html; charset=UTF-8");
                Script.back(response, "글쓰기 실패");
            }
        }
    }
}
