package kr.or.ddlt.basic;

import java.util.Stack;

public class StackTest {

	public static void main(String[] args) {
		Browser browser = new Browser();
		browser.goURL("1.네이버");
		browser.goURL("2.다음");
		browser.goURL("3.구글");
		browser.goURL("4.네이트");
		
		browser.history();
		
		browser.goBack();
		System.out.println("뒤로가기 후..");
		browser.history();
		browser.goBack();
		System.out.println("뒤로가기 후..");
		browser.history();
		browser.goForward();
		System.out.println("앞으로가기 후..");
		browser.history();
		
		browser.goURL("5.야후");
		browser.history();
	}

}

//웹 브라우저의 앞으로 가기, 뒤로 가기 기능을 구현한 클래스 작성
class Browser{
	private Stack<String> back; // 이전의 방문 내역이 저장될 Stack
	private Stack<String> forward; // 다음 방문 내역이 저장될 Stack
	private String currentURL; // 현재 페이지
	
	public Browser() {
		back = new Stack<String>();
		forward = new Stack<String>();
		currentURL = "";
	}
	
	//사이트를 방문하는 메서드 ==> 매개변수에는 방문할 URL이 저장된다.
	public void goURL(String url) {
		System.out.println(url + " 사이트에 접속합니다...");
		// 현재 페이지가 있을때
		if(currentURL != null && !"".equals(currentURL)) {
			back.push(currentURL); // 현재 페이지를 back스택에 저장한다.
		}
		currentURL = url; // 현재 페이지를 변경한다.
		forward.clear();
	}
	
	//뒤로가기
	public void goBack() {
		// isEmpty() ==> List나 Stack이 비어있으면 true 그렇지 않으면 false를 반환
		if(!back.isEmpty()) { // 뒤로가기할 back스택에 데이터가 있을때
			forward.push(currentURL); // 현재 페이지를 앞으로가기할 forward스택에 추가
			currentURL = back.pop(); // back스택에서 가장 최근 주소를 꺼내와 currentURL에 저장
		}
	}
	
	//앞으로가기
	public void goForward() {
		if(!forward.isEmpty()) {
			back.push(currentURL); // 현재 페이지를 뒤로가기할 back스택에 추가
			currentURL = forward.pop(); // forward스택에서 가장 최근 주소를 꺼내와 currentURL에 저장
		}
	}
	
	//방문 기록을 확인하기 위한 메서드
	public void history() {
		System.out.println();
		System.out.println("----------------------------------");
		System.out.println("\t\t방문기록");
		System.out.println("----------------------------------");
		
		System.out.println("back    : " + back);
		System.out.println("현재     : " + currentURL);
		System.out.println("forward : " + forward);
		System.out.println("----------------------------------");
	}
}
