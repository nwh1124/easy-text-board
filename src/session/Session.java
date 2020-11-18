package session;

public class Session {

	boolean logined;
	int loginedId;
	int selectedBoardId;
	
	public Session() {
		
		logined = false;
		loginedId = 0;
		selectedBoardId = 1;
		
	}
	
	public void login(int id) {
		logined = true;
		loginedId = id;
	}

	public boolean getLogined() {
		return logined;
	}
	
	public int getLoginedId() {
		return loginedId;
	}

	public void logout() {
		logined = false;
		loginedId = 0;
	}
	
	public int getSelectedBoardId() {
		return selectedBoardId;
	}

	public void selectBoard(int inputedId) {
		selectedBoardId = inputedId;
	}

}
