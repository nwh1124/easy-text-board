package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import Container.Container;
import Service.ArticleService;
import Service.MemberService;
import dto.Article;

public class ArticleController extends Controller {
	
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
		}else if(cmd.startsWith("article modify")) {			
			modify(cmd);			
		}else if(cmd.startsWith("article delete")) {			
			delete(cmd);			
		}else {
			System.out.println("= 잘못된 명령어 입력 =");
			return;
		}
		
	}

	private void delete(String cmd) {
		if(Container.session.getNowLogined() == false) {
			System.out.println("= 로그인 후 이용해주세요 =");
			return;
		}
		
		int inputedId = 1;
		String[] cmdBits = cmd.split(" ");
		
		if(cmdBits.length > 2) {
			try {
				inputedId = Integer.parseInt(cmdBits[2]);
			}
			catch(NumberFormatException e) {
				System.out.println("= 게시물 번호는 정수로 입력해주세요 =");
				return;
			}			
		}else {
			System.out.println("= 수정할 게시물의 번호를 입력해주세요 =");
			return;
		}
		
		if(inputedId <= 0 || inputedId > articleService.getArticleSize()) {
			System.out.printf("= %d번 게시물은 존재하지 않습니다 =\n", inputedId);
			return;
		}
		if(articleService.getArticleMemberIdByInput(inputedId) != Container.session.getNowLoginedId()) {
			System.out.printf("= %d번 게시물의 작성자가 아닙니다 =\n", inputedId);
			return;
		}
		
		System.out.println("= 게시물 삭제 =");
		articleService.delete(inputedId);
		System.out.printf("= %d번 게시물이 삭제되었습니다 =\n", inputedId);
		
		
	}

	private void modify(String cmd) {
		
		if(Container.session.getNowLogined() == false) {
			System.out.println("= 로그인 후 이용해주세요 =");
			return;
		}
		
		int inputedId = 1;
		String[] cmdBits = cmd.split(" ");
		
		if(cmdBits.length > 2) {
			try {
				inputedId = Integer.parseInt(cmdBits[2]);
			}
			catch(NumberFormatException e) {
				System.out.println("= 게시물 번호는 정수로 입력해주세요 =");
				return;
			}			
		}else {
			System.out.println("= 수정할 게시물의 번호를 입력해주세요 =");
			return;
		}
		
		if(inputedId <= 0 || inputedId > articleService.getArticleSize()) {
			System.out.printf("= %d번 게시물은 존재하지 않습니다 =\n", inputedId);
			return;
		}
		if(articleService.getArticleMemberIdByInput(inputedId) != Container.session.getNowLoginedId()) {
			System.out.printf("= %d번 게시물의 작성자가 아닙니다 =\n", inputedId);
			return;
		}
		
		System.out.println("= 게시물 수정 =");

		System.out.printf("수정할 제목 : ");
		String modTitle = sc.nextLine();
		System.out.printf("수정할 내용 : ");
		String modBody = sc.nextLine();
		
		articleService.modify(inputedId, modTitle, modBody);
		
	}

	private void detail(String cmd) {

		System.out.println("= 게시물 상세 =");
		
		int inputedId = 1;
		String[] cmdBits = cmd.split(" ");
		
		if(cmdBits.length > 2) {
			try {
				inputedId = Integer.parseInt(cmdBits[2]);
			}
			catch(NumberFormatException e) {
				System.out.println("= 게시물 번호는 정수로 입력해주세요 =");
				return;
			}
			
		}
		
		if(inputedId <= 0 || inputedId > articleService.getArticleSize()) {
			System.out.printf("= %d번 게시물은 존재하지 않습니다 =\n", inputedId);
			return;
		}
		
		
		
		Article detailArticle = articleService.getArticleByInput(inputedId);
		
		
		String writer = memberService.getMemberNameByNum(detailArticle.memberId);
		System.out.printf("번호 : %d\n작성자 : %s\n제목 : %s\n내용 : %s\n", detailArticle.num, writer, detailArticle.title, detailArticle.body);
		
	}

	private void list() {
		
		ArrayList<Article> listArticle = articleService.getArticle();
		
		if(listArticle.size() == 0) {
			System.out.println("= 등록된 게시물이 없습니다 =");
			return;
		}
		
		System.out.println("= 게시물 목록 =");
		
		System.out.println("번호 / 작성자 / 제목");
		
		for(Article article : listArticle) {
			String writer = memberService.getMemberNameByNum(article.num);
			System.out.printf("%d / %s / %s\n", article.num, writer ,article.title);
		}
		
		
	}

	private void add() {
		
		if(Container.session.getNowLogined() == false) {
			System.out.println("= 로그인 후 이용해주세요 =");
			return;
		}
		
		System.out.println("= 게시물 등록 =");

		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();
		
		int id = articleService.add(Container.session.getNowLoginedId(), title, body);
		
		System.out.printf("= %d번 게시물이 등록되었습니다 =\n", id);
		
	}

}
