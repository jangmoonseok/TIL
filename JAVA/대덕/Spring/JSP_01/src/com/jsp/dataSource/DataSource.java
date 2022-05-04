package com.jsp.dataSource;

import java.util.HashMap;
import java.util.Map;

import com.jsp.vo.Member;

public class DataSource {
	private Map<String, Member> memberList = new HashMap<String, Member>();
	
	private static DataSource instance = new DataSource();
	private DataSource() {
		for(int i=0;i<20;i++) {
			String temp = "mimi"+i;
			
			memberList.put(temp, new Member(temp,temp));
		} 
		
	}
	public static DataSource getInstance (){
		return instance;
	}
	public Map<String, Member> getMemberList() {
		return memberList;
	}
	public void setMemberList(Map<String, Member> memberList) {
		this.memberList = memberList;
	}
	public static void setInstance(DataSource instance) {
		DataSource.instance = instance;
	}
	
	
	
	
}
