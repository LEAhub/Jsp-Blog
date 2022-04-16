package com.cos.blog.config;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DB {
    public static Connection getConnection() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/TestDB");
            Connection conn = ds.getConnection();
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeDB(Connection conn, PreparedStatement pstmt){
        if(conn != null)try{conn.close();}catch (Exception e){}
        if(pstmt != null)try{pstmt.close();}catch (Exception e){}

    }

    public static void closeDB(Connection conn, PreparedStatement pstmt, ResultSet rs){
        if(conn != null)try{conn.close();}catch (Exception e){}
        if(pstmt != null)try{pstmt.close();}catch (Exception e){}
        if(rs != null)try{rs.close();}catch (Exception e){}

    }


}
