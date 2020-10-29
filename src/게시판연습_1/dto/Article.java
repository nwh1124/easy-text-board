package 게시판연습_1.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Article {
	
	public int id;
	public String title;
	public String body;
	public String time;
	
	public Article(int id, String title, String body){
		
		this.id = id;
		this.title = title;
		this.body = body;
		SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd hh:mm:ss");
		this.time = format.format(new Date());
		
	}

}
