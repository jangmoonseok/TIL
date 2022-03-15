package kr.or.ddit.basic.tcp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPFileClient {

	public static void main(String[] args) throws IOException {
		Socket socket = new Socket("192.168.34.18", 7777);
		System.out.println("서버에 연결되었습니다...");
		System.out.println();
		
		FileInputStream fin = new FileInputStream("d:/d_other/펭귄.jpg");
		OutputStream out = socket.getOutputStream();
		
		int data = 0;
		while ( (data = fin.read()) != -1 ) {
			out.write(data);
		}
		
		out.close();
		fin.close();
		
	}

}
