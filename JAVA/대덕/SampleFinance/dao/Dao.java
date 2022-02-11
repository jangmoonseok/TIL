package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class Dao {
	
	private Dao() { // 다른 클래스에서 객체생성을 막기 위해 생성자에 private를 붙인다
		
	}
	private static Dao instance; // 객체를 만들어서 보관 할 변수
	public static Dao getInstance() { // 다른 클래스에서 객체가 필요할 때 호출 할 메서드
		if(instance == null) {
			// 객체가 없으면 객체 생성
			instance = new Dao();
		}
		return instance;
	}
	
	public int insertKospi(List<Object> param) {
		String sql = "INSERT INTO KOSPY"
				+ "   VALUES("
				+ "   'kospy'||SEQ_STOCK_NO.NEXTVAL,"
				+ "   ?,?,?,?,?,?,?,?,?,?,?";

		List<Object> _param = new ArrayList<Object>();
		for(int i = 0; i < param.size(); i++) {
			_param.add(param.get(i));
		}
		
		
		return JDBCUtil.update(sql, _param);
	}
}
