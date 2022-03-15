package kr.or.ddit.basic.tcp;

import java.awt.Panel;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFileChooser;

/*
	Open Dialog를 이용하여 전송할 파일을 선택하여 서버로 전송한다.
	이 때 파일 이름도 같이 전송한다.
*/
public class TCPFileClient02 {
	private Socket socket;
	private BufferedInputStream bis;
	private BufferedOutputStream bos;
	private DataOutputStream dos;
	
	public void clientStart() {
		// 전송할 파일 선택
		File file = getSelectFile("OPEN");
		
		if(file == null) {
			System.out.println("전송할 파일을 선택하지 않았습니다.");
			System.out.println("파일 전송 작업을 중단합니다...");
			return;
		}

		
		try {
			socket = new Socket("localhost", 7777);
			System.out.println("파일 전송 시작...");
			
			// 파일 명을 먼저 출력한다.
			dos = new DataOutputStream(socket.getOutputStream());
			dos.writeUTF(file.getName());
			
			bis = new BufferedInputStream(new FileInputStream(file));
			bos = new BufferedOutputStream(socket.getOutputStream());
			
			
			
			byte[] temp = new byte[1024];
			int len = 0;
			
			// 파일 내용을 읽어와 소켓으로 출력한다
			while( (len = bis.read(temp)) > 0 ) {
				bos.write(temp, 0, len);
			}
			
			bos.flush();
	
			System.out.println("파일 전송 완료...");
		} catch (Exception e) {
			System.out.println("파일 전송 실패...");
			e.printStackTrace();
		} finally {
			if(dos != null) try { dos.close(); } catch (IOException e) { e.printStackTrace(); };
			if(bos != null) try { bos.close(); } catch (IOException e) { e.printStackTrace(); };
			if(bis != null) try { bis.close(); } catch (IOException e) { e.printStackTrace(); };
			if(socket != null) try { socket.close(); } catch (IOException e) { e.printStackTrace(); };
		}
	}
	
	public File getSelectFile(String option) {
		JFileChooser chooser = new JFileChooser();
		chooser.setAcceptAllFileFilterUsed(true);
		chooser.setCurrentDirectory(new File("d:/d_other"));
		
		int result;
		if("OPEN".equals(option)) {
			result = chooser.showOpenDialog(new Panel());
		}else if("SAVE".equals(option)) {
			result = chooser.showSaveDialog(new Panel());
		}else {
			System.out.println("option이 잘못되었습니다");
			return null;
		}
		
		File selectedFile = chooser.getSelectedFile();
		System.out.println("선택한 파일 : " + selectedFile.getAbsolutePath());
		return selectedFile;
	}
	
	public static void main(String[] args) throws IOException {

		new TCPFileClient02().clientStart();
	}

}
