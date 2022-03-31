<%@page import="kr.or.ddit.buyer.vo.BuyerVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	BuyerVO vo = (BuyerVO)request.getAttribute("idByDetail");
%>

{
	"id" : "<%= vo.getBuyer_id() %>",
	"name" : "<%= vo.getBuyer_name() %>",
	"lgu" : "<%= vo.getBuyer_lgu() %>",
	"bank" : "<%= vo.getBuyer_bank() %>",
	"bank_no" : "<%= vo.getBuyer_bankno() %>",
	"bank_name" : "<%= vo.getBuyer_bankname() %>",
	"mail" : "<%= vo.getBuyer_mail() %>",
	"zip" : "<%= vo.getBuyer_zip() %>",
	"addr1" : "<%= vo.getBuyer_add1() %>",
	"addr2" : "<%= vo.getBuyer_add2() %>"
}