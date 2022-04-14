<%@ page import="com.cos.blog.domain.user.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<!--해당 페이지로 URL 접근하게 되면 파일 내부에서 세션 체크를 해야 한다.-->
<!--view와 로직이 구분되지 않음-->
<!---.jsp로 들어오는 모든 접근을 막아버리면 된다.-->

<div class="container">
    <form action="#" method="POST">

        <div class="form-group">
            <label for="title">Title:</label>
            <input type="text" class="form-control" placeholder="title" id="title" name="title">
        </div>

        <div class="form-group">
            <label for="content">Content:</label>
            <textarea id="summernote" class="form-control" rows="5" id="content" name="content"></textarea>
        </div>

        <button type="submit" class="btn btn-primary">글쓰기 등록</button>
    </form>
</div>
<script>
    $('#summernote').summernote({
        placeholder: '여기서 글을 작성하세요',
        tabsize: 2,
        height: 300
    });
</script>

</body>
</html>

