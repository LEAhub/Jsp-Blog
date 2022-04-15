package com.cos.blog.domain.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {
    private int id;
    private int userId;
    private String title;
    private String content;
    private int readCount;
    private Timestamp createDate;

    //<> 브라우저에 보이기 위해 코드 작성
    public String getTitle(){
        return title.replaceAll("<","&lt;").replaceAll(">","&gt;");
    }

}
