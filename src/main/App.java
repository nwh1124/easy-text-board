package main;

import java.util.Scanner;

import Container.Container;
import Controller.ArticleController;
import Controller.Controller;
import Controller.MemberController;
import Service.ArticleService;
import Service.MemberService;

public class App {
	
	private MemberController memberController;
	private MemberService memberService;
	
	private ArticleController articleController;
	private ArticleService articleService;
	
	public App() {
		
		memberController = Container.memberController;
		memberService = Container.memberService;
		
		articleController = Container.articleController;
		articleService = Container.articleService;
		
		makeTestData();
		
	}

	private void makeTestData() {

		// 회원 생성
		int firstMemberId = memberService.join("aaa", "aaa", "aaa");
		int secondMemberId = memberService.join("bbb", "bbb", "bbb");
		
		// 게시판 생성
		Container.articleService.makeBoard("공지사항");
		
/*		// 게시물 생성??
		for(int i = 1; i <= 5; i++) {
			articleService.add(articleService.iniBoardId(), firstMemberId, "제목" + i , "내용" + i);
			}
		for(int i = 1; i <= 5; i++) {
			articleService.add(articleService.iniBoardId(), secondMemberId, "제목" + i , "내용" + i);
			}			
*/		
		// 게시판 설정
		Container.session.selectBoard(articleService.iniBoardId());
		
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
