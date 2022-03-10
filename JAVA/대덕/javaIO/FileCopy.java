package kr.or.ddlt.basic.stream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 *  문제) D드라이브의 d_other폴더에 있는 '펭귄.jpg'파일을 D드라이브의 d_other폴더에 있는 '연습용'폴더에 '펭귄_복사본.jpg'파일로 복사하는 프로그램을 작성하시오.
 */
public class FileCopy {

	public static void main(String[] args) {
		try {
			FileInputStream fin = new FileInputStream("d:/d_other/펭귄.jpg");
			FileOutputStream fout = new FileOutputStream("d:/d_other/연습용/펭귄_복사본.jpg");
			
			int c;
			
			while( (c = fin.read()) != -1) {
				fout.write(c);
			}
			
			System.out.println("작업 완료...");
			fin.close();
			fout.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
