<%--
  Created by IntelliJ IDEA.
  User: 82108
  Date: 2022-04-08
  Time: 오후 6:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../layout/popup.jsp"%>

<div class="container">
    <form action="/user?cmd=join" method="POST">
        <div class="form-group">
            <input type="text" name="username" class="form-control" placeholder="Enter name" required/>
        </div>
        <div class="form-group">
            <input type="password" name="password" class="form-control" placeholder="Enter password" required/>
        </div>
        <div class="form-group">
            <input type="email" name="email" class="form-control" placeholder="Enter email" required/>
        </div>
        <div class="form-group">
            <input type="text" name="address" class="form-control" placeholder="Enter address" required/>
        </div>
        <button type="submit" class="btn btn-primary">회원가입</button>
    </form>
</div>
</body>
</html>
