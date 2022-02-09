```java
package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import service.MemberService;
import util.JDBCUtil;

public class MemberDao {
	//Dao(DateAccessObject) : 각각의 페이지에서 중복되는 데이터접근을 막기위해 만든 객체
	
	private MemberDao() { // 다른 클래스에서 객체생성을 막기 위해 생성자에 private를 붙인다
		
	}
	private static MemberDao instance; // 객체를 만들어서 보관 할 변수
	public static MemberDao getInstance() { // 다른 클래스에서 객체가 필요할 때 호출 할 메서드
		if(instance == null) {
			// 객체가 없으면 객체 생성
			instance = new MemberDao();
		}
		return instance;
	}
	
	public int insertMember(Map<String, Object> param) {
		String sql = "	INSERT INTO TB_JDBC_MEMBER "
				+ " 	VALUES(?, ?, ?)";
		List<Object> _param = new ArrayList<Object>();
		_param.add(param.get("MEM_ID"));
		_param.add(param.get("PASSWORD"));
		_param.add(param.get("MEM_NAME"));
		
		return JDBCUtil.update(sql, _param);
	}
	
	public Map<String, Object> selectMember(String memId, String password){
		String sql = "SELECT MEM_ID,"
				+ "          PASSWORD,"
				+ "          MEM_NAME"
				+ "     FROM TB_JDBC_MEMBER"
				+ "    WHERE MEM_ID = ?"
				+ "      AND PASSWORD = ?";
		
		List<Object> param = new ArrayList<Object>();
		param.add(memId);
		param.add(password);
		
		return JDBCUtil.selectOne(sql, param);
	}
	
}

