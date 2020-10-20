package board;

import java.util.Scanner;

public class App {
	
	
	int id = 0;
	int articlesCount = 0;	
	Article[] articles = new Article[100];
	
	public int getIndex(int id) {
		for (int i = 0; i <= id; i++) {
			if (articles[i].id == id) 
				return i;
		}
		return -1;		
	}
		
	public void run( ){
		
		for (int i = 0; i < articles.length; i++) {
			articles[i] = new Article();			
		}

		String command;
		Scanner sc = new Scanner(System.in);
		
		while(true) {		
			System.out.printf("명령어 ) ");
			command = sc.nextLine();
		
			if(command.equals("article add")) {
				if(articlesCount >= articles.length) {
					System.out.println("==저장 공간이 부족합니다==");
				}else {
					System.out.printf("제목 : ");
					String title = sc.nextLine();
					System.out.printf("내용 : ");
					String body = sc.nextLine();
					
					articles[articlesCount].id = id+1;
					articles[articlesCount].title = title;
					articles[articlesCount].body = body;
					id++;
					articlesCount++;
					System.out.printf("%d번 글이 생성되었습니다\n",id);
				}
				
			}else if(command.equals("article list")) {
				System.out.println("==게시물 리스트==");
				if(articlesCount == 0) {
					System.out.println("==생성된 게시물이 없습니다==");
				}
				else {
					for(int i = 0; i < articlesCount; i++) {
						System.out.printf("번호 : %d\n제목: %s\n",articles[i].id,articles[i].title);
					}
				}
				
			}else if(command.startsWith("article detail ")) {
				String[] commandBits = command.split(" ");
				int inputedId = Integer.parseInt(commandBits[2]);
				int selIndex = getIndex(inputedId);
				if(inputedId > id || inputedId <= 0) {
					System.out.printf("==%d번 글이 존재하지 않습니다==\n",inputedId);					
				}
				else {
					System.out.printf("번호 %d\n제목 %s\n내용 %s\n",
							articles[selIndex].id,articles[selIndex].title,articles[selIndex].body);
				}
							
			}else if(command.startsWith("article delete ")) {
				String[] commandBits = command.split(" ");
				int inputedId = Integer.parseInt(commandBits[2]);
				int selIndex = getIndex(inputedId);
				if(selIndex == -1) {
					System.out.printf("%d번 게시물은 존재하지 않습니다\n",inputedId);
				}else {
					articles[selIndex].id = articles[selIndex+1].id;
					articles[selIndex].title = articles[selIndex+1].title;
					articles[selIndex].body = articles[selIndex+1].body; 
					System.out.printf("==%d번 게시물이 삭제되었습니다==\n",inputedId);
					articlesCount--;
				}
			}
			
			else if(command.equals("system exit")) {
				System.out.println("==종료==");
				break;
				
			}else if(command.equals("array")) {
				for(int i = 0; i < articlesCount+2; i++) {
					System.out.printf("위치 : articles[%d] 번호 : %d 제목 : %s 내용물 %s\n",
							i, articles[i].id, articles[i].title, articles[i].body);
				}
			}
			
			else {
				System.out.println("==잘못된 명령어==");
			}	
		}
		sc.close();
		
	}
	
}
