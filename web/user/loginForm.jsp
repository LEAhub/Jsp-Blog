<%--
  Created by IntelliJ IDEA.
  User: 82108
  Date: 2022-04-08
  Time: 오후 6:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="../layout/header.jsp"%>
<div class="container">
    <!-- submit 될 때 무조건 실행됨
         현재 주소에 남아있지, 다른 사이트로 이동하지 않음
         onSubmit은 true를 return할 때 다음을 수행하고
         false를 return 할 때는 다음을 수행하지 않음-->
    <form action="/user?cmd=login" method="POST">
        <div class="form-group">
            <input type="text" name="username" id="username" class="form-control" placeholder="Enter name" required/>
        </div>
        <div class="form-group">
            <input type="password" name="password" class="form-control" placeholder="Enter password" required/>
        </div>
        <button type="submit" class="btn btn-primary">로그인</button>
    </form>
</div>
</body>
</html>
