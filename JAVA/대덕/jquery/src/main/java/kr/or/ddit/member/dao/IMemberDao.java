package kr.or.ddit.member.dao;

import java.util.List;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.ZipVO;

public interface IMemberDao {
	
	public List<MemberVO> selectAll();
	
	//중복검사
	public String idcheck(String mem_id);
	
	//우편번호 검색
	public List<ZipVO> zipList(String dong);
	
	//회원가입
	public String insertMember(MemberVO vo);
}
