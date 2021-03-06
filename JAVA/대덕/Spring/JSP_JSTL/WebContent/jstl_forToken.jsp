<%@page import="java.util.StringTokenizer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String source = "a,b,c,d,e,f,g";

	String[] split = source.split(",");
	if(split != null) for(String str : split){
		out.println(str + "<br>");
	}
	
	out.println("<hr>");
	StringTokenizer tokens = new StringTokenizer(source, ",");
	while(tokens.hasMoreTokens()){
		out.println(tokens.nextToken() + "<br>");
	}
%>
<hr>
JSTL:split<br>
<c:set var="source" value="a,b,c,e,d,f,g"/>
<c:set var="split" value="${source.split(',') }"/>
<c:forEach items="${split }" var="str">
	${str }<br>
</c:forEach>
<hr>
JSTL:tokenizer<br>
<c:forTokens var="str" items="a,b,c,e,d,f,g" delims=",">
	${str }<br>
</c:forTokens>