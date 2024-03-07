package com.sist.main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sist.dao.MapoCultureDAO;
import com.sist.dao.MusicNewsDAO;
import com.sist.dao.MusicNewsVO;

public class MapoCultureMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{
			MapoCultureDAO dao=new MapoCultureDAO();
			
			for(int i=1;i<=2;i++)
			{	
				//https://www.mapo.go.kr/site/culture/his/culture_onstop_his_list?cp=1&sc=A.MOT_TITLE&pageSize=9&listType=list&catId=MTM&cCodeId=MTM003
				Document doc=Jsoup.connect("https://www.mapo.go.kr/site/culture/his/culture_onstop_his_list?cp="+i+
														"&sc=A.MOT_TITLE&pageSize=9&listType=list&catId=MTM&cCodeId=MTM003").get();
				Elements link=doc.select("div.culture_card_wrap ul li a");

				for(int j=0;j<link.size();j++)
				{
					
					try
					{
						String subLink="https://www.genie.co.kr/"+link.get(j).attr("href");
						System.out.println(subLink);
						
						Document doc2=Jsoup.connect(subLink).get();
						
						// 제목
						Element title_e=doc2.selectFirst("div.magazine-title div.title span.ellipsis");
						String title=title_e.text();
    					System.out.println(title_e.text());
    					
    					// 발행일
    					Element regdate_e=doc.selectFirst("div.list-normal ul.clearfix span.date");
    					String regdate=regdate_e.text();
    					System.out.println(regdate_e.text());
    					
    					// 이미지1 (메인)
    					Element poster_e=doc2.selectFirst("div.magazine-view div.main-visual div.cover img");
    					String poster=poster_e.attr("src");
    					System.out.println(poster_e.attr("src"));
    					
    					// 이미지2 (서브)
    					Element poster2_e=doc2.selectFirst("div.view-1 div.cover img");
    					String poster2=poster2_e.attr("src");
    					System.out.println(poster2_e.attr("src"));
    					
    					// 내용
    					Elements content_e=doc2.select("div.view-2 div.txt");
    					String content=content_e.text();
    					System.out.println(content_e.text());
    					
    					// 넣기
    					MusicNewsVO vo=new MusicNewsVO();
    					vo.setCateno(5);
    					vo.setTitle(title);
    					vo.setRegdate(regdate);
    					vo.setPoster(poster);
    					vo.setPoster2(poster2);
    					vo.setContent(content);
                    	
    					
    					//dao.musicNewsInsert(vo);
                    	System.out.println("save end");
    					
					}catch(Exception ex) {}	
				}
			
			}
		}catch(Exception ex) {}
		
	}
	}


