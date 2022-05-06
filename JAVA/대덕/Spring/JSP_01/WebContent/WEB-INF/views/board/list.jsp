<%@page import="java.util.Arrays"%>
<%@page import="java.util.Comparator"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.jsp.vo.Board"%>
<%@page import="java.util.Collections"%>
<%@page import="com.jsp.vo.Member"%>
<%@page import="java.util.List"%>
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
</head>
<body>

	<div style="min-width:1200px;margin:0 auto;">
		 
		<h1 style="text-align:center;">게시판 목록</h1>
		<hr style="width:70%;margin:0 auto;"/>
		<br/>
		<div style="width:70%;margin:0 auto;position:relative;overflow:hidden;">
			<%
				if(user.equals("")){
			%>			
			<button type="button" onclick="location.href = 'login'" style="display:block;float:right;">로그인</button>
			<%	
				}else{
			%>
			<button type="button" onclick="location.href = 'boardRegist'" style="display:block;float:right;">글 등록</button>				
			<%
				}
			%>
		</div>
		<br/>
		<table border="1" style="width:70%;margin:0 auto;">
			<tr>
				<th style="width:10%;">번호</th>
				<th style="width:30%;">제목</th>
				<th style="width:15%;">작성자</th>
				<th style="width:15%;">조휘수</th>
				<th style="width:30%;">등록날짜</th>
			</tr>
			<%
				List<Board> boardList = (List<Board>)request.getAttribute("boardList");
				Collections.sort(boardList);
				if (boardList!=null) for(Board board : boardList){
				pageContext.setAttribute("board",board);
				pageContext.setAttribute("boardNum",board.getBno());
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				pageContext.setAttribute("date",format.format(board.getRegDate()) );
			%>
			<tr style="text-align:center; cursor:pointer;" onclick="location.href = '<%= request.getContextPath()%>/board/boardDetail?boardNum=${boardNum} '">
				<td>${boardNum }</td>
				<td>${board.title }</td>
				<td>${board.writer}</td>
				<td>${board.viewCnt}</td>
				<td>${date}</td>
			</tr>
			<%		
				}else{
			%>
			<tr >
				<td colspan="3">해당내용이 없습니다.</td>
			</tr>
			<%		
					
				}
			%>
		</table>
		
	</div>
</body>
</html>








