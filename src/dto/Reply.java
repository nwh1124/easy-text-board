package dto;

import java.util.Map;

public class Reply {
	
	public int id;
	public String regDate;
	public String body;
	public int articleId;
	public int memberId;
	
	public Reply(Map<String, Object> map) {
		
		this.id = (int)map.get("id");
		this.regDate = (String)map.get("regDate");
		this.body = (String)map.get("body");
		this.articleId = (int)map.get("articleId");
		this.memberId = (int)map.get("memberId");

	}

}
