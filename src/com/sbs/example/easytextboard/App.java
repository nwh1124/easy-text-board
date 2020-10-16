package com.sbs.example.easytextboard;

import java.util.Scanner;

public class App {
	
	Article[] article = new Article[11];
	
	public void run() {
		
		for(int i = 1; i<=10; i++ ) {
			article[i] = new Article();
		}
		
		Scanner scanner = new Scanner(System.in);
		int id = 0;
		
		while(true) {
			System.out.printf("명령어 : ");
			String command = scanner.nextLine();
			
			if( command.equals("article add")) {
				System.out.println("== 게시물 등록 ==");
				++id;
				String title;
				String body;				
					if( id >= 1 && id <= 9 ) {			
						System.out.printf("제목 : ");
						title = scanner.nextLine();
						System.out.printf("내용 : ");
						body = scanner.nextLine();
						
						article[id].id = id;
						article[id].title = title;
						article[id].body = body;
						
						System.out.printf("==%d번 게시물 등록되었습니다==\n",id);
						}
					else {	
						System.out.println("== 저장 공간이 부족합니다 ==");	}		
						}
			
				else if( command.equals("article list")) {
					System.out.println("== 게시물 리스트 ==");
					if (id<=0 || id >= 11) {
						System.out.printf("== %d번 게시물은 존재하지 않습니다 ==\n",id);
						}
					else {
						System.out.printf("번호 : %d 제목 : %s\n", article[id].id, article[id].title);
						}
					}
				else if( command.equals("system exit")) {
					System.out.println("== 프로그램 종료 ==");
					break;
					
				}else if(command.startsWith("article detail ")) {
					int inputedId = 0;
					inputedId = Integer.parseInt(command.split(" ")[2]);
					
					if( inputedId > id || inputedId <=0 ) {
						System.out.printf("== %d번 게시물은 존재하지 않습니다 ==\n",inputedId);
					}
					else {
						System.out.printf("번호 : %s\n제목 : %s\n내용 : %s\n",
								article[inputedId].id,article[inputedId].title,article[inputedId].body);					
						}
				}
				else{
					System.out.printf("== '%s'는 지정되지 않은 명령어 ==\n",command);
				}
		}
		scanner.close();
	}

}
