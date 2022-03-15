package kr.or.ddit.basic.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
 	전송되어온 파일을 보내온 파일이름으로 저장한다.
 	저장위치(d:/d_other/down)
 */
public class TCPFileServer02 {
	private ServerSocket server;
	private Socket socket;
	
	private BufferedInputStream bis;
	private BufferedOutputStream bos;
	private DataInputStream dis;
	
	private String saveDir = "d:/d_other/down";
	private String fileName;
	
	public void serverStart() {
		File save = new File(saveDir);
		
		if(!save.exists()) {
			save.mkdirs(); // 저장 폴더가 없으면 새로 생성한다.
		}
		
		try {
			server = new ServerSocket(7777);
			System.out.println("서버 준비완료...");
			
			socket = server.accept(); // 클라이언트의 요청을 기다린다
			System.out.println("파일 다운로드 시작...");
			
			// 파일명을 먼저 받는다.
			dis = new DataInputStream(socket.getInputStream());
			fileName = dis.readUTF();
			if(fileName == null) {
				fileName = "noname.jpg";
			}
			
			bis = new BufferedInputStream(socket.getInputStream());
			bos = new BufferedOutputStream(new FileOutputStream(saveDir + File.separator + fileName));
			
			byte[] temp = new byte[1024];
			int len = 0;
			
			// 소켓으로 받은 데이터를 파일로 저장하기
			while( (len = bis.read(temp)) > 0 ) {
				bos.write(temp, 0, len);
			}
			
			
			bos.flush();
			
			System.out.println("파일 다운로드 완료...");
		} catch (Exception e) {
			System.out.println("파일 다운로드 실패");
			e.printStackTrace();
		} finally {
			// 사용했던 자원들을 반납한다.
			
			if(dis != null) try { dis.close(); } catch(IOException e) {e.printStackTrace();}
			if(bos != null) try { bos.close(); } catch(IOException e) {e.printStackTrace();}
			if(bis != null) try { bis.close(); } catch(IOException e) {e.printStackTrace();}
			if(socket != null) try { socket.close(); } catch(IOException e) {e.printStackTrace();}
			if(server != null) try { server.close(); } catch(IOException e) {e.printStackTrace();}
		}
	}
	
	public static void main(String[] args) throws IOException {

		new TCPFileServer02().serverStart();
	}

}
