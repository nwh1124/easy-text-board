package controller;

import java.util.List;
import java.util.Scanner;

import container.Container;
import dto.Article;
import service.ArticleService;
import service.MemberService;

public class ArticleController {
	
	Scanner sc;
	private ArticleService articleService;
	private MemberService memberService;

	public ArticleController() {
	
		sc = Container.sc;
		articleService = Container.articleService;
		memberService = Container.memberService;
		
	}
	
	public void doCommand(String cmd) {
		
		if(cmd.equals("article add")) {
			add();
		}else if(cmd.equals("article list")) {
			list();
		}else if(cmd.startsWith("article detail")) {
			detail(cmd);
		}else if(cmd.startsWith("article delete")) {
			delete(cmd);
		}
		
	}

	private void delete(String cmd) {
		
		int inputedId = 0;
		String[] cmdBits = cmd.split(" ");
		try {
			inputedId = Integer.parseInt(cmdBits[2]);
		}
		catch(Exception e) {
			System.out.println("= 게시물 번호를 정수로 입력해주세요 =");
			return;
		}
		
		articleService.delete(inputedId);
		
		System.out.printf("= %d번 게시물이 삭제되었을껄? =\n", inputedId);
		
	}

	private void detail(String cmd) {
				
		int inputedId = 0;
		String[] cmdBits = cmd.split(" ");
		try {
			inputedId = Integer.parseInt(cmdBits[2]);
		}
		catch(Exception e) {
			System.out.println("= 게시물 번호를 정수로 입력해주세요 =");
			return;
		}
		
		Article detailArticle = articleService.getArticle(inputedId);
		
		System.out.println("= 게시물 상세 =");
		
		String writer = memberService.getMemberNameById(detailArticle.memberId);
		System.out.printf("번호 : %d\n작성일 : %s\n제목 : %s\n내용 : %s\n작성자 : %s\n", 
				detailArticle.id, detailArticle.regDate, detailArticle.title, detailArticle.body, writer);
		
	}

	private void list() {
		
		List<Article> listArticle = articleService.getListArticle();
		
		System.out.println("= 게시물 목록 =");
		
		System.out.println("번호 / 작성일 / 작성자 / 제목");
		
		for(Article article : listArticle) {
			String writer = memberService.getMemberNameById(article.memberId);
			System.out.printf("%d / %s / %s / %s\n", article.id, article.regDate, writer, article.title);
		}
		
	}

	private void add() {
		
		if(Container.session.getLogined() == false) {
			System.out.println("= 로그인 후 이용해주세요 =");
			return;
		}
		
		int missCountMax = 3;
		String title;
		String body;
			
		System.out.println("= 게시물 작성 =");
		
		int missCount = 0;
		while( true ) { 
			if(missCount >= missCountMax) {
				System.out.println("= 취소 =");
				return;
			}
			
			System.out.printf("제목 : ");
			title = sc.nextLine();
						
			if(title.trim().length() == 0) {
				System.out.println("= 제목을 입력해주세요 =");
				missCount++;
				continue;
			}else {
				break;	
			}
		}
		
		System.out.printf("내용 : ");
		body = sc.nextLine();
		
		int id = articleService.add(title, body, Container.session.getLoginedId());
		
		System.out.printf("= %d번 게시물이 작성되었습니다! =\n", id);
		
	}
	
}
