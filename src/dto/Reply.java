package dto;

import java.util.Map;

public class Reply {
	
	public int id;
	public String regDate;
	public String body;
	public int articleId;
	public int memberId;
	public String extra__title;
	
	public Reply(Map<String, Object> map) {
		
		this.id = (int)map.get("id");
		this.regDate = (String)map.get("regDate");
		this.body = (String)map.get("body");
		this.articleId = (int)map.get("articleId");
		this.memberId = (int)map.get("memberId");
		if (map.containsKey("extra__title")) {
			this.extra__title = (String)map.get("extra__title");
		}

	}

}
