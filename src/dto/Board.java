package dto;

import java.util.Map;

public class Board {
		
	public int id;
	public String regDate;
	public String updateDate;
	public String code;
	public String name;
	public int cnt;
	
	public Board() {
		
	}
	
	public Board(Map<String, Object> map) {
		
		this.id = (int)map.get("id");
		this.regDate = (String)map.get("regDate");
		this.updateDate = (String)map.get("updateDate");
		this.code = (String)map.get("code");
		this.name = (String)map.get("name");
		if(map.containsKey("cnt")) {
			this.cnt = (int)map.get("cnt");
		}
		
	}

}
