<%@page import="com.ch.shop.dto.Board"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	// DetailController 가 request에 저장해준 board 를 가져온다
	Board board = (Board)request.getAttribute("board");
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
  
<!-- include libraries(jQuery, bootstrap) -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet"> 
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote.min.js"></script>

<script>
$(()=>{
	$('#summernote').summernote();
	
	$('#summernote').summernote("code", "<%=board.getContent()%>");
	
	$(".bt_edit").click(()=>{
		if(confirm("수정하시겠습니까?")){
			$(".form1").attr({
				action:"/board/edit",
				method:"post"
			});
			
			$(".form1").submit();
			
		}
	});
	
	$(".bt_del").click(()=>{
		if(confirm("삭제하시겠습니까?")){
			location.href="/board/delete?board_id=<%=board.getBoard_id()%>"
		}
	});
	
	$(".bt_list").click(()=>{
		location.href="/board/list";
	});
	
});
</script>
</head>
<body>

<div class="container">
  <h2>상세 페이지</h2>
  <form class = "form1" action="/action_page.php">
  			<input type="hidden" name="board_id" value="<%=board.getBoard_id()%>">
  			
			<div class="form-group">
				<label for="email">제목:</label> 
				<input type="email" class="form-control" id="email" value="<%=board.getTitle()%>" name="title">
			</div>
			
			<div class="form-group">
				<label for="email">작성자:</label> 
				<input type="email" class="form-control" id="email" value="<%=board.getWriter()%>" name="writer">
			</div>
			
			<div class="form-group form-check">
				<label for="email">내용:</label> 
				<textarea type="email" class="form-control" id="summernote" placeholder="Enter email" name="content"></textarea>
			</div>
			
			<button type="button" class="bt_edit btn-primary">수정</button>
			<button type="button" class="bt_del btn-primary">삭제</button>
			<button type="button" class="bt_list btn-primary">목록</button>
  </form>
</div>

</body>
</html>
