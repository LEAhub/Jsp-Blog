package com.cos.blog.web;

import com.cos.blog.domain.board.Board;
import com.cos.blog.domain.board.BoardDao;
import com.cos.blog.domain.board.dto.DeleteReqDto;
import com.cos.blog.domain.board.dto.DeleteResDto;
import com.cos.blog.domain.board.dto.DetailResDto;
import com.cos.blog.domain.board.dto.SavaReqDto;
import com.cos.blog.domain.user.User;
import com.cos.blog.service.BoardService;
import com.cos.blog.util.Script;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
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
        response.setContentType("text/html; charset=UTF-8");
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

            //진행도 확인 processBar 구현
            double currentPercentage = (double)page / maxPage;
            int curPer = (int)(currentPercentage * 100);
            int first = 100 / maxPage;
            request.setAttribute("curPer",curPer + first);

            RequestDispatcher dis =
                    request.getRequestDispatcher("/board/list.jsp");
            dis.forward(request,response);
        }else if(cmd.equals("detail")){
            int id = Integer.parseInt(request.getParameter("id"));
            DetailResDto dto = boardService.글상세보기(id); //board 테이블 + user 테이블 = 조인된 데이터

            if(dto == null){
                Script.back(response,"상세보기에 실패하였습니다.");
            }else{
                request.setAttribute("dto", dto);
                RequestDispatcher dis
                        = request.getRequestDispatcher("board/detail.jsp");
                dis.forward(request,response);
            }

        } else if (cmd.equals("delete")) {
            //js에서 json으로 받아왔기 때문에 BufferedReader 객체 사용
            BufferedReader br = request.getReader();
            String data = br.readLine();
//            System.out.println("data :"  + data);
            //Gson 라이브러리로 json과 객체 형변환 가능
            Gson gson = new Gson();
            DeleteReqDto dto = gson.fromJson(data, DeleteReqDto.class);
//            System.out.println("dto :"  +dto);

            //글삭제 service 구현
            int result = boardService.글삭제(dto.getBoardId());
            DeleteResDto resDto = new DeleteResDto();
            if (result == 1) {
                resDto.setStatus("ok");
            }else{
                resDto.setStatus("fail");
            }
            String respData = gson.toJson(resDto);
            System.out.println("respData : " + respData);
            PrintWriter out = response.getWriter();
            out.print(respData);
        }
    }
}
