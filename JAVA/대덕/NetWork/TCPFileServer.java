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
 */
public class TCPFileServer {

	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(7777);
		
		System.out.println("서버 준비중...");
		
		Socket socket = server.accept();
		System.out.println("클라이언트와 연결되었습니다.");
		System.out.println();

		
		InputStream is = socket.getInputStream();
		FileOutputStream fout = new FileOutputStream("d:/d_other/down/펭귄_전송본.jpg");
		
		int data = 0;
		while((data = is.read()) != -1) {
			fout.write(data);
		}
		
		fout.close();
		is.close();
		
		
	}

}
