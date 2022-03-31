<%@page import="kr.or.ddit.member.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.ibatis.config.SqlMapClientUtil"%>
<%@page import="com.ibatis.sqlmap.client.SqlMapClient"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 요청시 데이타 받기

// db를 통해서 crud처리
// mapper-xml 파일에 접근한다
// mapper-xml 파일에 접근할 수 있는 객체가 필요 - SqlMapClient
SqlMapClient smc = SqlMapClientUtil.getSqlMapClient();

// mapper를 실행
List<MemberVO> list = smc.queryForList("member.selectAll");

// 처리후 결과를 얻는다

// 결과를 가지고 응답데이터를 생성한다

%>

[
<%
	for(int i = 0; i < list.size(); i++){
		MemberVO memVo = list.get(i);
		if(i > 0) out.print(",");	
%>
	{
		"id" : "<%= memVo.getMem_id() %>",
		"name" : "<%= memVo.getMem_name() %>",
		"pass" : "<%= memVo.getMem_pass() %>",
		"tel" : "<%= memVo.getMem_hp() %>",
		"addr" : "<%= memVo.getMem_add1() %>"
	}
<% 		
	}
%>
]

