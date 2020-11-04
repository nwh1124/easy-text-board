package dto;

public class Article {

	public int num;
	public int memberId;
	public String title;
	public String body;
	
	public Article() {
		
	}
	public Article(int num, int memberId, String title, String body) {
	
		this.num = num;
		this.memberId = memberId;
		this.title = title;
		this.body = body;
		
	}

}
