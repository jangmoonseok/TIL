package com.jsp.vo;

public class Member implements Comparable<Member>{
	private String id;
	private String pwd;
	
	
	public Member() {}
	public Member(String id, String pwd) {
		super();
		this.id = id;
		this.pwd = pwd;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	@Override
	public int compareTo(Member o) {
		return id.compareTo(o.getId());
	}
	

}
