package com.sbs.example.easytextboard;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int id = 0;
		
		Article article1 = new Article();
		Article article2 = new Article();
		
		while(true) {
			System.out.printf("명령어 : ");
			String command = scanner.nextLine();
			
			if( command.equals("article add")) {
				System.out.println("==게시물 등록==");
				++id;
				String title;
				String body;				
					if( id==1 ) {			
						System.out.printf("제목 : ");
						title = scanner.nextLine();
						System.out.printf("내용 : ");
						body = scanner.nextLine();
						
						article1.id = id;
						article1.title = title;
						article1.body = body;
						
						System.out.printf("==%d번 게시물 등록되었습니다==\n",id);
						}
					else if( id==2 ) {			
						System.out.printf("제목 : ");
						title = scanner.nextLine();
						System.out.printf("내용 : ");
						body = scanner.nextLine();
							
						article2.id = id;
						article2.title = title;
						article2.body = body;
						
						System.out.printf("==%d번 게시물 등록되었습니다==\n",id);
						}
				
					else {	System.out.println("==저장 공간이 부족합니다==");	}		
						}
			
				else if( command.equals("article list")) {
					System.out.println("==게시물 리스트==");
					if (id==0) {
						System.out.println("==등록된 게시물이 없습니다==");
						}
					else {
						if( id >= 1 ) {
							System.out.printf("번호 : %d 제목 : %s\n", article1.id, article1.title);
							}
						if( id >= 2 ) {
							System.out.printf("번호 : %d 제목 : %s\n", article2.id, article2.title);
							}
						}
				
				}else if( command.equals("system exit")) {
					System.out.println("==프로그램 종료==");
					break;
					
				}else if(command.startsWith("article detail ")) {
					int inputedId = 0;
					inputedId = Integer.parseInt(command.split(" ")[2]);
					if( inputedId == 1) {
						System.out.printf("번호 %d \n제목 : %s \n내용 : %s\n", article1.id, article1.title, article1.body);
						}
					if( inputedId == 2) {
						System.out.printf("번호 %d \n제목 : %s \n내용 : %s\n", article2.id, article2.title, article2.body);
						}
					if( inputedId >= 3) {
						System.out.printf("==게시물이 없습니다==\n", article1.id, article1.title, article1.body);
						}
				}
				else{
					System.out.println("==지정되지 않은 명령어==");
				}
		}
		scanner.close();
	}
}