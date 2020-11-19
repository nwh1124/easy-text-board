package dto;

import java.util.Map;

public class Recommand {
		
	public int id;
	public String regDate;
	public int articleId;
	public int memberId;
	public boolean recommand;
	
	public Recommand(Map<String, Object> map) {
		
		this.id = (int)map.get("id");
		this.regDate = (String)map.get("regDate");
		this.articleId = (int)map.get("articleId");
		this.memberId = (int)map.get("memberId");
		this.recommand = (boolean)map.get("recommand");
		
	}

}
