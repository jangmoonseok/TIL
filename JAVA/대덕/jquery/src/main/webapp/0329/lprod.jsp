<%@page import="kr.or.ddit.lprod.vo.LprodVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.ibatis.config.SqlMapClientUtil"%>
<%@page import="com.ibatis.sqlmap.client.SqlMapClient"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
SqlMapClient smc = SqlMapClientUtil.getSqlMapClient();

List<LprodVO> list = smc.queryForList("lprod.selectAll");
	
%>

[
<%
	for(int i = 0; i < list.size(); i++){
		LprodVO vo = list.get(i);
		if(i > 0) out.print(",");
	
%>
	{
		"lprod_id" : "<%= vo.getLprod_id() %>",
		"lprod_gu" : "<%= vo.getLprod_gu() %>",
		"lprod_nm" : "<%= vo.getLprod_nm() %>"
	}
<%
	}
%>
]
