package kr.or.ddit.basic.tcp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/*
	서버와 클라이언트가 접속이 이루어지면 클라이언트가 d:/d_other/펭귄.jpg파일을 서버로 전송하면 서버는
	이 데이터를 받아서 d:/d_other/down폴더에 펭귄_전송본.jpg로 저장한다.
	
	서버 : 소켓으로 입력 받아서 파일로 출력
	클라이언트 : 파일로 입력 받아서 소켓으로 출력
*/
public class TCPFileClient {

	public static void main(String[] args) throws IOException {
		Socket socket = new Socket("192.168.34.18", 7777);
		System.out.println("서버에 연결되었습니다...");
		System.out.println();
		
		FileInputStream fin = new FileInputStream("d:/d_other/펭귄.jpg"); // 파일을 입력받아야 하기 때문에 FileInputStream사용
		OutputStream out = socket.getOutputStream(); // 소켓을 통해 서버로 전달해야하기 때문에 socket.getOutputStream()사용
		
		int data = 0;
		
		while ( (data = fin.read()) != -1 ) { // 입력 받은 파일을 소켓으로 출력
			out.write(data);
		}
		
		out.close();
		fin.close();
		
	}

}
