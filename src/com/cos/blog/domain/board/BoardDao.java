package com.cos.blog.domain.board;

import com.cos.blog.config.DB;
import com.cos.blog.domain.board.dto.SavaReqDto;
import com.cos.blog.domain.user.User;
import com.cos.blog.domain.user.dto.JoinReqDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardDao {


    public int countBoards(){
        Connection conn = DB.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;
        try{
            String sql = "SELECT COUNT(*) FROM board";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while(rs.next()){
                count = rs.getInt(1);
            }
            return count;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DB.closeDB(conn, pstmt, rs);
        }
        return 0;
    }

    public List<Board> findAll(int page){
        Connection conn = DB.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Board> boards = new ArrayList<>();
        try{
            String sql = "SELECT * FROM board ORDER BY id DESC LIMIT ?,4";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, page*4);
            rs = pstmt.executeQuery();
            while(rs.next()){
                Board board = Board.builder()
                        .id(rs.getInt("id"))
                        .title(rs.getString("title"))
                        .content(rs.getString("content"))
                        .readCount(rs.getInt("readCount"))
                        .userId(rs.getInt("userId"))
                        .createDate(rs.getTimestamp("createDate"))
                        .build();
                boards.add(board);
            }
            return boards;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DB.closeDB(conn, pstmt, rs);
        }
        return null;
    }

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
