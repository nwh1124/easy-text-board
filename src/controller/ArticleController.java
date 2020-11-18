package controller;

import java.util.List;
import java.util.Scanner;

import container.Container;
import dto.Article;
import dto.Board;
import dto.Reply;
import service.ArticleService;
import service.MemberService;

public class ArticleController extends Controller{
	
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
		}else if(cmd.startsWith("article modify")) {
			modify(cmd);
		}else if(cmd.startsWith("article writeReply")) {
			writeReply(cmd);
		}else if(cmd.equals("article makeBoard")) {
			makeBoard();
		}else if(cmd.startsWith("article selectBoard")) {
			selectBoard(cmd);
		}else if(cmd.startsWith("article curruntBoard")) {
			curruntBoard();
		}
		
	}

	private void curruntBoard() {

		System.out.println("== 접속 중인 게시판 정보 ==");
		
		Board board = articleService.getBoardById(Container.session.getSelectedBoardId());
		
		System.out.printf("게시판 번호 : %d\n", board.id);
		System.out.printf("게시판 이름 : %s 게시판\n", board.name);
		
	}

	private void selectBoard(String cmd) {
		
		System.out.println("== 게시판 선택 ==");
		
		int inputedId = 0;
		String[] cmdBits = cmd.split(" ");
		try {
			inputedId = Integer.parseInt(cmdBits[2]);
		}
		catch(Exception e) {
			System.out.println("= 게시판 번호를 정수로 입력해주세요 =");
			return;
		}
		
		Board board = articleService.getBoardById(inputedId);
		
		if(board == null) {
			System.out.println("= 존재하지 않는 게시판입니다 =");
			return;
		}
		
		Container.session.selectBoard(inputedId);
		System.out.printf("= %s(%d번) 게시판이 선택되었습니다 =\n", board.name, board.id);
		
	}

	private void makeBoard() {

		if(Container.session.getLogined() == false) {
			System.out.println("= 로그인 후 이용해주세요 =");
			return;
		}
		
		System.out.println("== 게시판 생성 ==");
		
		int missCountMax = 3;
		int missCount = 0;
				
		String boardName;
		
		while(true) {
			if(missCount >= missCountMax) {
				System.out.println("= 게시판 생성 취소 =");
				return;
			}
			
			System.out.printf("게시판 이름 : ");
			boardName = sc.nextLine();
			if(boardName.trim().length() == 0) {
				System.out.println("= 게시판 이름을 입력해주세요 =");
				missCount++;
				continue;
			}else {
				break;
			}
		}
		
		int id = articleService.makeBoard(boardName);
		System.out.printf("= %s(%d번) 게시판이 생성되었습니다 =\n", boardName, id);
		
	}

	private void writeReply(String cmd) {

		if(Container.session.getLogined() == false) {
			System.out.println("= 로그인 후 이용해주세요 =");
			return;
		}		
		
		int inputedId = 0;
		String[] cmdBits = cmd.split(" ");
		try {
			inputedId = Integer.parseInt(cmdBits[2]);
		}
		catch(Exception e) {
			System.out.println("= 게시물 번호를 정수로 입력해주세요 =");
			return;
		}
		
		System.out.printf("== %d번 게시물에 댓글 쓰기 ==", inputedId);		
		String body = sc.nextLine();
		
		articleService.writeReply(body, Container.session.getLoginedId(), inputedId);
		
	}

	private void modify(String cmd) {

		if(Container.session.getLogined() == false) {
			System.out.println("= 로그인 후 이용해주세요 =");
			return;
		}		
		
		int inputedId = 0;
		String[] cmdBits = cmd.split(" ");
		try {
			inputedId = Integer.parseInt(cmdBits[2]);
		}
		catch(Exception e) {
			System.out.println("= 게시물 번호를 정수로 입력해주세요 =");
			return;
		}
		
		if(Container.session.getLoginedId() != articleService.getArticleWriterId(inputedId)) {
			System.out.println("= 게시물 작성자가 아닙니다 =");
			return;
		}
		
		int missCountMax = 3;
		String modTitle;
		String modBody;
			
		System.out.println("== 게시물 수정 ==");
		
		int missCount = 0;
		while( true ) { 
			if(missCount >= missCountMax) {
				System.out.println("= 취소 =");
				return;
			}
			
			System.out.printf("제목 : ");
			modTitle = sc.nextLine();
						
			if(modTitle.trim().length() == 0) {
				System.out.println("= 제목을 입력해주세요 =");
				missCount++;
				continue;
			}else {
				break;	
			}
		}
		
		System.out.printf("내용 : ");
		modBody = sc.nextLine();
		
		articleService.modify(modTitle, modBody, inputedId);
		System.out.printf("= %d번 게시물이 수정되었습니다 =\n", inputedId);
		
	}

	private void delete(String cmd) {
		
		if(Container.session.getLogined() == false) {
			System.out.println("= 로그인 후 이용해주세요 =");
			return;
		}		
		
		int inputedId = 0;
		String[] cmdBits = cmd.split(" ");
		try {
			inputedId = Integer.parseInt(cmdBits[2]);
		}
		catch(Exception e) {
			System.out.println("= 게시물 번호를 정수로 입력해주세요 =");
			return;
		}
		
		if(Container.session.getLoginedId() != articleService.getArticleWriterId(inputedId)) {
			System.out.println("= 게시물 작성자가 아닙니다 =");
			return;
		}
		
		articleService.delete(inputedId);
		
		System.out.printf("= %d번 게시물이 삭제되었습니다 =\n", inputedId);
		
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
		
		System.out.println("== 게시물 상세 ==");
		
		String boardName = articleService.getBoardNameById(detailArticle.boardId);
		String writer = memberService.getMemberNameById(detailArticle.memberId);
		System.out.printf("게시판 : %s\n", boardName);
		System.out.printf("번호 : %d\n", detailArticle.id);
		System.out.printf("작성일 : %s\n", detailArticle.regDate);
		System.out.printf("수정일 : %s\n", detailArticle.updateDate);
		System.out.printf("제목 : %s\n", detailArticle.title);
		System.out.printf("내용 : %s\n", detailArticle.body);
		System.out.printf("작성자 : %s\n", writer);
		System.out.printf("조회수 : %d\n", detailArticle.hit);
		
		System.out.println("== 댓글 작성자 / 댓글 내용 ==");
		
		List<Reply> replys = articleService.getReplysByArticleId(inputedId);
			
		for(Reply reply : replys) {					
			writer = memberService.getMemberNameById(reply.memberId);
			System.out.printf("%s / %s\n", writer, reply.body);
		}
		
	}

	private void list() {
		
		List<Article> listArticle = articleService.getListArticle();
		
		System.out.println("= 게시물 목록 =");
		
		System.out.println("번호 / 작성일 / 작성자 / 제목");
		
		for(Article article : listArticle) {
			
			String boardName = articleService.getBoardNameById(article.boardId);
			//String writer = memberService.getMemberNameById(article.memberId);

			System.out.printf("%s /", boardName);
			System.out.printf(" %d /", article.id);
			System.out.printf(" %s /", article.regDate);
			System.out.printf(" %s /", article.extra__writer);
			System.out.printf(" %s \n", article.title);
			
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
		
		int id = articleService.add(title, body, Container.session.getLoginedId(), Container.session.getSelectedBoardId());
		
		System.out.printf("= %d번 게시물이 작성되었습니다! =\n", id);
		
	}
	
}
