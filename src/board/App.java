package board;

import java.util.Scanner;

public class App {
	
	
	int id = 0;
	int articlesCount = 0;	
	Article[] articles = new Article[100];
		
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
					System.out.printf("%d번 글이이 생성되었습니다\n",id);
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
				if(inputedId > articlesCount || inputedId <= 0) {
					System.out.printf("==%d번 글이 존재하지 않습니다==\n",inputedId);					
				}
				else {
					System.out.printf("번호 %d\n제목 %s\n내용 %s\n",
							articles[(inputedId-1)].id,articles[(inputedId-1)].title,articles[(inputedId-1)].body);
				}
							
			}else if(command.startsWith("article delete ")) {
				String[] commandBits = command.split(" ");
				int inputedId = Integer.parseInt(commandBits[2]);
				
				if(inputedId <= 0 || inputedId > articlesCount) {
					System.out.println("==%d 번 게시물은 존재하지 않습니다==");
				}
				else {
					System.out.printf("==%d번 게시물이 삭제되었습니다==\n",inputedId);
					
					for(int i = 0; i < articlesCount; i++) {
						if(inputedId == articles[i].id) {
							while(i <= articlesCount ) {																	
							articles[i].id = articles[i+1].id;
							articles[i].title = articles[i+1].title;
							articles[i].body = articles[i+1].body;
							i ++;
							}
						}
					}									
				articlesCount--;
				}
			}
			
			else if(command.equals("system exit")) {
				System.out.println("==종료==");
				break;
				
			}else {
				System.out.println("==잘못된 명령어==");
			}	
		}
		sc.close();
		
	}
	
}
