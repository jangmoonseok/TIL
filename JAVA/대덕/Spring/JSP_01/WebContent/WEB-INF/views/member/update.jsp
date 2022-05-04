<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

td,th{
		text-align: center;
		width: 100px;
	}
</style>
</head>
<body>
	<form action="update?oldId=${member.id }" method="post">	
		<table border="1">
				<tr>
					<th>아이디</th>
					<td><input type="text" name="id" value="${member.id }"/></td>
				</tr>
				<tr>
					<th>패스워드</th>
					<td><input type="password" name="pwd" value="${member.pwd }"/></td>
				</tr>
				<tr>
					<td colspan="2">
						<button type="submit">수정</button>
					</td>
				</tr>
		</table>
	</form>
</body>
</html>