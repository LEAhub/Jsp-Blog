package com.cos.blog.service;

import com.cos.blog.domain.board.BoardDao;
import com.cos.blog.domain.board.dto.SavaReqDto;

public class BoardService {

    private BoardDao boardDao;

    public BoardService(){
        boardDao = new BoardDao();
    }

    public int 글쓰기(SavaReqDto dto){
        int result = boardDao.save(dto);
        return -1;
    }
}
