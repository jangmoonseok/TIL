package kr.or.ddit.basic.mvc.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.basic.mvc.vo.MemberVO;

/**
 * Service객체는 DAO에 설정된 메서드를 원하는 작업에 맞게 호출하여 결과를 받아와 받아온 결과를 Controller에게 보내주는 역할을 한다.
 * @author PC-25
 *
 */
public interface IMemberService {
	/**
	 * MemberVo에 담겨진 데이터를 DB에 insert하는 메서드
	 * 
	 * @param memVo DB에 insert할 데이터가 저장된 MemberVO객체
	 * @return insert작업에 성공 : 1, 실패 : 0
	 * @throws SQLException
	 */
	public int insertMember(MemberVO memVo);
	
	/**
	 * 회원ID를 매개변수로 받아 해당 회원 정보를 삭제하는 메서드
	 * 
	 * @param memId 삭제할 회원ID
	 * @return delete작업 성공 : 1, 실패 : 0
	 * @throws SQLException
	 */
	public int deleteMember(String memId);
	
	/**
	 * MemberVO에 담겨진 데이터를 DB에 update하는 메서드
	 * 
	 * @param memVo update할 데이터가 저장된 MemberVO객체
	 * @return update작업 성공 : 1, 실패 : 0
	 * @throws SQLException
	 */
	public int updateMember(MemberVO memVo);
	
	/**
	 * DB의 전체 회원정보를 가져와서 List에 담아서 반환하는 메서드
	 * 
	 * @return 전체 회원정보가 담긴 List
	 * @throws SQLException
	 */
	public List<MemberVO> getAllMember();
	
	/**
	 * 회원ID를 매개변수로 받아 DB에 저장된 해당 회원ID의 개수를 반환하는 메서드
	 * 
	 * @param memId 검색할 회원ID
	 * @return 검색된 회원ID의 개수
	 * @throws SQLException
	 */
	public int getMemberCount(String memId);
	
	
	public int updateMember2(Map<String, String> paramMap);
}
