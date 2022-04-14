package com.cos.blog.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Script {

    public static void back(HttpServletResponse response, String msg) {
        try{

            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('"+msg+"');");
            out.println("history.back();");
            out.println("</script>");
            out.flush(); //buffer 비우기
        }catch(Exception e){
            e.printStackTrace();
        }

        
    }
}
