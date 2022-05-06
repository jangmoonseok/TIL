<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	th{
		width: 100px;
		text-align: center;
	}
	#container{
		margin: 20px 50px;
	}
	label{
		font-weight: bold;
		display:inline-block;
		width: 50px;
	}
</style>
</head>
<body>
	<h1>글 수정</h1>
	<br>
	<form action="boardUpdate?boardNum=${board.bno }" method="post">	
		<button type="submit">수정</button>
		<hr>
		<div id="container">
			<label>제목</label>
			<span><input type="text" name="title" value="${board.title }"></span><br>
			<label>내용</label><br>
				<textarea cols="30" rows="10"  name="content">${board.content }</textarea>
			<br>
			<label>작성자</label>
			<span>${board.writer }</span><br>
			<label>조회수</label>
			<span>${board.viewCnt }</span>
		</div>
	</form>
</body>
</html>