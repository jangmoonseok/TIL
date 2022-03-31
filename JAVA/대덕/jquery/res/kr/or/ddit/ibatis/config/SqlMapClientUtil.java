package kr.or.ddit.ibatis.config;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;


public class SqlMapClientUtil {
	public static SqlMapClient getSqlMapClient() {
		
		// 1-2 환경 설정 파일을 읽어올 스트림객체 생성.
		SqlMapClient smc = null;
		Reader rd = null;
		try {
			Charset charset = Charset.forName("utf-8");
			rd = Resources.getResourceAsReader("kr/or/ddit/ibatis/config/sqlMapConfig.xml");
			if(smc == null) smc = SqlMapClientBuilder.buildSqlMapClient(rd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rd != null) try {rd.close();}catch(IOException e) {}
		}
		return smc;
	}
	
}
