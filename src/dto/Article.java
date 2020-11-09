package dto;

public class Article {

	public int boardId;
	public int num;
	public int memberId;
	public String title;
	public String body;
	
	public Article() {
		
	}
	public Article(int boardId, int num, int memberId, String title, String body) {
	
		this.boardId = boardId;
		this.num = num;
		this.memberId = memberId;
		this.title = title;
		this.body = body;
		
	}

}
