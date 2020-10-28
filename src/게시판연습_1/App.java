package 게시판연습_1;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
	
	ArrayList<Members> member = new ArrayList<Members>();	
	ArrayList<Members> nowLogin = new ArrayList<Members>();
	ArrayList<Article> articles = new ArrayList<Article>();
	int lastArticleId = 0;
	int lastMembersNum = 0;
	
	void run() {
		
		for (int i = 0; i < 32; i++) {
			lastArticleId++;
			articles.add(new Article(lastArticleId, "제목"+(i+1), "내용"+(i+1)));
		}
		nowLogin.add(new Members(0, "","","",""));
		
		Scanner sc = new Scanner(System.in);
				
		while(true) {
			
			System.out.printf("명령어 ) ");
			String command = sc.nextLine();
			
			if(command.equals("article add")) {
				
				add(sc, command);
				
			}
			else if(command.startsWith("article list")) {
				
				list(command);
				
			}
			else if(command.startsWith("article detail ")) {
				
				detail(command);
				
			}
			else if(command.startsWith("article delete ")) {
				
				delete(command);
				
			}
			else if(command.startsWith("article modify ")) {
				
				modify(sc, command);
				
			}else if(command.startsWith("article search ")) {
				
				search(command);
				
			}else if(command.equals("member join")) {
				
				join(sc);
				
			}else if(command.endsWith("member login")) {
				
				login(sc);
				
			}else if(command.equals("whoami")) {
				
				whoami();
				
			}else if(command.endsWith("log out")) {
				
				logOut();
				
			}
			else if(command.equals("exit")) {
				
				System.out.println("== 종료 ==");
				break;
				
			}else {
				
				System.out.println("== 잘못된 메인 명령어 ==");
				
			}
		}
				
		sc.close();
		
	}

	private void logOut() {
		
		if(nowLogin.get(0).num == 0) {
			System.out.println("== 로그인 중이 아닙니다 ==");
		}
		else {
		nowLogin.set(0, new Members(0, "", "", "",""));
		System.out.println("== 로그아웃 되었습니다 ==");
		return;
		}
		
	}

	private void whoami() {
		
		if(nowLogin.get(0).num == 0) {
			System.out.println("== 로그인 중이 아닙니다 ==");
			return;
		}
		else {
			System.out.println("== 현재 로그인 중인 회원 정보 ==");
			System.out.printf("등록일 : %s / Id %s / Password %s / 이름 %s\n",nowLogin.get(0).time,
					nowLogin.get(0).id,nowLogin.get(0).pass,nowLogin.get(0).name);
		}
	}

	private void login(Scanner sc) {
		
		if(nowLogin.get(0).num != 0) {
			System.out.println("== 이미 로그인 중입니다 ==");
		}else {
		
			System.out.println("== 로그인 ==");
	
			System.out.printf("ID : ");
			String logId = sc.nextLine();
			boolean searchId = true;
			
			for(Members mem : member) {
				if(mem.id.equals(logId)) {
					
					searchId = false;
					System.out.printf("Password : ");
					
					String logPass = sc.nextLine();
					if(mem.pass.equals(logPass)) {
						
						System.out.println("== 로그인 되었습니다 ==");
						nowLogin.set(0, mem);
						return;
						
					}else {
						System.out.println("== 비밀번호가 틀렸습니다 ==");
						return;
					}
				}
			}if(searchId) {
			System.out.println("== ID가 존재하지 않습니다 ==");	
		}
		
		}
		
	}

	private void join(Scanner sc) {

		System.out.println("== 회원 등록 ==");
		
		System.out.printf("ID : ");
		String id = sc.nextLine();
		
		for(Members mem : member) {
			if(mem.id.contains(id)) {
				System.out.println("== 아이디가 중복되었습니다 ==");
				return;
			}
		}
		
		System.out.printf("Password : ");
		String pass = sc.nextLine(); 
		System.out.printf("이름 : ");
		String name = sc.nextLine();
		lastMembersNum++;
		SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd hh:mm:ss");
		String time = format.format(new Date());
		
		member.add(new Members(lastMembersNum, id, pass, name, time));
		System.out.println("== 등록이 완료되었습니다 ==");
				
	}

	private void search(String command) {
		System.out.println("== 게시물 검색 ==");
		
		String[] commandBits = command.split(" ");
		String selectedStr = commandBits[2];
		ArrayList<Article> searchArticle = new ArrayList<Article>();
		
		for(int i = 0; i < articles.size(); i++) {
			if (articles.get(i).title.contains(selectedStr) || articles.get(i).body.contains(selectedStr)){
				searchArticle.add(new Article (articles.get(i).id,articles.get(i).title,articles.get(i).body));
			}
		}
		
		int pageSize = 10;
		int pagePoint = searchArticle.size() / pageSize;
		
		if(commandBits.length >= 1 && commandBits.length < 4) {
			int startPoint = searchArticle.size()-1;
			int endPoint = startPoint - pageSize;
			
			for (int i = startPoint; i > endPoint; i--) {
				System.out.printf("번호 : %d / 제목 : %s\n", searchArticle.get(i).id,searchArticle.get(i).title);					}
		}
		else if(commandBits.length >= 3) {
			
			int selectedPage = 0;
			try{
				selectedPage = Integer.parseInt(commandBits[3]);
			}catch(NumberFormatException e) {
				System.out.println("== 페이지 숫자를 양의 정수로 입력해주세요 ==");
				return;
			}
			
			int startPoint = (searchArticle.size()-1) - 10*(selectedPage-1);
			int endPoint = startPoint - pageSize;
			if( selectedPage > 0 && selectedPage <= pagePoint) {
				for (int i = startPoint; i > endPoint; i--) {
					System.out.printf("번호 : %d / 제목 : %s\n", searchArticle.get(i).id,searchArticle.get(i).title);
				}
			}else if(selectedPage == pagePoint+1) {
				for (int i = startPoint; i >= 0; i--) {
					System.out.printf("번호 : %d / 제목 : %s\n", searchArticle.get(i).id,searchArticle.get(i).title);				}
			}else {
				System.out.println("== 페이지 번호 지정이 잘못되었습니다 ==");
			}
		}
		
	}

	private void modify(Scanner sc, String command) {
		
		String[] commandBits = command.split(" ");
		int selectedNum = 0;
		try{
			selectedNum = Integer.parseInt(commandBits[2]);
		}catch(NumberFormatException e) {
			System.out.println("== 페이지 숫자를 양의 정수로 입력해주세요 ==");
			return;
		}
		
		if(selectedNum > articles.size() || selectedNum <= 0) {
			System.out.println("== 게시물을 찾을 수 없습니다 ==");
		}else {
			System.out.printf("수정된 제목 : ");
			String title = sc.nextLine();
			System.out.printf("수정된 내용 : ");
			String body = sc.nextLine();
			
			articles.set(selectedNum-1, new Article(selectedNum, title, body));
			System.out.printf("= %d번 게시물이 수정되었습니다 =\n", selectedNum);
		}
		
	}

	private void delete(String command) {
		
		String[] commandBits = command.split(" ");
		int selectedNum = 0;
		try{
			selectedNum = Integer.parseInt(commandBits[2]);
		}catch(NumberFormatException e) {
			System.out.println("== 페이지 숫자를 양의 정수로 입력해주세요 ==");
			return;
		}
		
		if(selectedNum > articles.size() || selectedNum <= 0) {
			System.out.println("== 게시물을 찾을 수 없습니다 ==");
		}else {
			articles.remove(selectedNum-1);
			System.out.printf("= %d번 게시물이 삭제되었습니다 =\n", selectedNum);
		}
		
	}

	private void detail(String command) {
		
		String[] commandBits = command.split(" ");
		
		int selectedNum = 0;
		try{
			selectedNum = Integer.parseInt(commandBits[2]);
		}catch(NumberFormatException e) {
			System.out.println("== 게시물 번호를 양의 정수로 입력해주세요 ==");
			return;
		}		
		
		if(selectedNum > articles.size() || selectedNum <= 0) {
			System.out.println("== 게시물을 찾을 수 없습니다 ==");
		}else {
			System.out.printf("= 게시물 상세 =\n");
			System.out.printf("날짜 : %s / 번호 : %d / 제목 : %s / 내용 : %s\n", articles.get(selectedNum-1).time,
					articles.get(selectedNum-1).id,articles.get(selectedNum-1).title,articles.get(selectedNum-1).body);
		}
		
	}

	private void list(String command) {

		String[] commandBits = command.split(" ");
		int pageSize = 10;
		int pagePoint = articles.size() / pageSize;
		
		if(commandBits.length >= 1 && commandBits.length < 3) {
			int startPoint = articles.size()-1;
			int endPoint = startPoint - pageSize;
			
			for (int i = startPoint; i > endPoint; i--) {
				System.out.printf("번호 : %d / 제목 : %s / 내용 : %s\n", articles.get(i).id,articles.get(i).title,articles.get(i).body);
			}
		}
		else if(commandBits.length >= 3) {
			int selectedPage = 0;
			try{
				selectedPage = Integer.parseInt(commandBits[2]);
			}catch(NumberFormatException e) {
				System.out.println("== 페이지 숫자를 양의 정수로 입력해주세요 ==");
				return;
			}
			int startPoint = (articles.size()-1) - 10*(selectedPage-1);
			int endPoint = startPoint - pageSize;
			if( selectedPage > 0 && selectedPage <= pagePoint) {
				for (int i = startPoint; i > endPoint; i--) {
					System.out.printf("번호 : %d / 제목 : %s\n", articles.get(i).id,articles.get(i).title);
				}
			}else if(selectedPage == pagePoint+1) {
				for (int i = startPoint; i >= 0; i--) {
					System.out.printf("번호 : %d / 제목 : %s\n", articles.get(i).id,articles.get(i).title);				}
			}else {
				System.out.println("== 페이지 번호 지정이 잘못되었습니다 ==");
			}
		}
		
	}

	private void add(Scanner sc, String command) {

		System.out.println("== 게시물 등록 ==");

		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();
		lastArticleId++;
		SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd hh:mm:ss");
		String time = format.format(new Date());
		
		articles.add(new Article(lastArticleId, title, body));
		System.out.printf("= 등록되었습니다 =\n");
		for(Article article : articles) {
			System.out.printf("%d %s %s\n",article.id,article.title,article.body);
		}
		
	}
	
}
