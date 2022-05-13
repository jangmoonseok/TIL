<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	int price = 10000000;

	String priceStr = new DecimalFormat("#,###").format(price);
	out.println("￦" + priceStr);
%>
<hr>

<c:set var="price" value="10000000"/>
￦<fmt:formatNumber value="${price }" pattern="#,###"/>

