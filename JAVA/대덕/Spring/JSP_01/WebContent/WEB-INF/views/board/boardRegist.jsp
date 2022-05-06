<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Cookie[] cookies = request.getCookies();
	String user = "";
	
	if(cookies != null){
		for(Cookie cookie : cookies){
			if(cookie.getName().equals("user")){
				user = cookie.getValue();
			}
		}
	}
%>
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
	<h1>글 등록</h1>
	<br>
	<form action="boardRegist?user=<%= user %>" method="post">	
		<button type="submit">등록</button>
		<button type="button" onclick="location.href = '<%= request.getContextPath()%>/board/list'">목록</button>
		<hr>
		<div id="container">
			<label>제목</label>
			<span><input type="text" name="title"></span><br>
			<label>내용</label><br>
			<textarea cols="30" rows="10"  name="content"></textarea><br>
		</div>
	</form>
</body>
</html>