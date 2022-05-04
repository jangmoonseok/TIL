<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% pageContext.setAttribute("message","안녕하세요."); %> 
<% pageContext.setAttribute("today",new Date()); %> 
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<% out.println(pageContext.getAttribute("message")); %> <br/>
	<%=pageContext.getAttribute("message") %> <br/>
	${message }<br/>
	
	<hr/>
	
	<% out.println(pageContext.getAttribute("today")); %> <br/>
	<%=pageContext.getAttribute("today") %> <br/>
	${today } 
	
</body>
</html>










