package com.sbs.example.easytextboard;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int id = 0;
		
		int article1_id = 0;
		String article1_title = "";
		String article1_body= "";
		
		int article2_id = 0;
		String article2_title = "";
		String article2_body= "";
		
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
					
					article1_id = id;
					article1_title = title;
					article1_body = body;
					
					System.out.printf("==%d번 게시물 등록되었습니다==\n",id);
					}
					
					
				
				else if( id==2 ) {			
					System.out.printf("제목 : ");
					title = scanner.nextLine();
					System.out.printf("내용 : ");
					body = scanner.nextLine();
						
					article2_id = id;
					article2_title = title;
					article2_body = body;
					
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
					System.out.printf("번호 : %d 제목 : %s\n", article1_id, article1_title);
					}
					if( id >= 2 ) {
						System.out.printf("번호 : %d 제목 : %s\n", article2_id, article2_title);
					}
			}
			
			}else if( command.equals("system exit")) {
				System.out.println("==프로그램 종료==");
				break;
				
				}else if(command.equals("article detail 1")) {
					System.out.printf("번호 %d \n제목 : %s \n내용 : %s\n", article1_id, article1_title, article1_body);
				}else if(command.equals("article detail 2")) {
					System.out.printf("번호 %d \n제목 : %s \n내용 : %s\n", article2_id, article2_title, article2_body);
				}
			else{
				System.out.println("==지정되지 않은 명령어==");
			}
		}
		scanner.close();
	}
}