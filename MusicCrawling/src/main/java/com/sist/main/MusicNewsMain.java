package com.sist.main;

import com.sist.dao.*;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;

public class MusicNewsMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{
			MusicNewsDAO dao=new MusicNewsDAO();
			
			for(int i=1;i<=30;i++)
			{	
				//Document doc=Jsoup.connect("https://www.genie.co.kr/magazine?ctid=1&pg="+i).get();
				//Document doc=Jsoup.connect("https://www.genie.co.kr/magazine?ctid=26&pg="+i).get();
				//Document doc=Jsoup.connect("https://www.genie.co.kr/magazine?ctid=25&pg="+i).get();
				//Document doc=Jsoup.connect("https://www.genie.co.kr/magazine?ctid=19&pg="+i).get();
				Document doc=Jsoup.connect("https://www.genie.co.kr/magazine?ctid=8&pg="+i).get();
				Elements link=doc.select("div.list-normal ul.clearfix li a");

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
    					
    					MusicNewsVO vo=new MusicNewsVO();
    					vo.setCateno(5);
    					vo.setTitle(title);
    					vo.setRegdate(regdate);
    					vo.setPoster(poster);
    					vo.setPoster2(poster2);
    					vo.setContent(content);
                    	
    					
    					dao.musicNewsInsert(vo);
                    	System.out.println("save end");
    					
					}catch(Exception ex) {}	
				}
			
			}
		}catch(Exception ex) {}
		
	}

}
