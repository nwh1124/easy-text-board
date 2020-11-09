package Session;

public class Session {
	
	boolean nowLogined;
	int nowLoginedId;
	int selectedBoardId;
	
	public Session() {
		
		nowLogined = false;
		nowLoginedId = 0;
		selectedBoardId = 0;
		
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

	public int getSelectedBoardId() {
		return selectedBoardId;
	}

	public void selectBoard(int inputedId) {
		selectedBoardId = inputedId;
	}

}
