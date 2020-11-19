package dto;

import java.util.Map;

public class Article {

	public int id;
	public String regDate;
	public String updateDate;
	public String title;
	public String body;
	public int hit;
	public int recommand;
	public int memberId;
	public int boardId;
	public String extra__writer;
	public String extra__boardName;
	
	public Article() {
		
	}

	public Article(Map<String, Object> map) {

		this.id = (int)map.get("id");
		this.regDate = (String)map.get("regDate");
		this.updateDate = (String)map.get("updateDate");
		this.title = (String)map.get("title");
		this.body = (String)map.get("body");
		this.hit = (int)map.get("hit");
		this.recommand = (int)map.get("recommand");
		this.memberId = (int)map.get("memberId");
		this.boardId = (int)map.get("boardId");
		
		if(map.containsKey("extra__writer")) {
			this.extra__writer = (String)map.get("extra__writer");
		}
		if(map.containsKey("extra__boardName")) {
			this.extra__boardName = (String)map.get("extra__boardName");
		}		
	}	
}
