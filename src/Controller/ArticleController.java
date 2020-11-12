package Controller;

import java.util.ArrayList;

import java.util.Scanner;

import dto.Board;
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
		}else if(cmd.startsWith("article list")) {			
			list(cmd);			
		}else if(cmd.startsWith("article detail")) {			
			detail(cmd);			
		}else if(cmd.startsWith("article modify")) {			
			modify(cmd);			
		}else if(cmd.startsWith("article delete")) {			
			delete(cmd);			
		}else if(cmd.startsWith("article search")) {			
			search(cmd);			
		}else if(cmd.startsWith("article makeBoard")) {			
			makeBoard();			
		}else if(cmd.startsWith("article selectBoard")) {			
			selectBoard(cmd);			
		}else if(cmd.startsWith("article boardList")) {			
			boardList();			
		}else if(cmd.startsWith("article curruntBoard")) {			
			curuuntBoard();			
		}else if(cmd.equals("article getDate")) {			
			getDate();			
		}else {
			System.out.println("= 잘못된 명령어 입력 =");
			return;
		}
		
	}

	private void getDate() {
		articleService.getDate();
	}

	private void curuuntBoard() {
		
		String nowBoard = articleService.getBoardName(Container.session.getSelectedBoardId());
		System.out.printf("= 현재 선택된 게시판은 %s 게시판입니다 =\n", nowBoard);

	}

	private void boardList() {
		
		System.out.println("= 게시판 목록 =");
		
		
		ArrayList<Board> list = articleService.getArrayListBoard();
		
		int selectedBoardId = Container.session.getSelectedBoardId();
		
		System.out.printf("=%s(%d번) 게시판에 등록된 게시물 %d개 =\n", list.get(selectedBoardId-1).name, list.get(selectedBoardId-1).num, articleService.getArticleSize(selectedBoardId-1));
		
		for(int i = 0; i < list.size(); i++) {
			System.out.printf("게시판 번호 : %d / 게시판 이름 : %s\n", list.get(i).num, list.get(i).name);
		}
		
	}

	private void selectBoard(String cmd) {
		
		int inputedId = 1;
		String[] cmdBits = cmd.split(" ");
		
		if(cmdBits.length < 3) {
			System.out.printf("선택할 게시판 번호를 입력해주세요 : ");
			inputedId = sc.nextInt();
			
		}else {
			inputedId = Integer.parseInt(cmdBits[2]);
		}
		
		if(inputedId > articleService.getBoardSize()) {
			System.out.printf("= %d번 게시판은 존재하지 않습니다 =\n", inputedId);
			return;
		}
		
		System.out.println("= 게시판 선택 =");
		
		Container.session.selectBoard(inputedId);
		String boardName = articleService.getBoardName(inputedId);
		System.out.printf("= %s(%d번) 게시판이 선택되었습니다 =\n", boardName, inputedId);
		
	}

	private void makeBoard() {

//		System.out.println("= 관리자 체크 같은 거 ? =");
		
		System.out.printf("게시판 이름 : ");
		
		String boardName;
		
		while(true) {
			boardName = sc.nextLine();
			if(boardName.trim().length() > 0) {
				break;
			}else {
				System.out.println("= 생성할 게시판의 이름을 입력해주세요 =");
				continue;
			}				
		}
		
		int boardId = articleService.makeBoard(boardName);
		System.out.printf("= %s(%d번) 게시판이 생성되었습니다 =\n", boardName, boardId);
		
	}

	private void search(String cmd) {
			
		int inputedId = 1;
		String[] cmdBits = cmd.split(" ");
		
		String searchWord = cmdBits[2];
		ArrayList<Article> searchList = articleService.getArrayListBySearchWord(searchWord);
		
		if(cmdBits.length > 3) {
			inputedId = Integer.parseInt(cmdBits[3]);
		}
		
		int pageGap = 10;
		int pagePoint = searchList.size()/pageGap;
		int startPoint = (searchList.size() - 1) - pageGap*(inputedId - 1);
		int endPoint = startPoint - pageGap;
		
		if(pagePoint+1 < inputedId || startPoint < 0) {
			System.out.printf("= 게시물 페이지 입력이 잘못되었습니다 =\n");
			return;
		}
		
		System.out.println("= 게시물 목록 =");
		System.out.println("번호 / 작성자 / 제목");
		
		if(pagePoint == 0 || pagePoint + 1 == inputedId) {
			for(int i = startPoint; i >= 0; i--) {
				String writer = memberService.getMemberNameByNum(searchList.get(i).memberId);
				System.out.printf("%d / %s / %s\n", searchList.get(i).num, writer, searchList.get(i).title);
			}
		}else if(pagePoint > 0 && pagePoint >= inputedId ) {
			for(int i = startPoint; i > endPoint; i--) {
				String writer = memberService.getMemberNameByNum(searchList.get(i).memberId);
				System.out.printf("%d / %s / %s\n", searchList.get(i).num, writer, searchList.get(i).title);
			}
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
/*		
		if(inputedId <= 0 || inputedId > articleService.getArticleSize()) {
			System.out.printf("= %d번 게시물은 존재하지 않습니다 =\n", inputedId);
			return;
		}
		if(articleService.getArticleMemberIdByInput(inputedId) != Container.session.getNowLoginedId()) {
			System.out.printf("= %d번 게시물의 작성자가 아닙니다 =\n", inputedId);
			return;
		}
*/		
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
		
/*		if(inputedId <= 0 || inputedId > articleService.getArticleSize()) {
			System.out.printf("= %d번 게시물은 존재하지 않습니다 =\n", inputedId);
			return;
		}
		if(articleService.getArticleMemberIdByInput(inputedId) != Container.session.getNowLoginedId()) {
			System.out.printf("= %d번 게시물의 작성자가 아닙니다 =\n", inputedId);
			return;
		}
*/
		
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

	private void list(String cmd) {
		
		ArrayList<Article> listArticle = articleService.getArrayListListing(Container.session.getSelectedBoardId());
		String boardName = articleService.getBoardName(Container.session.getSelectedBoardId());
		
		if(listArticle.size() == 0) {			
			System.out.printf("= %s 게시판에 등록된 게시물이 없습니다 =\n", boardName);
			return;
		}
		
		System.out.printf("= %s 게시판 게시물 목록 =\n", boardName);
		
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
		
		int pageGap = 10;
		int pagePoint = listArticle.size() / pageGap;
		int startPoint = (listArticle.size() - 1) - pageGap*(inputedId - 1);
		int endPoint = startPoint - pageGap;
		
		System.out.println("번호 /	 날짜	 / 작성자 / 제목 / 조회수");
		
		if(pagePoint == 0 || pagePoint+1 == inputedId) {
			for(int i = startPoint; i >= 0; i--) {
				//String writer = memberService.getMemberNameByNum(listArticle.get(i).memberId);
				//boardName = articleService.getBoardName(listArticle.get(i).boardId);
				System.out.printf("%d / %s / %s / %s / %d\n", listArticle.get(i).num, listArticle.get(i).regDate, listArticle.get(i).writer, listArticle.get(i).title, listArticle.get(i).hit);
			}
		}else if(pagePoint+1 < inputedId) {
			System.out.println("= 선택된 페이지가 등록된 게시물보다 큽니다 =");
			return;			
		}else {
			for(int i = startPoint; i > endPoint; i--) {
				//String writer = memberService.getMemberNameByNum(listArticle.get(i).memberId);
				//boardName = articleService.getBoardName(listArticle.get(i).boardId);
				System.out.printf("%d / %s / %s / %s / %d\n", listArticle.get(i).num, listArticle.get(i).regDate, listArticle.get(i).writer, listArticle.get(i).title, listArticle.get(i).hit);
			}
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
		
		String writer = memberService.getMemberNameByNum(Container.session.getNowLoginedId());
		
		int id = articleService.add(Container.session.getSelectedBoardId(), writer, title, body);
		
		System.out.printf("= %d번 게시물이 등록되었습니다 =\n", id);
		
	}

}
