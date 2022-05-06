package com.jsp.vo;

import java.util.Date;

public class Board implements Comparable<Board>{
	private int bno;
	private String title;
	private String writer;
	private String content;
	private Date regDate;
	private int viewCnt;
	
	public Board() {}
	public Board(int bno, String title, String writer, String content, Date regDate, int viewCnt) {
		super();
		this.bno = bno;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.regDate = regDate;
		this.viewCnt = viewCnt;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}
	@Override
	public String toString() {
		return "Board [bno=" + bno + ", title=" + title + ", writer=" + writer + ", content=" + content + ", regDate="
				+ regDate + ", viewCnt=" + viewCnt + "]";
		
	}
	@Override
	public int compareTo(Board o) {
		return bno - o.bno;
	}


}