package practice;

public class Members {
	
	int num;
	String id;
	String pass;
	String name;
	String time;
	
	Members(int num, String id, String pass, String name, String time){
		
		this.num = num;
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.time = time;
		
	}
	Members(){
		this.num = 0;
		this.id = "";
		this.pass = "";
		this.name = "";
		
	}
}
