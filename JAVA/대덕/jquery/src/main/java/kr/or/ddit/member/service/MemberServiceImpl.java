package kr.or.ddit.member.service;

import java.util.List;

import org.junit.jupiter.api.Test;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.ZipVO;

public class MemberServiceImpl implements IMemberService {
	private static IMemberService service;
	private IMemberDao dao;
	private MemberServiceImpl() {
		dao = MemberDaoImpl.getInstance();
	}
	
	public static IMemberService getInstance(){
		if(service == null) service = new MemberServiceImpl();
		return service;
	}
	
	@Override
	public List<MemberVO> selectAll() {
//		List<MemberVO> list = null;
//		list = dao.selectAll();
//		return list;
		return dao.selectAll();
	}

	@Test
	void idcheckTest() {
		String idcheck = idcheck("しけいしけい");
		System.out.println(idcheck);
	}
	@Override
	public String idcheck(String mem_id) {
		return dao.idcheck(mem_id);
	}

	@Override
	public List<ZipVO> zipList(String dong) {
		return dao.zipList(dong);
	}

	@Override
	public String insertMember(MemberVO vo) {
		return dao.insertMember(vo);
	}

}
