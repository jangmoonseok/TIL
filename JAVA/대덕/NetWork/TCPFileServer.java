package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
 	서버와 클라이언트가 접속이 이루어지면 클라이언트가 d:/d_other/펭귄.jpg파일을 서버로 전송하면 서버는
 	이 데이터를 받아서 d:/d_other/down폴더에 펭귄_전송본.jpg로 저장한다.
 	
 	서버 : 소켓으로 입력 받아서 파일로 출력
 	클라이언트 : 파일로 입력 받아서 소켓으로 출력
 */
public class TCPFileServer {

	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(7777);
		
		System.out.println("서버 준비중...");
		
		Socket socket = server.accept();
		System.out.println("클라이언트와 연결되었습니다.");
		System.out.println();

		
		InputStream is = socket.getInputStream(); // 소켓으로 입력을 받아야하기 때문에 socket.getInputStream()사용
		FileOutputStream fout = new FileOutputStream("d:/d_other/down/펭귄_전송본.jpg"); // 파일로 출력해야하기 때문에 FileOutputStream()사용
		
		int data = 0;
		while((data = is.read()) != -1) { // 소켓으로 입력받은 데이터를 파일로 출력
			fout.write(data);
		}
		
		fout.close();
		is.close();
		
		
	}

}
