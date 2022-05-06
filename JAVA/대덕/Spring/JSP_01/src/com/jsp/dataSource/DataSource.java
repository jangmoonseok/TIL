package com.jsp.dataSource;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.jsp.vo.Board;
import com.jsp.vo.Member;

public class DataSource {
	private Map<String, Member> memberList = new HashMap<String, Member>();
	private Map<String, Board> boardList = new HashMap<String, Board>();
	
	private static DataSource instance = new DataSource();
	private DataSource() {
		for(int i=0;i<20;i++) {
			String temp = "mimi"+i;		
						
			memberList.put(temp, new Member(temp,temp));
			boardList.put(""+i, new Board(i,temp,temp,temp,new Date(),0));
		} 
		
	}
	public static DataSource getInstance (){
		return instance;
	}
	public Map<String, Member> getMemberList() {
		return memberList;
	}
	public Map<String, Board> getBoardList() {
		return boardList;
	}
	
	
	
	
}
