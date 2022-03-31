package kr.or.ddit.lprod.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.ibatis.config.SqlMapClientUtil;
import kr.or.ddit.lprod.vo.LprodVO;

public class LprodDaoImpl implements ILprodDao {
	private static ILprodDao dao;
	private SqlMapClient smc;
	
	private LprodDaoImpl() {
		smc = SqlMapClientUtil.getSqlMapClient();
	}
	
	public static ILprodDao getInstance() {
		if(dao == null) dao = new LprodDaoImpl();
		return dao;
	}

	@Override
	public List<LprodVO> selectAll() {
		List<LprodVO> list = null;
		
		try {
			list = smc.queryForList("lprod.selectAll");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

}
