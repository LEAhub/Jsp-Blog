<%--
  Created by IntelliJ IDEA.
  User: 82108
  Date: 2022-04-08
  Time: 오후 6:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>블로그</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<nav class="navbar navbar-expand-md bg-dark navbar-dark">
    <a class="navbar-brand" href="../index.jsp">블로그</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>


    <c:choose>
        <c:when test="${sessionScope.principal!=null}">
            <div class="collapse navbar-collapse" id="collapsibleNavbar">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="<%request.getContextPath();%>/board?cmd=saveForm">글쓰기</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<%request.getContextPath();%>/user?cmd=updateForm">회원정보</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<%request.getContextPath();%>/user?cmd=logout">로그아웃</a>
                    </li>
                </ul>
            </div>
        </c:when>
        <c:otherwise>
            <div class="collapse navbar-collapse" id="collapsibleNavbar">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="<%request.getContextPath();%>/user/joinForm.jsp">회원가입</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<%request.getContextPath();%>/user/loginForm.jsp">로그인</a>
                    </li>
                </ul>
            </div>
        </c:otherwise>
    </c:choose>

</nav>
<br>