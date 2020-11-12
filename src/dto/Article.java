package dto;

public class Article {

	public int boardId;
	public int num;
	public int memberId;
	public String regDate;
	public String writer;
	public String title;
	public String body;
	public int hit;
	
	public Article() {
		
	}
	
	public Article(int boardId, int num, int memberId, String title, String body) {
		
		this.boardId = boardId;
		this.num = num;
		this.memberId = memberId;
		this.title = title;
		this.body = body;
		
	}
	
	public Article(int boardId, int num, int memberId, String regDate, String writer, String title, String body, int hit) {
	
		this.boardId = boardId;
		this.num = num;
		this.memberId = memberId;
		this.regDate = regDate;
		this.writer = writer;
		this.title = title;
		this.body = body;
		this.hit = hit;
		
	}

}
