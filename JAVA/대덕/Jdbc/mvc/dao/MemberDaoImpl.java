package kr.or.ddit.basic.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.or.ddit.basic.mvc.vo.MemberVO;

public class MemberDaoImpl implements IMemberDao{
	private static IMemberDao instance;
	
	private MemberDaoImpl() {}
	
	public static IMemberDao getInstance() {
		if(instance == null) instance = new MemberDaoImpl();
		return instance;
	} 

	@Override
	public int insertMember(Connection conn, MemberVO memVo) throws SQLException {
		String sql = "insert into mymember(mem_id, mem_pass, mem_name, mem_tel, mem_addr)"
				+ "   values(?,?,?,?,?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memVo.getMem_id());
		pstmt.setString(2, memVo.getMem_pass());
		pstmt.setString(3, memVo.getMem_name());
		pstmt.setString(4, memVo.getMem_tel());
		pstmt.setString(5, memVo.getMem_addr());
		
		int result = pstmt.executeUpdate();
		
		if(pstmt != null) pstmt.close();
		
		return result;
	}

	@Override
	public int deleteMember(Connection conn, String memId) throws SQLException {
		String sql = "delete from mymember where mem_id = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memId);
		
		int result = pstmt.executeUpdate();
		
		if(pstmt != null) pstmt.close();
		return result;
	}

	@Override
	public int updateMember(Connection conn, MemberVO memVo) throws SQLException {
		String sql = "update mymember"
				+ "		 set mem_name = ?,"
				+ "			 mem_pass = ?,"
				+ "			 mem_tel = ?,"
				+ "			 mem_addr = ?"
				+ "	   where mem_id = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memVo.getMem_name());
		pstmt.setString(2, memVo.getMem_pass());
		pstmt.setString(3, memVo.getMem_tel());
		pstmt.setString(4, memVo.getMem_addr());
		pstmt.setString(5, memVo.getMem_id());
		
		int result = pstmt.executeUpdate();
		
		if(pstmt != null) pstmt.close();
		
		return result;
	}

	@Override
	public List<MemberVO> getAllMember(Connection conn) throws SQLException {
		List<MemberVO> list = new ArrayList<MemberVO>();
		
		String sql = "select * from mymember";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()){
			MemberVO memVo = new MemberVO();
			memVo.setMem_id(rs.getString("mem_id"));
			memVo.setMem_addr(rs.getString("mem_addr"));
			memVo.setMem_tel(rs.getString("mem_tel"));
			memVo.setMem_pass(rs.getString("mem_pass"));
			memVo.setMem_name(rs.getString("mem_name"));
			list.add(memVo);
		}
		
		if(rs != null) rs.close();
		if(pstmt != null) pstmt.close();
		
		return list;
	}

	@Override
	public int getMemberCount(Connection conn, String memId) throws SQLException {
		String sql = "select count(*) cnt from mymember where mem_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memId);
		
		ResultSet rs = pstmt.executeQuery();
		
		int count = 0;
		
		if(rs.next()) {
			count = rs.getInt("cnt");
		}
		
		if(rs != null) rs.close();
		if(pstmt != null) pstmt.close();
		
		return count;
	}

	@Override
	public int updateMember2(Connection conn, Map<String, String> paramMap) throws SQLException {
		int result = 0;
		
		String sql = "update mymember"
				+ "		 set " + paramMap.get("field") + " = ?"
				+ "    where mem_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, paramMap.get("data"));
		pstmt.setString(2, paramMap.get("memId"));
		
		result = pstmt.executeUpdate();
		
		if(pstmt != null) pstmt.close();
		
		return result;
	}



}
