package kr.or.ddlt.basic.stream;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataIOTest {

	public static void main(String[] args) {
		try {
			FileOutputStream fout = new FileOutputStream("d:/d_other/test.dat");
			
			// 자료형 단위로 출력할 보조스트림 객체 생성
			DataOutputStream dos = new DataOutputStream(fout);
			
			dos.writeInt(200); // 정수형으로 출력
			dos.writeFloat(123.45f); // 실수형(float)으로 출력
			dos.writeBoolean(false); // 논리형으로 출력
			dos.writeUTF("ABCD1234"); // 문자열형식으로 출력
			
			System.out.println("출력 완료...");
			dos.close();
			System.out.println("----------------------------------------------");
			
			// 출력한 자료 읽어오기
			FileInputStream fin = new FileInputStream("d:/d_other/test.dat");
			
			DataInputStream dis = new DataInputStream(fin);
			
			// DataInputStream으로 자료를 읽어올 때는 출력할 때의 순서와 같은 순서로 읽어와야 한다.
			System.out.println("정수형 : " + dis.readInt());
			System.out.println("실수형 : " + dis.readFloat());
			System.out.println("논리형 : " + dis.readBoolean());
			System.out.println("문자열형식 : " + dis.readUTF());
			
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

}
