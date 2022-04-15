package com.cos.blog.domain.board.dto;


import lombok.Data;

@Data
public class DetailResDto {
    private int id;
    private String title;
    private String content;
    private int readCount;
    private String username;

    //<> 브라우저에 보이기 위해 코드 작성
    public String getTitle(){

        return title.replaceAll("<","&lt;").replaceAll(">","&gt;");
    }

}
