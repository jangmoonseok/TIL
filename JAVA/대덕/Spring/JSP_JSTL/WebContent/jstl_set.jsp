<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% pageContext.setAttribute("name", "kim");%>
<%=pageContext.getAttribute("name")%><br>

<c:set scope="page" var="name" value="lee"/>
${name }

<c:set scope="request" var="name" value="park"/>
${requestScope.name }<br>

<h3>remove</h3>
<c:remove scope="request" var="name"/>
page : ${pageScope.name }<br>
request : ${requestScope.name }
 