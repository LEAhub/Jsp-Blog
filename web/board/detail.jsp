<%@ page import="com.cos.blog.domain.user.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<!--해당 페이지로 URL 접근하게 되면 파일 내부에서 세션 체크를 해야 한다.-->
<!--view와 로직이 구분되지 않음-->
<!---.jsp로 들어오는 모든 접근을 막아버리면 된다.-->


<div class="container">
    <c:if test="${sessionScope.principal.id == dto.userId}">
    <button onClick="deleteById(${dto.id})" class="btn btn-danger">삭제</button>
    </c:if>

    <script>
        function deleteById(boardId){

            var data = {
                boardId: boardId
            };
            console.log(JSON.stringify(data));
            console.log(data);
            $.ajax({
                type : "POST",
                url : "board?cmd=delete",
                data : JSON.stringify(data),
                contentType: "applitcation/json; charset=utf-8",
                dataType:"json"
            }).done(function(result){
                console.log(result);
                if(result.status == "ok"){
                    location.href = "index.jsp";
                }else{
                    alert("삭제 실패했습니다.");
                }
            });
        }

    </script>
    <br />
    <br />
    <h6 class="m-2">
        작성자 : <i>${dto.username}</i> 조회수 : <i>${dto.readCount}</i>
    </h6>
    <br />
    <h3 class="m-2">
        <b>${dto.title}</b>
    </h3>
    <hr />
    <div class="form-group">
        <div class="m-2">${dto.content}</div>
    </div>

    <hr />

    <!-- 댓글 박스 -->
    <div class="row bootstrap snippets">
        <div class="col-md-12">
            <div class="comment-wrapper">
                <div class="panel panel-info">
                    <div class="panel-heading m-2"><b>Comment</b></div>
                    <div class="panel-body">
                        <textarea id="reply__write__form" class="form-control" placeholder="write a comment..." rows="2"></textarea>
                        <br>
                        <button onclick="#" class="btn btn-primary pull-right">댓글쓰기</button>
                        <div class="clearfix"></div>
                        <hr />

                        <!-- 댓글 리스트 시작-->
                        <ul id="reply__list" class="media-list">

                            <!-- 댓글 아이템 -->
                            <li id="reply-1" class="media">
                                <div class="media-body">
                                    <img src="../img/customer1.png" width="50">
                                    <strong class="text-primary">홍길동</strong>
                                    <p>
                                        댓글입니다.
                                    </p>
                                </div>
                                <div class="m-2">

                                    <i onclick="#" class="material-icons">delete</i>

                                </div>
                            </li>

                        </ul>
                        <!-- 댓글 리스트 끝-->
                    </div>
                </div>
            </div>

        </div>
    </div>
    <!-- 댓글 박스 끝 -->
</div>

</body>
</html>