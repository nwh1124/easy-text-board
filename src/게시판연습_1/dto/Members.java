package 게시판연습_1.dto;

public class Members {
	
	public int num;
	public String id;
	public String pass;
	public String name;
	public String time;
	
	public Members(int num, String id, String pass, String name, String time){
		
		this.num = num;
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.time = time;
		
	}
	public Members(){
		this.num = 0;
		this.id = "";
		this.pass = "";
		this.name = "";
		
	}
}
