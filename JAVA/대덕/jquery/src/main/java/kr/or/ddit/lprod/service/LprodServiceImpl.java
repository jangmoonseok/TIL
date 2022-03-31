package kr.or.ddit.lprod.service;

import java.util.List;

import kr.or.ddit.lprod.dao.ILprodDao;
import kr.or.ddit.lprod.dao.LprodDaoImpl;
import kr.or.ddit.lprod.vo.LprodVO;

public class LprodServiceImpl implements ILprodService {
	private static ILprodService service;
	private ILprodDao dao;
	
	private LprodServiceImpl() {
		dao = LprodDaoImpl.getInstance();
	}
	
	public static ILprodService getInstance() {
		if(service == null) service = new LprodServiceImpl();
		return service;
	}
	
	@Override
	public List<LprodVO> selectAll() {
		return dao.selectAll();
	}

}
