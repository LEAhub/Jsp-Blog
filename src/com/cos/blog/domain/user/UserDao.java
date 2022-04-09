package com.cos.blog.domain.user;

import com.cos.blog.config.DB;
import com.cos.blog.domain.user.dto.JoinReqDto;
import com.cos.blog.domain.user.dto.LoginReqDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {
     
    public int save(JoinReqDto dto){

        Connection conn = DB.getConnection();
        PreparedStatement pstmt = null;
        final String USER_ROLE = "USER";

        try{
            String sql = "insert into user " +
                    "(username,password,email,address, userRole, createDate)"+
                    "values(?,?,?,?,?,now())";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dto.getUsername());
            pstmt.setString(2, dto.getPassword());
            pstmt.setString(3, dto.getEmail());
            pstmt.setString(4, dto.getAddress());
            pstmt.setString(5, USER_ROLE);
            int result = pstmt.executeUpdate();
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DB.closeDB(conn, pstmt);
        }
        return -1;
    }

    public void update(){

    }

    public void usernameCheck(){

    }

    public int findById(String username){
        Connection conn = DB.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int result = 1;
        try{
            String sql = "SELECT * FROM user WHERE username = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();
            if(rs.next()){ result = 1;;
            }
            else {result = -1;;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DB.closeDB(conn, pstmt, rs);
        }
        return result;

    }

    //스프링에서 Persistence API가 있음 자동으로 build 해줌
    public User findByNameAndPassword(LoginReqDto dto) {
        Connection conn = DB.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            String sql = "SELECT id, username, email, address FROM user " +
                    "WHERE username = ? AND password = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dto.getUsername());
            pstmt.setString(2, dto.getPassword());
            rs = pstmt.executeQuery();
            if(rs.next()){
                User user = User.builder().
                        id(rs.getInt("id")).
                        username(rs.getString("username")).
                        email(rs.getString("email")).
                        address(rs.getString("address")).build();
                return user;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DB.closeDB(conn, pstmt, rs);
        }
        return null;
    }
}
