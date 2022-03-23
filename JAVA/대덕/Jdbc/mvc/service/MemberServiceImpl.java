package kr.or.ddit.basic.mvc.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.basic.mvc.dao.IMemberDao;
import kr.or.ddit.basic.mvc.dao.MemberDaoImpl;
import kr.or.ddit.basic.mvc.vo.MemberVO;
import kr.or.ddit.util.DBUtil3;

public class MemberServiceImpl implements IMemberService{
	private IMemberDao dao = MemberDaoImpl.getInstance();
	private static IMemberService instance;
	
	private MemberServiceImpl() {}
	
	public static IMemberService getInstance() {
		if(instance == null) instance = new MemberServiceImpl();
		return instance;
	}
	



	@Override
	public int insertMember(MemberVO memVo) {
		Connection conn = null;
		int result = 0; // 반환값 변수
		
		try {
			conn = DBUtil3.getConnection();
			
			result = dao.insertMember(conn, memVo);
		} catch (SQLException e) {
			result = 0;
			e.printStackTrace();
		} finally {
			if(conn != null) try { conn.close(); } catch(SQLException e) {}
		}
		return result;
	}

	@Override
	public int deleteMember(String memId) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = DBUtil3.getConnection();
			
			result = dao.deleteMember(conn, memId);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn != null) try { conn.close(); } catch(SQLException e) {}
		}
		
		return result;
	}

	@Override
	public int updateMember(MemberVO memVo) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = DBUtil3.getConnection();
			
			result = dao.updateMember(conn, memVo);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn != null) try { conn.close(); } catch(SQLException e) {}
		}
		return result;
	}

	@Override
	public List<MemberVO> getAllMember() {
		Connection conn = null;
		List<MemberVO> list = null;
		
		try {
			conn = DBUtil3.getConnection();
			list = dao.getAllMember(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn != null) try { conn.close(); } catch(SQLException e) {}
		}
		
		return list;
	}

	@Override
	public int getMemberCount(String memId) {
		Connection conn = null;
		int count = 0;
		try {
			conn = DBUtil3.getConnection();
			count = dao.getMemberCount(conn, memId);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn != null) try { conn.close(); } catch(SQLException e) {}
		}
		
		return count;
	}

	@Override
	public int updateMember2(Map<String, String> paramMap) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = DBUtil3.getConnection();
			
			result = dao.updateMember2(conn, paramMap);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn != null) try { conn.close(); } catch(SQLException e) {}
		}
		
		return result;
	}

}
