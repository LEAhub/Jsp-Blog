package com.cos.blog.domain.board;

import com.cos.blog.config.DB;
import com.cos.blog.domain.board.dto.SavaReqDto;
import com.cos.blog.domain.user.dto.JoinReqDto;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class BoardDao {


    public int save(SavaReqDto dto){

        Connection conn = DB.getConnection();
        PreparedStatement pstmt = null;
        final String USER_ROLE = "USER";

        try{
            String sql = "insert into board " +
                    "(userId,title,content,createDate)"+
                    "values(?,?,?,now())";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, dto.getUserId());
            pstmt.setString(2, dto.getTitle());
            pstmt.setString(3, dto.getContent());
            int result = pstmt.executeUpdate();
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DB.closeDB(conn, pstmt);
        }
        return -1;
    }

}
