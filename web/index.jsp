<%--
  Created by IntelliJ IDEA.
  User: 82108
  Date: 2022-04-07
  Time: 오후 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
//  RequestDispatcher dis =
//          request.getRequestDispatcher("board/list.jsp");
//  dis.forward(request,response);
  response.sendRedirect("board?cmd=list&page=0");
%>