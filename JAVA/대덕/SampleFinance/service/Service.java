package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import dao.Dao;

public class Service {

	public static void main(String[] args) {
			new Service().kospi();
		
	}
	
	private Dao testDao = Dao.getInstance();
			
	private void kospi() {
		String Samsung = "https://finance.naver.com/item/main.naver?code=005930";
		String LGEnerge = "https://finance.naver.com/item/main.naver?code=373220";
		String SkHynix = "https://finance.naver.com/item/main.naver?code=000660";
		String SamsungWo = "https://finance.naver.com/item/main.naver?code=005935";
		String Naver = "https://finance.naver.com/item/main.naver?code=035420";
		String SamsungBio = "https://finance.naver.com/item/main.naver?code=207940";
		String LGChemical = "https://finance.naver.com/item/main.naver?code=051910";
		String HyunDae = "https://finance.naver.com/item/main.naver?code=005380";
		String SamsungSDI = "https://finance.naver.com/item/main.naver?code=006400";
		String Kakao = "https://finance.naver.com/item/main.naver?code=035720";
		
		stockList(Samsung);
		stockList(LGChemical);
//		stockList(SkHynix);
//		stockList(SamsungWo);
//		stockList(Naver);
//		stockList(SamsungBio);
//		stockList(LGChemical);
//		stockList(HyunDae);
//		stockList(SamsungSDI);
//		stockList(Kakao);
		
	}
	
	
	void stockList(String url) {
		Document doc;
		
		try {
			doc = Jsoup.connect(url).get();
			
			Elements curPrice = doc.select("#chart_area > div.rate_info > div > p.no_today > em > span");
			Elements stockName = doc.select("#middle > div.h_company > div.wrap_company > h2 > a");
			Elements befPrice = doc.select("#chart_area > div.rate_info > table > tbody > tr:nth-child(1) > td.first > em > span");
			Elements highPrice = doc.select("table > tbody > tr:nth-child(1) > td:nth-child(2) > em.no_up > span");
			Elements lowPrice = doc.select("#chart_area > div.rate_info > table > tbody > tr:nth-child(2) > td:nth-child(2) > em:nth-child(2) > span");
			Elements compare = doc.select("#chart_area > div.rate_info > div > p.no_exday > em:nth-child(2) > span");
			Elements totalVol = doc.select("#chart_area > div.rate_info > table > tbody > tr:nth-child(1) > td:nth-child(3) > em > span");
			Elements totalPrice = doc.select("#chart_area > div.rate_info > table > tbody > tr:nth-child(2) > td:nth-child(3) > em > span");
			Elements marketTotal = doc.select("#_market_sum");
			Elements startPrice = doc.select("#chart_area > div.rate_info > table > tbody > tr:nth-child(2) > td.first > em > span");
			Elements stockClass = doc.select("#tab_con1 > div.first > table > tbody > tr:nth-child(2) > td");
			
			String CurPrice = curPrice.get(0).text();
			String StockName = stockName.get(0).text();
			String BefPrice = befPrice.get(0).text();
//			String HighPrice = highPrice.get(0)
			String LowPrice = lowPrice.get(0).text();
			String Compare = compare.get(1).text();
			String TotalVol = totalVol.get(0).text();
//			String TotalPrice = totalPrice.get(0).text();
//			String MarketTotal = marketTotal.get(0).text();
//			String StartPrice = startPrice.get(0).text();
//			String[] StockClass = stockClass.get(0).text().split(" ");
			
			
			System.out.println(CurPrice);
			System.out.println(StockName);
			System.out.println(BefPrice);
			System.out.println(highPrice.get(1));
			System.out.println(LowPrice);
			System.out.println(Compare);
			System.out.println(TotalVol);
			
			List<Object> param = new ArrayList<Object>();
//			param.add(StockName);
//			param.add(CurPrice);
//			param.add(StartPrice);
//			param.add(BefPrice);
//			param.add(HighPrice);
//			param.add(LowPrice);
//			param.add(Compare);
//			param.add(TotalVol);
//			param.add(TotalPrice);
//			param.add( MarketTotal);
//			param.add(StockClass[0]);
			
			
//			
//			int result = testDao.insertKospi(param);
//			
//			if(result != 0) {
//				System.out.println("등록성공");
//			}else {
//				System.out.println("등록실패");
//			}

			
//			System.out.print("종목명:" + StockName);
//			System.out.print("\t현재가격:" + CurPrice);
//			System.out.print("\t시가:" + StartPrice);
//			System.out.print("\t전일:" + BefPrice);
//			System.out.print("\t고가:" + HighPrice);
//			System.out.println("\t저가:" + LowPrice);
//			System.out.print("전일대비:" + Compare);
//			System.out.print("\t거래량:" + TotalVol);
//			System.out.print("\t거래대금:" + TotalPrice);
//			System.out.println("\t시가총액:" + MarketTotal);
//			System.out.println("분류:" + StockClass[0]);
//			System.out.println("----------------------------------------------------------------------------------------------------");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
