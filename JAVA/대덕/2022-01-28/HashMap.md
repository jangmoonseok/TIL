```java
package j_collection;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

public class HashMapClass {

	public static void main(String[] args) {
		/*
		 * HashMap
		 * - 테이블 형식을 표현하기위해 사용한다.
		 * - Object put(Object key, Object value) : 지정된 키와 값을 저장한다.
		 * - Object remove(Object key) : 저장된 키로 저장된 값을 제거한다.
		 * - Object get(Object key) : 지정된 키의 값(없으면 null)을 반환한다.
		 * - Set keySet() : 저장된 모든 키를 Set으로 반환한다.
		 */
		
		//선언
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		//데이터 저장
		map.put("age", 10);
		map.put("name", "홍길동");
		map.put("date", new Date());
		
		System.out.println(map); //{date=Fri Jan 28 15:52:18 KST 2022, name=홍길동, age=10}
		//HashMap은 index가 없기때문에 저장한 순서와 상관없는 위치에 저장된다.
		
		//데이터 변경
		map.put("name", "이순신");
		System.out.println(map);
		
		//데이터 삭제
		map.remove("date");
		System.out.println(map);
		
		//데이터 읽기
		Object value = map.get("name"); //반환하는 타입은 HashMap을 Generic으로 지정한 타입
		
		System.out.println(((String)value).substring(0, 2)); // 반환된 타입이 Object클래스이므로 String클래스의 메소드를 사용하려면 String으로 형 변환해서 사용
		
		//keySet
		Set<String> keys = map.keySet();
		System.out.println(keys);
		
		//Set은 값을 한개씩 꺼내는 메소드가 없기때문에 반복문사용
		for(String key : keys) {
			System.out.println(key + " : " + map.get(key));
		}
		
		//테이블 만들기
		ArrayList<HashMap<String, Object>> lprodTable =new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> lprod = new HashMap<String, Object>();
		lprod.put("LPROD_ID", 1);
		lprod.put("LPROD_GU", "P101");
		lprod.put("LPROD_NM", "컴퓨터제품");
		lprodTable.add(lprod);
		
		lprod = new HashMap<String, Object>();
		lprod.put("LPROD_ID", 2);
		lprod.put("LPROD_GU", "P102");
		lprod.put("LPROD_NM", "전자제품");
		lprodTable.add(lprod);
		
		
		lprod = new HashMap<String, Object>();
		lprod.put("LPROD_ID", 3);
		lprod.put("LPROD_GU", "P103");
		lprod.put("LPROD_NM", "여성캐주얼");
		lprodTable.add(lprod);
		
		keys = lprod.keySet();
		
		for(int i = 0; i < lprodTable.size(); i++) {			
			lprod = lprodTable.get(i);
			for(String key : keys) {
				System.out.print(lprod.get(key) + "\t");
			}
			System.out.println();
		}
	
		
	}

}
```
