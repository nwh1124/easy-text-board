package com.sbs.example.easytextboard;

import java.util.Scanner;

public class App {
	Article article1 = new Article();
	
	Article article2 = new Article();
	
	public Article getArticle(int id) {
		if( id == 1 ) {
			return article1;
		}
		if( id == 2 ) {
			return article2;
		}
		return null;
	}
	
	public void run() {
		int id = 0;
		
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			System.out.printf("명령어 : ");
			String command = scanner.nextLine();
			
			if( command.equals("article add")) {
				
				System.out.println("==게시물 등록==");
				
				if( id>=0 && id <= 2) {
					id++;
					System.out.printf("제목 : ");
					String title = scanner.nextLine();
					System.out.printf("내용 : ");
					String body = scanner.nextLine();
															
					getArticle(id).id = id;
					getArticle(id).title = title;
					getArticle(id).body = body;
					
				}
				else {
					System.out.println("==저장 공간이 부족합니다==");
				}
				
			}else if( command.equals("article list")) {
				
				System.out.println("==게시물 목록==");
				
				if( id == 0 ) {
					System.out.printf("==등록된 게시물이 없습니다==\n");
				}
				else for(int i = 1; i <= id; i++) {
				if( id >= 1 ) {
					System.out.printf("번호 : %d\n제목 : %s\n", 
							getArticle(i).id,getArticle(i).title);
				}}
				
			}else if( command.equals("system exit")) {
				
				System.out.println("==시스템 종료==");
				break;
				
			}else if( command.startsWith("article detail ")) {
				String commandBits[] = command.split(" ");
				int inputedId = Integer.parseInt(commandBits[2]);
					if( id <= 0 && id <= 3 ){
						System.out.printf("==번호가 바르지 않습니다==\n");
						continue;
					}
					else {
						System.out.printf("번호 : %d\n제목 : %s\n내용 : %s\n",
							getArticle(inputedId).id,getArticle(inputedId).title,getArticle(inputedId).body);
					}
			}else{
				System.out.println("==잘못된 명령어==");
			}
		}
		scanner.close();
	}
}
