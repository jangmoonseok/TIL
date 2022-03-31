package kr.or.ddit.buyer.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.buyer.vo.BuyerVO;
import kr.or.ddit.ibatis.config.SqlMapClientUtil;

public class BuyerDaoImpl implements IBuyerDao {
	private static IBuyerDao dao;
	private SqlMapClient smc;
	
	private BuyerDaoImpl() {
		smc = SqlMapClientUtil.getSqlMapClient();
	}
	
	public static IBuyerDao getInstance() {
		if(dao == null) dao = new BuyerDaoImpl();
		return dao;
	}
	
	@Override
	public List<BuyerVO> selectByName() {
		List<BuyerVO> list = null;
		
		try {
			list = smc.queryForList("buyer.selectByName");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public BuyerVO idByDetail(String bId) {
		BuyerVO vo = null;
		
		try {
			vo = (BuyerVO)smc.queryForObject("buyer.idByDetail", bId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return vo;
	}

}
