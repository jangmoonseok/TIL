<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>JSP : Java Server Page</h1>
<%
	request.setCharacterEncoding("UTF-8");

	String userId = request.getParameter("id");
	String userName = request.getParameter("name");
%>

<p><%= userId %>님 환영합니다</p>
<p><%= userName %>님 즐거운 하루 되세요</p>


</body>
</html>