<%@page import="java.util.Collections"%>
<%@page import="com.jsp.vo.Member"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div style="min-width:1200px;margin:0 auto;">
		 
		<h1 style="text-align:center;">회원리스트</h1>
		<hr style="width:70%;margin:0 auto;"/>
		<br/>
		<div style="width:70%;margin:0 auto;position:relative;overflow:hidden;">
			<button type="button" onclick="location.href = 'regist'" style="display:block;float:right;">회원가입</button>
		</div>
		<br/>
		<table border="1" style="width:70%;margin:0 auto;">
			<tr>
				<th style="width:10%;">번호</th>
				<th style="width:40%;">아이디</th>
				<th style="width:40%;">패스워드</th>
			</tr>
			<%
				List<Member> memberList = (List<Member>)request.getAttribute("memberList");
				Collections.sort(memberList);
				int count = 0;
				if (memberList!=null) for(Member member : memberList){
				pageContext.setAttribute("member",member);
				pageContext.setAttribute("num",count++);
			%>
			<tr style="text-align:center">
				<td>${num }</td>
				<td>${member.id }</td>
				<td>${member.pwd }</td>
				<td style="width: 100px;">
					<button type="button" onclick="location.href = '<%=request.getContextPath() %>/member/detail?id=${member.id}'" style="width: 100%">상세정보</button>
				</td>
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








