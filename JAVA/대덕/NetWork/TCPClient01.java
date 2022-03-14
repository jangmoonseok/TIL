package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient01 {

	public static void main(String[] args) throws IOException {
		// 현재 자신 컴퓨터를 나타내는 방법
		// 1) 원래의 IP주소 : 예) 192.168.34.18
		// 2) 지정된 IP주소 : 127.0.0.1
		// 3) 원래의 컴퓨터이름 : 예)  DESKTOP-BP73NMV
		// 4) 지정된 컴퓨터이름 : localhost
		
		Socket socket = new Socket("192.168.34.18", 7777);
		
		// 이 부분 부터는 서버와 연결이 완료된 이후에 실행된다.
		System.out.println("서버에 연결되었습니다...");
		System.out.println();
		
		// 서버에서 보낸 메시지를 받아서 화면에 출력하기
		InputStream is = socket.getInputStream();
		DataInputStream dis = new DataInputStream(is);
		
		// 서버가 보낸 데이터 받아서 출력
		System.out.println("서버에서 보낸 메시지 : " + dis.readUTF());
		System.out.println();
		
		System.out.println("연결을 종료합니다...");
		dis.close();
		socket.close();
	}

}
