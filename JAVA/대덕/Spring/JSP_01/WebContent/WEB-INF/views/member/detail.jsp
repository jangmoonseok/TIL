<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	td,th{
		text-align: center;
		width: 100px;
	}
</style>
</head>
<body>
	<table border="1">
		<tr>
			<th>아이디</th>
			<td>${member.id }</td>
		</tr>
		<tr>
			<th>패스워드</th>
			<td>${member.pwd }</td>
		</tr>
		<tr>
			<td colspan="2">
				<button type="button" onclick="location.href='<%=request.getContextPath() %>/member/update?id=${member.id}'">수정</button>
				<button type="button" onclick="location.href='<%=request.getContextPath() %>/member/delete?id=${member.id}'">삭제</button>
			</td>
		</tr>
	</table>
</body>
</html>