<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = (String)request.getAttribute("id");
	if(id == null){
%>
	{
		"flag" : "사용가능한 아이디입니다."
	}	
<% 	}else{
%>	
	{
		"flag" : "이미 가입된 아이디입니다."
	}
<% 
	}
%>
