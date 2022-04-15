package com.cos.blog.web;

import com.cos.blog.domain.board.Board;
import com.cos.blog.domain.board.BoardDao;
import com.cos.blog.domain.board.dto.SavaReqDto;
import com.cos.blog.domain.user.User;
import com.cos.blog.service.BoardService;
import com.cos.blog.util.Script;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

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
//                RequestDispatcher dis =
//                        request.getRequestDispatcher("/board/saveForm.jsp");
//                dis.forward(request,response);
                response.sendRedirect("board/saveForm.jsp");
            }else{
//                RequestDispatcher dis =
//                        request.getRequestDispatcher("/user/loginForm.jsp");
//                dis.forward(request,response);
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
        } else if (cmd.equals("list")) {
            int page = Integer.parseInt(request.getParameter("page"));
            List<Board> boards = boardService.글목록보기(page);
            request.setAttribute("boards", boards);

            //게시글 수를 얻어와 연산함
            int count = boardService.게시글수();
            int maxPage = count / 4;
            if(count % 4 != 0 ) maxPage++;
            System.out.println(maxPage);

            //마지막 페이지인지 확인한다.
            Boolean maxCheck = false;
            if(page == maxPage-1) maxCheck = true;
            request.setAttribute("maxCheck", maxCheck);

            //첫번째 페이지인지 확인한다.
            Boolean minCheck = false;
            if(page == 0) minCheck = true;
            request.setAttribute("minCheck", minCheck);

            RequestDispatcher dis =
                    request.getRequestDispatcher("/board/list.jsp");
            dis.forward(request,response);
        }
    }
}
