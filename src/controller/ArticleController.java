package controller;

import java.util.List;
import java.util.Scanner;

import container.Container;
import dto.Article;
import dto.Board;
import dto.Recommand;
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
		}else if(cmd.startsWith("article list")) {
			list(cmd);
		}else if(cmd.startsWith("article detail")) {
			detail(cmd);
		}else if(cmd.startsWith("article delete")) {
			delete(cmd);
		}else if(cmd.startsWith("article modify")) {
			modify(cmd);
		}else if(cmd.startsWith("article search")) {
			search(cmd);
		}else if(cmd.startsWith("article writeReply")) {
			writeReply(cmd);
		}else if(cmd.startsWith("article modReply")) {
			modReply(cmd);
		}else if(cmd.startsWith("article delReply")) {
			delReply(cmd);
		}else if(cmd.startsWith("article myReply")) {
			myReply(cmd);
		}else if(cmd.equals("article makeBoard")) {
			makeBoard();
		}else if(cmd.startsWith("article selectBoard")) {
			selectBoard(cmd);
		}else if(cmd.startsWith("article curruntBoard")) {
			curruntBoard();
		}else if(cmd.startsWith("article recommand")) {
			recommand(cmd);
		}else if(cmd.startsWith("article cancelRecommand")) {
			cancelRecommand(cmd);
		}
		
	}

	private void cancelRecommand(String cmd) {

		System.out.println("== 게시물 추천 취소 ==");
		
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
			System.out.println("= 추천 취소할 게시물 번호를 정수로 입력해주세요 =");
			return;
		}
		
		Article article = articleService.getArticle(inputedId);
		
		if(article == null) {
			System.out.println("= 존재하지 않는 게시물입니다 =");
			return;
		}
		
		List<Recommand> recommands = articleService.getRecommand(inputedId);
		
		for(Recommand rec : recommands) {
			if(rec.memberId == Container.session.getLoginedId()) {
				System.out.println("= 추천이 취소되었습니다 =");
				articleService.cancelRecommand(inputedId, rec.id);
				return;
			}
		}
		
		System.out.printf("= %d번 게시물을 추천하지 않은 상태입니다 =", inputedId);
		
	}

	private void recommand(String cmd) {
		
		System.out.println("== 게시물 추천 ==");
		
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
			System.out.println("= 추천할 게시물 번호를 정수로 입력해주세요 =");
			return;
		}
		
		Article article = articleService.getArticle(inputedId);
		
		if(article == null) {
			System.out.println("= 존재하지 않는 게시물입니다 =");
			return;
		}
		
		List<Recommand> recommands = articleService.getRecommand(inputedId);
		
		for(Recommand rec : recommands) {
			if(rec.memberId == Container.session.getLoginedId()) {
				System.out.println("== 이미 추천한 게시물입니다 ==");
				return;
			}
		}
		
		articleService.doRecommand(inputedId, Container.session.getLoginedId());
		System.out.printf("= %d번 게시물이 추천되었습니다 =\n", inputedId);
		
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
		
		System.out.printf("댓글 작성 : ");		
		String body = sc.nextLine();
		
		articleService.writeReply(body, Container.session.getLoginedId(), inputedId);
		System.out.printf("= %d번 게시물에 댓글을 남겼습니다 =\n", inputedId);
		
	}
	
	private void modReply(String cmd) {
		
		if(Container.session.getLogined() == false) {
			System.out.println("= 로그인 후 이용해주세요 =");
			return;
		}	

		System.out.println("== 댓글 수정 ==");
		
		List<Reply> replys = articleService.getReplysByMemberId(Container.session.getLoginedId());
		
		if(replys == null) {
			System.out.println("= 작성한 댓글이 없습니다 =");
			return;
		}
		
		System.out.println("= 작성한 댓글 목록 =");
		System.out.println("번호 / 작성일 / 내용 / 원본 게시물");
		
		for(Reply reply : replys) {
			System.out.printf("%d / %s / %s / %s\n", reply.id, reply.regDate, reply.body, reply.extra__title);
		}
		
		int missCountMax = 3;
		int missCount = 0;
		int modRepCmd = 0;
		
		while(true) {
			if(missCount >= missCountMax) {
				System.out.println("= 댓글 수정 취소 =");
				return;
			}
			
			System.out.printf("수정할 댓글 번호 : ");
			modRepCmd = Integer.parseInt(sc.nextLine());
			
			Reply reply = articleService.getReplysById(modRepCmd);
			
			if(modRepCmd == 0) {
				System.out.println("= 수정할 댓글 번호를 입력해주세요 =");
				missCount++;
				continue;
			}else if(reply == null) {
				System.out.println("= 존재하지 않는 댓글입니다 =");
				missCount++;
				continue;
			}else if(reply.memberId != Container.session.getLoginedId()) {
				System.out.println("= 댓글 작성자가 아닙니다 =");
				missCount++;
				continue;
			}else {
				break;
			}
		}
			
		System.out.printf("수정할 내용 : ");
		String modRepBody = sc.nextLine();
		
		articleService.modReply(modRepCmd, modRepBody);
		System.out.println("= 댓글이 수정되었습니다 =");
		
	}
	
	private void delReply(String cmd) {

		if(Container.session.getLogined() == false) {
			System.out.println("= 로그인 후 이용해주세요 =");
			return;
		}	

		System.out.println("== 댓글 삭제 ==");
		
		List<Reply> replys = articleService.getReplysByMemberId(Container.session.getLoginedId());
		
		if(replys == null) {
			System.out.println("= 작성한 댓글이 없습니다 =");
			return;
		}
		
		System.out.println("= 작성한 댓글 목록 =");
		System.out.println("번호 / 작성일 / 내용 / 원본 게시물");
		
		for(Reply reply : replys) {
			System.out.printf("%d / %s / %s / %s\n", reply.id, reply.regDate, reply.body, reply.extra__title);
		}
		
		int missCountMax = 3;
		int missCount = 0;
		int modRepCmd = 0;
		
		while(true) {
			if(missCount >= missCountMax) {
				System.out.println("= 댓글 삭제 취소 =");
				return;
			}
			
			System.out.printf("삭제할 댓글 번호 : ");
			modRepCmd = Integer.parseInt(sc.nextLine());
			
			Reply reply = articleService.getReplysById(modRepCmd);
			
			if(modRepCmd == 0) {
				System.out.println("= 삭제할 댓글 번호를 입력해주세요 =");
				missCount++;
				continue;
			}else if(reply == null) {
				System.out.println("= 존재하지 않는 댓글입니다 =");
				missCount++;
				continue;
			}else if(reply.memberId != Container.session.getLoginedId()) {
				System.out.println("= 댓글 작성자가 아닙니다 =");
				missCount++;
				continue;
			}else {
				break;
			}
		}
		
		articleService.delReply(modRepCmd);
		System.out.println("= 댓글이 삭제되었습니다 =");
		
	}
	
	private void myReply(String cmd) {

		System.out.println("== 작성한 댓글 목록 ==");
		
		if(Container.session.getLogined() == false) {
			System.out.println("= 로그인 후 이용해주세요 =");
			return;
		}	
		
		List<Reply> replys = articleService.getReplysByMemberId(Container.session.getLoginedId());
		
		if(replys == null) {
			System.out.println("= 작성한 댓글이 없습니다 =");
			return;
		}
		
		System.out.println("번호 / 작성일 / 내용 / 원본 게시물");
		
		for(Reply reply : replys) {
			System.out.printf("%d / %s / %s / %s\n", reply.id, reply.regDate, reply.body, reply.extra__title);
		}
		
	}
	
	private void search(String cmd) {
		
		System.out.println("== 게시물 검색 ==");
		
		int inputedId = 1;
		String searchWord = "";
		String[] cmdBits = cmd.split(" ");
		
		if(cmdBits.length > 2) {
			searchWord = cmdBits[2];
		}else {
			System.out.println("= 검색어를 입력해주세요 =");
			return;
		}
		
		if(cmdBits.length > 3) {
			try {			
				inputedId = Integer.parseInt(cmdBits[3]);
			}
			catch(Exception e) {
				System.out.println("= 페이지 번호를 정수로 입력해주세요 =");
				return;
			}
		}
		
		List<Article> articles = articleService.getSearchArticles(searchWord);
		
		int pageGap = 5;
		int pagePoint = articles.size() / pageGap;
		int startPoint = (articles.size() - 1) - (pageGap*(inputedId - 1));
		int endPoint = startPoint - pageGap;
		
		if(pagePoint < 0 || pagePoint + 1 < inputedId || inputedId <= 0){
			System.out.printf("= %d번 페이지는 존재하지 않습니다 =\n", inputedId);
			return;
		}
		
		System.out.println("게시판 / 번호 / 작성일 / 작성자 / 제목 / 조회수");
		
		if(pagePoint == 0 || pagePoint + 1 == inputedId) {
			for(int i = startPoint ; i >= 0 ; i--) {	
				String boardName = articleService.getBoardNameById(articles.get(i).boardId);
				String writer = memberService.getMemberNameById(articles.get(i).memberId);
				System.out.printf("%s 게시판 /", boardName);
				System.out.printf(" %d /", articles.get(i).id);
				System.out.printf(" %s /", articles.get(i).regDate);
				System.out.printf(" %s /", writer);
				System.out.printf(" %s /", articles.get(i).title);
				System.out.printf(" %d \n", articles.get(i).hit);				
			}
		}else if(pagePoint > 0 && pagePoint + 1 >= inputedId) {
			for(int i = startPoint ; i > endPoint ; i--) {	
				String boardName = articleService.getBoardNameById(articles.get(i).boardId);
				String writer = memberService.getMemberNameById(articles.get(i).memberId);
				System.out.printf("%s 게시판 /", boardName);
				System.out.printf(" %d /", articles.get(i).id);
				System.out.printf(" %s /", articles.get(i).regDate);
				System.out.printf(" %s /", writer);
				System.out.printf(" %s /", articles.get(i).title);
				System.out.printf(" %d \n", articles.get(i).hit);				
			}
		}
/*		
		for(Article article : articles) {
			
			String boardName = articleService.getBoardNameById(article.boardId);
			String writer = memberService.getMemberNameById(article.memberId);

			System.out.printf("%s /", boardName);
			System.out.printf(" %d /", article.id);
			System.out.printf(" %s /", article.regDate);
			System.out.printf(" %s /", writer);
			System.out.printf(" %s \n", article.title);
			
		}
*/		
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
		
		if(detailArticle == null) {
			System.out.printf("= %d번 게시물은 존재하지 않습니다 =\n", inputedId);
			return;
		}
		
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
		System.out.printf("추천 수 : %d\n", detailArticle.recommand);
		
		articleService.doHitPlus(inputedId);
		
		System.out.println("== 댓글 작성자 / 댓글 내용 ==");
		
		List<Reply> replys = articleService.getReplysByArticleId(inputedId);
		
		if(replys == null) {
			System.out.println("= 등록된 댓글이 없습니다 =");
			return;
		}
			
		for(Reply reply : replys) {					
			writer = memberService.getMemberNameById(reply.memberId);
			System.out.printf("%s / %s\n", writer, reply.body);
		}
		
	}

	private void list(String cmd) {
		
		System.out.println("= 게시물 목록 =");
		
		List<Article> listArticle = articleService.getListArticle(Container.session.getSelectedBoardId());
		
		int inputedId = 1;
		String[] cmdBits = cmd.split(" ");
		try {
			if(cmdBits.length > 2) inputedId = Integer.parseInt(cmdBits[2]);
		}
		catch(Exception e) {
			System.out.println("= 게시물 페이지를 정수로 입력해주세요 =");
			return;
		}
				
		int pageGap = 5;
		int pagePoint = listArticle.size() / pageGap;
		int startPoint = (listArticle.size() - 1) - (pageGap*(inputedId - 1));
		int endPoint = startPoint - pageGap;
		
		if(pagePoint < 0 || pagePoint + 1 < inputedId || inputedId <= 0){
			System.out.printf("= %d번 페이지는 존재하지 않습니다 =\n", inputedId);
			return;
		}
		
		System.out.println("게시판 / 번호 / 작성일 / 작성자 / 제목 / 조회수");
		
		if(pagePoint == 0 || pagePoint + 1 == inputedId) {
			for(int i = startPoint ; i >= 0 ; i--) {				
				System.out.printf("%s 게시판 /", listArticle.get(i).extra__boardName);
				System.out.printf(" %d /", listArticle.get(i).id);
				System.out.printf(" %s /", listArticle.get(i).regDate);
				System.out.printf(" %s /", listArticle.get(i).extra__writer);
				System.out.printf(" %s /", listArticle.get(i).title);
				System.out.printf(" %d \n", listArticle.get(i).hit);				
			}
		}else if(pagePoint > 0 && pagePoint + 1 >= inputedId) {
			for(int i = startPoint ; i > endPoint ; i--) {				
				System.out.printf("%s 게시판 /", listArticle.get(i).extra__boardName);
				System.out.printf(" %d /", listArticle.get(i).id);
				System.out.printf(" %s /", listArticle.get(i).regDate);
				System.out.printf(" %s /", listArticle.get(i).extra__writer);
				System.out.printf(" %s /", listArticle.get(i).title);
				System.out.printf(" %d \n", listArticle.get(i).hit);				
			}
		}
		
/*		for(Article article : listArticle) {
			
			//String boardName = articleService.getBoardNameById(article.boardId);
			//String writer = memberService.getMemberNameById(article.memberId);

			System.out.printf("%s 게시판 /", article.extra__boardName);
			System.out.printf(" %d /", article.id);
			System.out.printf(" %s /", article.regDate);
			System.out.printf(" %s /", article.extra__writer);
			System.out.printf(" %s /", article.title);
			System.out.printf(" %d \n", article.hit);
			
		}
*/
		
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
