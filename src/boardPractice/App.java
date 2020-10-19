package boardPractice;

import java.util.Scanner;

public class App {
	
	Article[] articles = new Article[10];

	public void run() {
		
		for (int i = 0; i < articles.length; i++) {
			articles[i] = new Article();
		}
		for (int i = 0; i < articles.length; i++) {
			articles[i].id = i+1;
		}
		
		int id = 0;
		Scanner sc = new Scanner(System.in);
		String command;
		
		while(true) {
			System.out.printf("명령어 ) ");
			command = sc.nextLine();
		
			if (command.equals("article add")) {
				
				System.out.println("==게시물 등록==");
				if(id > 10) {
					System.out.println("==저장 공간이 부족합니다==");
				}else {

					System.out.printf("제목 : ");
					String title = sc.nextLine();
					System.out.printf("내용 : ");
					String body = sc.nextLine();
					
					
					articles[id].title = title;
					articles[id].body = body;
					id++;
					System.out.printf("%d번 글이 생성되었습니다\n",id);
				}
				
			}else if (command.equals("article list")) {
				System.out.println("==게시물 목록==");
				if(id <= 0) {
					System.out.println("==저장된 게시물이 없습니다==");
				}else {
					for(int i = 0; i < id; i++) {
					System.out.printf("번호 : %d\n제목 : %s\n",
							articles[i].id,articles[i].title);
					}
				}
				
			}else if (command.startsWith("article detail ")) {
				String[] commandBits = command.split(" ");
				int inputedId = Integer.parseInt(commandBits[2]);
				
				if(inputedId <= 0 || inputedId > id) {
					System.out.printf("==%d번 글이 존재하지 않습니다==\n",inputedId);
				}else {
					System.out.printf("번호 : %d\n제목 : %s\n내용 : %s\n",
							articles[(inputedId-1)].id,articles[(inputedId-1)].title,articles[(inputedId-1)].body);
				}
				
			}else if (command.equals("system exit")) {
				System.out.println("==프로그램 종료==");
				break;			
				
			}else {
				System.out.println("==잘못된 명령어==");
			}
			
		}sc.close();
		
	}
	
}
