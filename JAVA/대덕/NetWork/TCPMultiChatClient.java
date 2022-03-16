package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TCPMultiChatClient {

	public void clientStart() {
		Socket socket = null;
		
		try {
			socket = new Socket("localhost", 7777);
			System.out.println("서버에 연결되었습니다.");
			System.out.println();
			
			// 메세지 전송용 쓰레드와 수신용 쓰레드를 실행
			ClientSender sender = new ClientSender(socket);
			ClientReceiver receiver = new ClientReceiver(socket);
			
			sender.start();
			receiver.start();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void main(String[] args) {
		new TCPMultiChatClient().clientStart();
	}
	
	// 메세지 전송용 쓰레드
	class ClientSender extends Thread{
		private Socket socket;
		private DataInputStream dis;
		private DataOutputStream dos;
		
		private String name;
		private Scanner scan = new Scanner(System.in);
		
		public ClientSender(Socket socket) {
			this.socket = socket;
			
			try {
				dis = new DataInputStream(socket.getInputStream());
				dos = new DataOutputStream(socket.getOutputStream());
				
				if(dis != null) {
					// 처음 클라이언트가 실행되면 자신의 대화명을 서버로 전송하고 대화명의 중복 여부를 feedback받아서 확인한다.
					do {						
						System.out.println("대화명 : ");
						String name = scan.nextLine();
						
						dos.writeUTF(name); // 대화명 전송
						
						// 대화명 중복여부 받기
						String feedback = dis.readUTF();
						
						if("이름중복".equals(feedback)) {
							// 대화명이 중복일때
							System.out.println(name + "은 중복된 이름입니다.");
							System.out.println("다른 대화명을 입력하세요.");
						}else {
							// 대화명이 중복이 아닐때
							this.name = name;
							System.out.println(name + "이름으로 대화방에 입장했습니다.");
							break;
						}
					} while (true);
					
					
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		} // 생성자 끝
		
		@Override
		public void run() {
			try {
				while(dos != null) {
					// 키보드로 입력한 내용을 서버로 전송한다.
					dos.writeUTF("[" + name + "] : " + scan.nextLine());
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	// 메세지 수신용 쓰레드
	class ClientReceiver extends Thread{
		private Socket socket;
		private DataInputStream dis;
		
		// 생성자
		public ClientReceiver(Socket socket) {
			this.socket = socket;
			
			try {
				dis = new DataInputStream(socket.getInputStream());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		@Override
		public void run() {
			try {
				while(dis != null) {
					// 서버가 보낸 메세지를 받아서 화면에 출력
					System.out.println(dis.readUTF());
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

}










