package main;

import java.util.Scanner;

import Controller.ArticleController;
import Controller.Controller;
import Controller.MemberController;

public class App {
	
	private MemberController memberController;
	private ArticleController articleController;
	
	public App() {
		
		memberController = new MemberController();
		articleController = new ArticleController();
		
	}

	void run() {
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			
			System.out.printf("명령어 ) ");
			String cmd = sc.nextLine();
			
			if(cmd.equals("system exit")) {
				System.out.println("= 종료 =");
				break;
			}
			
			Controller controller = getController(cmd);
			
			if(controller == null) {
				System.out.println("= 잘못된 명령어 입력 =");
				continue;
			}	
			
			controller.doCommand(cmd);
			
		}
		
		sc.close();
		
	}

	private Controller getController(String cmd) {
		
		if(cmd.startsWith("article")) {
			return articleController;
		}else if(cmd.startsWith("member")) {
			return memberController;
		}
		return null;
	}
	
}
