<%@page import="com.jsp.vo.Board"%>
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
	<h1>글 상세보기</h1>
	<br>
	<%
		Board board = (Board)request.getAttribute("board");
		if(user.equals(board.getWriter())){
	%>
	<button type="button" onclick="location.href = '<%= request.getContextPath()%>/board/boardUpdate?boardNum=${board.bno }'">수정</button>
	<button type="button" onclick="location.href = '<%= request.getContextPath()%>/board/boardDelete?boardNum=${board.bno }'">삭제</button>
	<button type="button" onclick="location.href = '<%= request.getContextPath()%>/board/list'">목록</button>	
	<%		
		}else{
	%>
	<button type="button" onclick="location.href = '<%= request.getContextPath()%>/board/list'">목록</button>	
	<%		
		}
	%>
	<hr>
	<div id="container">
		<label>제목</label>
		<span>${board.title }</span><br>
		<label>내용</label><br>
		<textarea cols="30" rows="10"  name="content" disabled="disabled">${board.content }</textarea><br>
		<label>작성자</label>
		<span>${board.writer }</span><br>
		<label>조회수</label>
		<span>${board.viewCnt }</span>
	</div>
</body>
</html>