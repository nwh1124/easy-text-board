import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class App {
	
	Article[] articles = new Article[32];	
	int lastArticleId = 0;
	int articleArrayNum = 0;
	Date date;
	SimpleDateFormat dayTimeFormat;
	
	Member member[] = new Member[2];
	Member nowLogin = new Member();
	int memberCount = 0;
	
		App() {
		for(int i = 0; i < member.length; i++) {
				member[i] = new Member();
		}	
			
		for(int i = 0; i < articles.length; i++) {
			articles[i] = new Article();
		}
		lastArticleId = 0;
		articleArrayNum = 0;
		dayTimeFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
		
		for ( int i = 0; i < articles.length; i++) {
			lastArticleId++;
			articles[i].id = lastArticleId;
			articles[i].title = "0"+lastArticleId;
			articles[i].body = "0"+lastArticleId;
			Date initDate = new Date();
			articles[i].time = dayTimeFormat.format(initDate);
			articleArrayNum++;
		}
		for(int i = 0; i < member.length; i++) {
			member[i] = new Member(); 
		}
	}
	
	public void run() {
		
	    new	App();
		
		Scanner sc = new Scanner(System.in);
		String command; 
				
		while(true) {
			
			System.out.printf("명령어 ) ");
			command = sc.nextLine();
			
			if(command.equals("article add")) {
				
				preAdd();				
				add(sc);
				
			}else if(command.startsWith("article list")) {
				
				listing(command);
				
			}else if(command.startsWith("article delete ")) {
				
				delete(command);
				
			}else if(command.startsWith("article modify ")) {
				
				modify(command, sc);
			
			}else if(command.startsWith("article detail ")) {
				
				detail(command);
				
			}else if(command.startsWith("article search ")) {
				
				search(command);
			
			}else if(command.equals("member join")) {
				
				Join(sc);		
				
			}else if(command.equals("member login")){
				
				Login(sc);
			
			}else if(command.equals("log info")) {
				
				System.out.println("== 현재 회원 정보 ==");
				System.out.printf("ID = %s Password = %s Name = %s\n",nowLogin.id,nowLogin.pass,nowLogin.name);
				
			}else if(command.equals("exit")) {
				System.out.println("== 종료 ==");
				break;
			}else {
				System.out.println("== 잘못된 명령어 입력 ==");
			}
		}sc.close();
	}

	

	private void Login(Scanner sc) {

		System.out.println("== 로그인 ==");
		System.out.printf("Id = ");
		String loginId = sc.nextLine();				
		
		int findId = -1;
		
		for (int i = 0; i < member.length; i++) {
			if(member[i].id.equals(loginId)) {
				findId = i;
				break;
			}
		}
		
		if(findId == -1) {
			if(member[findId].id == null)
				System.out.println("= 아이디가 없습니다 =");
			else {
				System.out.println("= 아이디가 없습니다 =");
			}
		}else {
				System.out.printf("Password = ");
				String Password = sc.nextLine();
			if(Password.equals(member[findId].pass )) {
					System.out.printf("=%s님 로그인 되었습니다 =\n",member[findId].name);
					nowLogin = member[findId];
			}else {
					System.out.println("= 비밀번호가 틀립니다 =");
			}
		}
		
	}

	private void Join(Scanner sc) {

		System.out.println("== 회원 가입 ==");
		System.out.println("가입할 아이디 : ");
		String LogId = sc.nextLine();
		System.out.printf("가입할 비밀번호 : ");
		String LogPass = sc.nextLine();
		System.out.printf("가입자 이름 : ");
		String LogName = sc.nextLine();
		
		member[memberCount].memNum = memberCount+1;
		member[memberCount].id = LogId;
		member[memberCount].pass = LogPass;
		member[memberCount].name = LogName;		
		
		System.out.printf("==%d번 회원으로 가입되었습니다==\nID = %s Password = %s Name = %s\n",
				member[memberCount].memNum,member[memberCount].id,member[memberCount].pass,member[memberCount].name);
		
		memberCount++;
	}

	private void search(String command) {

		System.out.println("== 게시물 검색 ==");
		String selectedStr = commandSelectStr(command, 2);
		int articleArrayNum[] = new int[articles.length];
		int searchStack = 0;
		
		for( int i = 0; i < this.articleArrayNum; i++) {
			if ( articles[i].title.contains(selectedStr) || articles[i].body.contains(selectedStr) ) {
				articleArrayNum[searchStack] = i;
				searchStack++;
			}
		}
		String[] commandCheck = command.split(" ");
		
		if ( commandCheck.length > 3 ) {
			int selectedSearchNum = commandSelectInt(command, 3);
			int searchArrayNum[] = new int[searchStack];
			int searchPageSize = 10;
			int searchPage = searchStack/searchPageSize;
			int searchStartPoint = (searchStack-1) - (searchPageSize*(selectedSearchNum-1));
			int searchEndPoint = searchStartPoint - searchPageSize;
			
			for(int i = 0; i < searchStack; i++) {
				searchArrayNum[i] = articleArrayNum[i];
			}
			
			if( searchStack == 0 ) {
				System.out.println("= 아무것도 검색되지 않았습니다 =\n");
			}else if(selectedSearchNum <= 0) {
				System.out.println("= 페이지가 존재하지 않습니다 =");
			}else if( searchPage == 0 || searchPage < selectedSearchNum) {
				for(int i = searchStartPoint; i >= 0; i--) {
					System.out.printf("= %d번 게시물이 검색되었습니다 =\n",searchArrayNum[i]+1);					
				}
			}else {
				for ( int i = searchStartPoint; i > searchEndPoint; i-- ) {
					System.out.printf("= %d번 게시물이 검색되었습니다 =\n",searchArrayNum[i]+1);
				}
			}
		}else {
			System.out.println("= 명령어가 잘못되었습니다 =");
		}
		
	}
	
	private void detail(String command) {
		
		int selectedNum = commandSelectInt(command, 2);
		int arrayNum = -1;
		
		for( int i = 0; i < articles.length; i++) {
			if ( articles[i].id == selectedNum ) {
				arrayNum = i;
				break;
			}
		}
		if ( arrayNum == -1 ) {
			System.out.printf("== %d번 게시물은 존재하지 않습니다 ==\n", selectedNum);
		}
		else {
			System.out.printf("== 게시물 상세 ==\n날짜 : %s\n번호 : %d\n제목 : %s\n내용 : %s\n",
								 articles[arrayNum].time, articles[arrayNum].id, articles[arrayNum].title, articles[arrayNum].body);
		}
		
	}
	
	private void modify(String command, Scanner sc) {

		int selectedNum = commandSelectInt(command, 2);
		int arrayNum = -1;
		
		for( int i = 0; i < articles.length; i++) {
			if ( articles[i].id == selectedNum ) {
				arrayNum = i;
				break;
			}
		}
		if ( arrayNum == -1 || articleArrayNum == 0) {
			System.out.printf("== %d번 게시물이 존재하지 않습니다 ==\n", selectedNum);
		}else {
			
			System.out.printf("== %d번 게시물을 수정합니다 ==\n", selectedNum);
			System.out.printf("제목 수정 : ");
			articles[arrayNum].title = sc.nextLine();
			System.out.printf("내용 수정 : ");
			articles[arrayNum].body = sc.nextLine();
			
			System.out.println("== 수정이 완료되었습니다 ==");
		}
		
	}
	
	private void delete(String command) {
		
		int selectedNum = commandSelectInt(command, 2);
		int arrayNum = -1;
		
		for( int i = 0; i < articles.length; i++) {
			if ( articles[i].id == selectedNum ) {
				arrayNum = i;
				break;
			}
		}if ( arrayNum == -1 || articleArrayNum == 0) {
			System.out.printf("== %d번 게시물이 존재하지 않습니다 ==\n", selectedNum);
		}else {
			for ( int i = arrayNum; i < (articles.length - 1); i++) {
				articles[i] = articles[i + 1];
			}
			articleArrayNum--;
			System.out.printf("== %d번 게시물이 삭제되었습니다 ==\n", selectedNum);
		}
		
	}
	
	private void listing(String command) {
		
		String commandBits[] = command.split(" ");
		
		int searchPage = 1;
		int commandCheck = 1;
		
		if (commandBits.length >= 3) {
			searchPage = commandSelectInt(command, 2);
			commandCheck = commandSelectInt(command, 2);
		}
		
		int listSize = 10;
		int pagePoint = articleArrayNum / listSize; 
		int startPoint = (articleArrayNum -1) - listSize * (searchPage - 1);
						
		System.out.printf("== 게시물 목록 = %d 페이지 ==\n",searchPage);
		
		if ( commandCheck <= 0  ) {
			System.out.printf("= %d 페이지는 존재하지 않습니다 =\n= 현재 게시물 갯수 %d개 = 총 %d 페이지 =\n", commandCheck, articleArrayNum, pagePoint+1);
		}
		else if(pagePoint == 0 || pagePoint < searchPage) {
			for ( int i = startPoint; i >= 0; i-- ) {
				System.out.printf("번호 : %d 제목 : %s\n",articles[i].id, articles[i].title);
			}
		}else if(pagePoint > 0) {
			for ( int i = startPoint; i > (startPoint - listSize); i--) {
				System.out.printf("번호 : %d 제목 : %s\n",articles[i].id, articles[i].title);
			}
		}
		if ( articleArrayNum == 0 ) {
			System.out.println("== 아무 게시물도 없습니다 ==");
		}
	}

	private void preAdd() {
		if( articleArrayNum >= articles.length) {					
			Article[] newArticle = new Article[articles.length*2];
			for ( int i = 0; i < articles.length*2; i++) {
				newArticle[i] = new Article();
			}
			for ( int i = 0; i < articles.length; i++) {
				newArticle[i] = articles[i];
			}
		articles = newArticle;
		}		
	}
	
	public void add(Scanner sc) {
		
		System.out.println("== 게시물 등록 ==");
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();
		date = new Date();
		String dateString = dayTimeFormat.format(date);
		lastArticleId++;	
		
		articles[articleArrayNum].id = lastArticleId;
		articles[articleArrayNum].title = title;
		articles[articleArrayNum].body = body;
		articles[articleArrayNum].time = dateString;
		articleArrayNum++;
		
		System.out.printf("== %d번 게시물이 저장되었습니다 ==\n", lastArticleId);
	}
	
	private String commandSelectStr(String command, int i) {
		String commandBits[] = command.split(" ");
		String selectedStr = commandBits[i];
		
		return selectedStr;
	}

	private int commandSelectInt(String command, int i) {
		
		String commandBits[] = command.split(" ");
		if (commandBits.length < 3) {
			System.out.println("= 명령어가 잘못되었습니다 =");
		}
		else if (Integer.parseInt(commandBits[i]) < 0 ) {
			return -1;
		}
		int seletedNum = Integer.parseInt(commandBits[i]);
		
		return seletedNum;
	}

}