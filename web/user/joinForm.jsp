<%--
  Created by IntelliJ IDEA.
  User: 82108
  Date: 2022-04-08
  Time: 오후 6:07
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
<script language="javascript">
    // opener관련 오류가 발생하는 경우 아래 주석을 해지하고, 사용자의 도메인정보를 입력합니다. ("팝업API 호출 소스"도 동일하게 적용시켜야 합니다.)
    //document.domain = "abc.go.kr";

    function goPopup(){
        // 주소검색을 수행할 팝업 페이지를 호출합니다.
        // 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
        var pop = window.open("/user/jusoPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes");

        // 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://www.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
        //var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes");
    }


    function jusoCallBack(roadFullAddr){
        // 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
        var address = document.querySelector("#address");
        address.value = roadFullAddr;
        // document.form.address.value = roadFullAddr;
    }
</script>
</head>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../layout/header.jsp"%>

<body>
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
        <div class="d-flex justify-content-end">
            <button type="button" class="btn btn-info" onClick="goPopup();" value="팝업_domainChk">주소검색</button>
        </div>
        <div class="form-group">
            <input type="text" id="address" name="address" class="form-control" placeholder="Enter address" required readonly/>
        </div>
        <button type="submit" class="btn btn-primary">회원가입</button>
    </form>
</div>
</body>
</html>
