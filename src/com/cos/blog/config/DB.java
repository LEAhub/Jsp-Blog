package com.cos.blog.config;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;

public class DB {
    public static Connection getConnection() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/TestDB");
            Connection conn = ds.getConnection();
            System.out.println("success DB Connection");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
