package util;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class test {

	public static void main(String[] args) {
		String url = "https://finance.naver.com/item/main.naver?code=005930";
		
		Document doc;
		try {
			doc = Jsoup.connect(url).get();
			Elements newsHeadlines = doc.select("#middle > dl > dd");
			
			String name = newsHeadlines.get(1).text();
			String code = newsHeadlines.get(2).text();
			String date = newsHeadlines.get(3).text();
			String juga = newsHeadlines.get(4).text();
			System.out.println(name);
			System.out.println(code);
			System.out.println(date);
			System.out.println(juga);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
