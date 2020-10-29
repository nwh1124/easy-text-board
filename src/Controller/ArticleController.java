package Controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import 게시판연습_1.dto.Article;

public class ArticleController extends Controller {

	ArrayList<Article> articles;
	int lastArticleId;
	
	public ArticleController() {
		articles = new ArrayList<Article>();
		lastArticleId = 0;
		for (int i = 0; i < 32; i++) {
			lastArticleId++;
			articles.add(new Article(lastArticleId, "제목"+(i+1), "내용"+(i+1)));
		}
	}
	
	public void run(Scanner sc, String command){
		
		if(command.equals("article add")) {
			add(sc, command);			
		}
		else if(command.startsWith("article list")) {			
			list(command);			
		}
		else if(command.startsWith("article detail ")) {			
			detail(command);			
		}
		else if(command.startsWith("article delete ")) {			
			delete(command);			
		}
		else if(command.startsWith("article modify ")) {			
			modify(sc, command);			
		}else if(command.startsWith("article search ")) {			
			search(command);			
		}
	}
	

	public  void search(String command) {
		System.out.println("== 게시물 검색 ==");
		
		String[] commandBits = command.split(" ");
		String selectedStr = commandBits[2];
		ArrayList<Article> searchArticle = new ArrayList<Article>();
		
		for(int i = 0; i < articles.size(); i++) {
			if (articles.get(i).title.contains(selectedStr) || articles.get(i).body.contains(selectedStr)){
				searchArticle.add(new Article (articles.get(i).id,articles.get(i).title,articles.get(i).body));
			}
		}
		
		int pageSize = 10;
		int pagePoint = searchArticle.size() / pageSize;
		
		if(commandBits.length >= 1 && commandBits.length < 4) {
			int startPoint = searchArticle.size()-1;
			int endPoint = startPoint - pageSize;
			
			for (int i = startPoint; i > endPoint; i--) {
				System.out.printf("번호 : %d / 제목 : %s\n", searchArticle.get(i).id,searchArticle.get(i).title);					}
		}
		else if(commandBits.length >= 3) {
			
			int selectedPage = 0;
			try{
				selectedPage = Integer.parseInt(commandBits[3]);
			}catch(NumberFormatException e) {
				System.out.println("== 페이지 숫자를 양의 정수로 입력해주세요 ==");
				return;
			}
			
			int startPoint = (searchArticle.size()-1) - 10*(selectedPage-1);
			int endPoint = startPoint - pageSize;
			if( selectedPage > 0 && selectedPage <= pagePoint) {
				for (int i = startPoint; i > endPoint; i--) {
					System.out.printf("번호 : %d / 제목 : %s\n", searchArticle.get(i).id,searchArticle.get(i).title);
				}
			}else if(selectedPage == pagePoint+1) {
				for (int i = startPoint; i >= 0; i--) {
					System.out.printf("번호 : %d / 제목 : %s\n", searchArticle.get(i).id,searchArticle.get(i).title);				}
			}else {
				System.out.println("== 페이지 번호 지정이 잘못되었습니다 ==");
			}
		}
		
	}

	public  void modify(Scanner sc, String command) {
		
		String[] commandBits = command.split(" ");
		int selectedNum = 0;
		try{
			selectedNum = Integer.parseInt(commandBits[2]);
		}catch(NumberFormatException e) {
			System.out.println("== 페이지 숫자를 양의 정수로 입력해주세요 ==");
			return;
		}
		
		if(selectedNum > articles.size() || selectedNum <= 0) {
			System.out.println("== 게시물을 찾을 수 없습니다 ==");
		}else {
			System.out.printf("수정된 제목 : ");
			String title = sc.nextLine();
			System.out.printf("수정된 내용 : ");
			String body = sc.nextLine();
			
			articles.set(selectedNum-1, new Article(selectedNum, title, body));
			System.out.printf("= %d번 게시물이 수정되었습니다 =\n", selectedNum);
		}
		
	}

	public  void delete(String command) {
		
		String[] commandBits = command.split(" ");
		int selectedNum = 0;
		try{
			selectedNum = Integer.parseInt(commandBits[2]);
		}catch(NumberFormatException e) {
			System.out.println("== 페이지 숫자를 양의 정수로 입력해주세요 ==");
			return;
		}
		
		if(selectedNum > articles.size() || selectedNum <= 0) {
			System.out.println("== 게시물을 찾을 수 없습니다 ==");
		}else {
			articles.remove(selectedNum-1);
			System.out.printf("= %d번 게시물이 삭제되었습니다 =\n", selectedNum);
		}
		
	}

	public  void detail(String command) {
		
		String[] commandBits = command.split(" ");
		
		int selectedNum = 0;
		try{
			selectedNum = Integer.parseInt(commandBits[2]);
		}catch(NumberFormatException e) {
			System.out.println("== 게시물 번호를 양의 정수로 입력해주세요 ==");
			return;
		}		
		
		if(selectedNum > articles.size() || selectedNum <= 0) {
			System.out.println("== 게시물을 찾을 수 없습니다 ==");
		}else {
			System.out.printf("= 게시물 상세 =\n");
			System.out.printf("날짜 : %s / 번호 : %d / 제목 : %s / 내용 : %s\n", articles.get(selectedNum-1).time,
					articles.get(selectedNum-1).id,articles.get(selectedNum-1).title,articles.get(selectedNum-1).body);
		}
		
	}

	public void list(String command) {

		String[] commandBits = command.split(" ");
		int pageSize = 10;
		int pagePoint = articles.size() / pageSize;
		
		if(commandBits.length >= 1 && commandBits.length < 3) {
			int startPoint = articles.size()-1;
			int endPoint = startPoint - pageSize;
			
			for (int i = startPoint; i > endPoint; i--) {
				System.out.printf("번호 : %d / 제목 : %s / 내용 : %s\n", articles.get(i).id,articles.get(i).title,articles.get(i).body);
			}
		}
		else if(commandBits.length >= 3) {
			int selectedPage = 0;
			try{
				selectedPage = Integer.parseInt(commandBits[2]);
			}catch(NumberFormatException e) {
				System.out.println("== 페이지 숫자를 양의 정수로 입력해주세요 ==");
				return;
			}
			int startPoint = (articles.size()-1) - 10*(selectedPage-1);
			int endPoint = startPoint - pageSize;
			if( selectedPage > 0 && selectedPage <= pagePoint) {
				for (int i = startPoint; i > endPoint; i--) {
					System.out.printf("번호 : %d / 제목 : %s\n", articles.get(i).id,articles.get(i).title);
				}
			}else if(selectedPage == pagePoint+1) {
				for (int i = startPoint; i >= 0; i--) {
					System.out.printf("번호 : %d / 제목 : %s\n", articles.get(i).id,articles.get(i).title);				}
			}else {
				System.out.println("== 페이지 번호 지정이 잘못되었습니다 ==");
			}
		}
		
	}

	public void add(Scanner sc, String command) {

		System.out.println("== 게시물 등록 ==");

		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();
		lastArticleId++;
		SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd hh:mm:ss");
		String time = format.format(new Date());
		
		articles.add(new Article(lastArticleId, title, body));
		System.out.printf("= 등록되었습니다 =\n");
		for(Article article : articles) {
			System.out.printf("%d %s %s\n",article.id,article.title,article.body);
		}
		
	
	}
}
