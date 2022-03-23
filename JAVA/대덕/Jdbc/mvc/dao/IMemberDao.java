package kr.or.ddit.basic.mvc.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.basic.mvc.vo.MemberVO;

/**
 * 실제 DB와 연결해서 SQL문을 수행하여 결과를 작성해서 Service에 전달하는 Dao의 Interface
 * 
 * 메서드 하나가 DB와 관련된 작업 1개를 수행한다.
 * @author PC-25
 *
 */
public interface IMemberDao {
	/**
	 * MemberVo에 담겨진 데이터를 DB에 insert하는 메서드
	 * 
	 * @param conn java.sql.Connection객체
	 * @param memVo DB에 insert할 데이터가 저장된 MemberVO객체
	 * @return insert작업에 성공 : 1, 실패 : 0
	 * @throws SQLException
	 */
	public int insertMember(Connection conn,  MemberVO memVo) throws SQLException;
	
	/**
	 * 회원ID를 매개변수로 받아 해당 회원 정보를 삭제하는 메서드
	 * 
	 * @param conn java.sql.Connection객체
	 * @param memId 삭제할 회원ID
	 * @return delete작업 성공 : 1, 실패 : 0
	 * @throws SQLException
	 */
	public int deleteMember(Connection conn, String memId) throws SQLException;
	
	/**
	 * MemberVO에 담겨진 데이터를 DB에 update하는 메서드
	 * 
	 * @param conn java.sql.Connection객체
	 * @param memVo update할 데이터가 저장된 MemberVO객체
	 * @return update작업 성공 : 1, 실패 : 0
	 * @throws SQLException
	 */
	public int updateMember(Connection conn, MemberVO memVo) throws SQLException;
	
	/**
	 * DB의 전체 회원정보를 가져와서 List에 담아서 반환하는 메서드
	 * 
	 * @param conn java.sql.Connection객체
	 * @return 전체 회원정보가 담긴 List
	 * @throws SQLException
	 */
	public List<MemberVO> getAllMember(Connection conn) throws SQLException;
	
	/**
	 * 회원ID를 매개변수로 받아 DB에 저장된 해당 회원ID의 개수를 반환하는 메서드
	 * 
	 * @param conn java.sql.Connection객체
	 * @param memId 검색할 회원ID
	 * @return 검색된 회원ID의 개수
	 * @throws SQLException
	 */
	public int getMemberCount(Connection conn, String memId) throws SQLException;
	
	
	/**
	 * Map의 정보를 이용하여 회원정보 중 원하는 컬럼을 수정하는 메서드
	 * Key : 회원ID(memId), 수정할 컬럼명(field), 수정할 데이터(data)
	 * 
	 * @param conn conn java.sql.Connection객체
	 * @param paramMap 수정할 회원ID, 수정할 컬럼명, 수정할 데이터를 저장된 Map객체
	 * @return update작업 성공 : 1, 실패 : 0
	 * @throws SQLException
	 */
	public int updateMember2(Connection conn, Map<String, String> paramMap) throws SQLException;
	
	
}
