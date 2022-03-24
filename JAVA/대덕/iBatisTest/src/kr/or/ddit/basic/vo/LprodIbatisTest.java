package kr.or.ddit.basic.vo;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.Scanner;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class LprodIbatisTest {
	
	// iBatis를 이용하여 DB자료를 처리하는 순서 및 방법
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 1. iBatis의 환경설정파일을 읽어와서 실행한다.
		try {
			// 1-1 문자 인코딩 캐릭터셋 설정
			Charset charset = Charset.forName("utf-8");
			
			// 1-2 환경 설정 파일을 읽어올 스트림객체 생성.
			Reader rd = Resources.getResourceAsReader("kr/or/ddit/ibatis/config/sqlMapConfig.xml");
			
			// 1-3 위에서 생성한 스트림 객체를 이용하여 환경설정 파일을 실행한다. iBatis를 처리할 객체가 반환된다.
			SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
			//------------------------------------------------------------------------
			
			// 2. 실행할 SQL문에 맞는 쿼리문을 호출해서 원하는 작업 수행하기
			
			// 2-1 insert작업
			System.out.println("insert 작업 시작...");
			System.out.print("Lprod_id 입력 : ");
			int id = sc.nextInt();
			System.out.print("Lprod_gu 입력 : ");
			String gu = sc.next();
			System.out.print("Lprod_nm 입력 : ");
			String nm = sc.next();
			
			// 저장할 데이터를 VO에 담는다.
			LprodVO lvo = new LprodVO();
			lvo.setLprod_id(id);
			lvo.setLprod_gu(gu);
			lvo.setLprod_nm(nm);
			
			// sqlMapClient객체변수를 이용해서 처리할 쿼리문을 호출하여 실행한다.
			// 형식) smc.insert("namespace값.id값", 파라미터클래스); 반환값 : insert성공 : null, 실패 : 오류객체
			Object obj = smc.insert("Lprod.insertLprod", lvo);
			
			if(obj == null) {
				System.out.println("insert 성공");
			}else {
				System.out.println("insert 실패ㅔ");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
