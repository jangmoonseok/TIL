package kr.or.ddlt.basic.stream;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedIOTest01 {

	public static void main(String[] args) {
		// 입출력 향상을 위하여 Buffered스트림을 사용한다.
		
		try {
			FileOutputStream fout = new FileOutputStream("d:/d_other/bufferTest.txt");
			
			BufferedOutputStream bout = new BufferedOutputStream(fout, 5);
			
			for(char i = '1'; i <= '9'; i++) {
				// buffer의 크기만큼 출력이되고 buffer가 가득차면 데이터를 파일로 옮기고 buffer를 비운다.
				bout.write(i);
			}
			
			System.out.println("작업 끝...");
			bout.flush();
			
			// 버퍼스트림을 close()하면 버퍼의 내용을 모두 flush한 후 닫아준다.
			bout.close();
		} catch (IOException e) {
			// TODO: handle exception
		}

	}

}
