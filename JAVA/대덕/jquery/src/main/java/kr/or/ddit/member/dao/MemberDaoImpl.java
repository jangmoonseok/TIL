package kr.or.ddit.member.dao;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.ibatis.config.SqlMapClientUtil;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.ZipVO;

// mapper접근 - SqlMapClient필요
// dao객체 생성

public class MemberDaoImpl implements IMemberDao{
	private static IMemberDao dao;
	private SqlMapClient smc;
	
	private MemberDaoImpl() {
		smc = SqlMapClientUtil.getSqlMapClient();
	}
	
	public static IMemberDao getInstance() {
		if(dao == null) dao = new MemberDaoImpl();
		return dao;
	}
	
	
	@Override
	public List<MemberVO> selectAll() {
		List<MemberVO> list = null;
		
		try {
			list = smc.queryForList("member.selectAll");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public String idcheck(String mem_id) {
		String id = "";
		try {
			id = (String)smc.queryForObject("member.idcheck", mem_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}

	@Override
	public String insertMember(MemberVO vo) {
		String id = null;
		
		try {
			id = (String)smc.insert("insertMember", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	@Test
	void zipListTest() {
		List<ZipVO> zipList = zipList("오류동");
		System.out.println(zipList);
	}
	@Override
	public List<ZipVO> zipList(String dong) {
		List<ZipVO> list = null;
		
		try {
			list = smc.queryForList("zip.zipList", dong);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
