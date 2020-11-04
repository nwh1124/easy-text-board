package dto;

public class Member {
	
	public int num;
	public String loginId;
	public String loginPw;
	public String name;
	
	public Member() {
		
	}
	
	public Member(int num, String loginId, String loginPw, String name) {
		
		this.num = num;
		this.loginId = loginId;
		this.loginPw = loginPw;
		this.name = name;
		
	}

}
