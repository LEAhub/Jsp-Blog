package com.cos.blog.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Script {

    public static void back(HttpServletResponse response, String msg)
                throws IOException {
        PrintWriter out = response.getWriter();
        out.println("<scrpit>");
        out.println("alret("+msg+")");
        out.println("history.back();");
        out.println("</scrpit>");
        out.flush(); //buffer 비우기
        
    }
}
