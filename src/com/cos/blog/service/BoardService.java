package com.cos.blog.service;

import com.cos.blog.domain.board.Board;
import com.cos.blog.domain.board.BoardDao;
import com.cos.blog.domain.board.dto.DetailResDto;
import com.cos.blog.domain.board.dto.SavaReqDto;

import java.util.List;

public class BoardService {

    private BoardDao boardDao;

    public BoardService(){
        boardDao = new BoardDao();
    }

    public int 글쓰기(SavaReqDto dto){
        int result = boardDao.save(dto);
        return result;
    }

    public List<Board> 글목록보기(int page){
        return boardDao.findAll(page);
    }

    public int 게시글수(){
        int count = boardDao.countBoards();
        return count;
    }

    //하나의 서비스 안에 여러가지 DB관련 로직이 섞여 있다.
    public DetailResDto 글상세보기(int id){
        int result = boardDao.updateReadCount(id);
        if(result == 1){
            return boardDao.findById(id);
        }
        return null;
    }
}
