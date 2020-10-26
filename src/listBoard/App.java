package listBoard;

import java.util.Scanner;
import java.util.ArrayList;

public class App {
	
	ArrayList<Article> articles = new ArrayList<Article>() ;
	int lastArticleId = 0 ;
	
	public void run(){
				
		for(int i = 0; i < 32; i++) {
			lastArticleId++;
			articles.add(new Article(i+1,"제목"+(i+1),"내용"+(i+1)));
			
		}
				
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			
			System.out.printf("명령어 ) ");
			String command = sc.nextLine();			
			
			if(command.equals("article add")) {
				
				add(sc, command);
								
			}else if(command.startsWith("article list")) {
				
				list(command);
				
			}else if(command.startsWith("article detail ")) {
				
				detail(command);
			
			}
			else if(command.startsWith("article delete ")) {
				
				delete(command);
				
			}
			else if(command.startsWith("article modify ")) {
				
				modify(sc, command);
				
				
			}else if(command.startsWith("article search ")) {
				
				search(command);
				
			}
			else if ( command.equals("exit")) {
				System.out.println("== 종료 ==");
				break;
			}
		}
	sc.close();	
	}

	private void add(Scanner sc, String command) {

		System.out.println("== 게시물 등록 ==");

		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();
		lastArticleId++;
		
		articles.add(new Article(lastArticleId, title, body));
		
	}

	private void list(String command) {

		String[] commandBits = command.split(" ");
		int selectedNum = 0;
		try {
			selectedNum = Integer.parseInt(commandBits[2]);
			System.out.printf("== 게시물 리스트 = %d페이지 ==\n",selectedNum);
		}
		catch(NumberFormatException e) {
			System.out.println("= 페이지를 양의 정수로 입력해주세요 =");
			return;
		}
		
		int pageSelect = 1;
		int pageSize = 10;
		int pageCount = articles.size()/pageSize;
		
		if(selectedNum <1 || pageCount+1 < selectedNum ) {
			System.out.println("= 페이지 숫자가 잘못되었습니다 =");
		}else {
			pageSelect = selectedNum;
		}
		int startPoint = (articles.size()-1) -10*(pageSelect-1);
		if(selectedNum <= pageCount) {
			for(int i = startPoint; i > startPoint - pageSize; i--) {
				System.out.printf("번호 %d / 제목 %s / 내용 %s\n",
						articles.get(i).id,articles.get(i).title,articles.get(i).body);
			}
		}else if(pageSelect > pageCount) {
			for(int i = startPoint; i >= 0; i--) {
				System.out.printf("번호 %d / 제목 %s / 내용 %s\n",
						articles.get(i).id,articles.get(i).title,articles.get(i).body);
			}
		}
		
	}

	private void detail(String command) {
		
		String[] commandBits = command.split(" ");
		int selectedNum = 0;
		try {
			selectedNum = Integer.parseInt(commandBits[2]);
			System.out.printf("== 게시물 상세 ==\n");
		}
		catch(NumberFormatException e) {
			System.out.println("= 번호를 양의 정수로 입력해주세요 =");
			return;
		}
		
		int searchNum = -1;
		
		for(int i = 0; i < articles.size(); i++) {
			if (articles.get(i).id == selectedNum) {
				searchNum = i;
				break;
			}
		}
		if(searchNum == -1) {
			System.out.println("= 해당 게시물을 찾을 수 없습니다 =");
		}else {
			System.out.printf("번호 %d / 제목 %s / 내용 %s\n",
					articles.get(searchNum).id,articles.get(searchNum).title,articles.get(searchNum).body);
		}
		
	}

	private void delete(String command) {
		
		String[] commandBits = command.split(" ");
		int selectedNum = 0;
		try {
			selectedNum = Integer.parseInt(commandBits[2]);
			System.out.println("= 게시물 삭제 =");
		}
		catch(NumberFormatException e) {
			System.out.println("= 페이지를 양의 정수로 입력해주세요 =");
			return;
		}
		
		int searchNum = -1;
		
		for(int i = 0; i < articles.size(); i++) {
			if (articles.get(i).id == selectedNum) {
				searchNum = i;
				break;
			}
		}
		if(searchNum == -1) {
			System.out.println("= 해당 게시물을 찾을 수 없습니다 =");
		}else {
			System.out.printf("= %d번 게시물이 삭제되었습니다 =\n", articles.get(searchNum).id);
			articles.remove(searchNum);
		}
		
	}

	private void modify(Scanner sc,String command) {

		String[] commandBits = command.split(" ");
		int selectedNum = 0;
		try {
			selectedNum = Integer.parseInt(commandBits[2]);
			System.out.println("= 게시물 수정 =");
		}
		catch(NumberFormatException e) {
			System.out.println("= 페이지를 양의 정수로 입력해주세요 =");
			return;
		}
		
		int searchNum = -1;
		
		for(int i = 0; i < articles.size(); i++) {
			if (articles.get(i).id == selectedNum) {
				searchNum = i;
				break;
			}
		}
		if(searchNum == -1) {
			System.out.println("= 해당 게시물을 찾을 수 없습니다 =");
		}else {
			System.out.printf("제목 : ");
			String title = sc.nextLine();
			System.out.printf("내용 : ");
			String body = sc.nextLine();
			
			System.out.printf("= %d번 게시물이 수정되었습니다 =\n", articles.get(searchNum).id);
			articles.set(searchNum, new Article(articles.get(searchNum).id, title, body));
		}
	}

	private void search(String command) {
		


		String[] commandBits = command.split(" ");
		String selectedStr = commandBits[2];
		int selectedNum = 0;
		try {
			selectedNum = Integer.parseInt(commandBits[3]);
			System.out.println("= 게시물 검색 =");
		}
		catch(NumberFormatException e) {
			System.out.println("= 페이지를 양의 정수로 입력해주세요 =");
			return;
		}
		
		ArrayList<Article> articleSearch = new ArrayList<Article>();
		int searchStack = 0;
		
		for(int i = 0; i < articles.size(); i++) {
			if (articles.get(i).title.contains(selectedStr) || articles.get(i).body.contains(selectedStr)){
				articleSearch.add(new Article(articles.get(i).id, articles.get(i).title, articles.get(i).body));
				searchStack++;
			}
		}
		if(searchStack == 0) {
			System.out.println("= 해당 게시물을 찾을 수 없습니다 =");
		}else {
			
			int pageSelect = 1;
			int pageSize = 10;
			int pageCount = articleSearch.size()/pageSize;
			
			if(selectedNum <1 || pageCount+1 < selectedNum ) {
				System.out.println("= 페이지 숫자가 잘못되었습니다 =");
			}else {
				pageSelect = selectedNum;
			}
			int startPoint = (articleSearch.size()-1) -10*(pageSelect-1);
			if(selectedNum <= pageCount) {
				for(int i = startPoint; i > startPoint - pageSize; i--) {
					System.out.printf("번호 %d / 제목 %s / 내용 %s\n",
							articleSearch.get(i).id,articleSearch.get(i).title,articleSearch.get(i).body);
				}
			}else if(pageSelect > pageCount) {
				for(int i = startPoint; i >= 0; i--) {
					System.out.printf("번호 %d / 제목 %s / 내용 %s\n",
							articleSearch.get(i).id,articleSearch.get(i).title,articleSearch.get(i).body);
				}
			}
		}
	}

}


