package 게시판연습_1;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Article {
	
	int id;
	String title;
	String body;
	String time;
	
	Article(int id, String title, String body){
		
		this.id = id;
		this.title = title;
		this.body = body;
		SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd hh:mm:ss");
		this.time = format.format(new Date());
		
	}

}
