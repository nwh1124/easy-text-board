package Session;

public class Session {
	
	boolean nowLogined;
	int nowLoginedId;
	
	public Session() {
		
		nowLogined = false;
		nowLoginedId = 0;
		
	}

	public boolean getNowLogined() {
		return nowLogined;
	}

	public void login(int num) {
		nowLogined = true;
		nowLoginedId = num;		
	}

	public int getNowLoginedId() {
		return nowLoginedId;
	}

	public void logout() {
		nowLogined = false;
		nowLoginedId = 0;
	}

}
