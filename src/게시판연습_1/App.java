package 게시판연습_1;

import java.util.Scanner;

import Controller.ArticleController;
import Controller.MemberController;
import Controller.MeemberController;

public class App {
	
	ArticleController articleController = new ArticleController();
	MemberController memberController = new MemberController();
	MeemberController meemberController = new MeemberController();
	
	void run() {
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			
			
				
			System.out.printf("명령어 ) ");
			String command = sc.nextLine();
			if(command.startsWith("article ")) {
				articleController.run(sc, command);
			}
			else if(command.startsWith("member ")|| command.equals("whoami")) {
				meemberController.run(sc, command);
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
}
