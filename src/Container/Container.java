package Container;

import java.util.Scanner;

import Controller.ArticleController;
import Controller.MemberController;
import Service.ArticleService;
import Service.MemberService;
import Session.Session;
import dao.ArticleDao;
import dao.MemberDao;
import dto.Board;

public class Container {

	public static Scanner sc;
	public static Session session;
	
	public static ArticleDao articleDao;
	public static MemberDao memberDao;
	
	public static MemberService memberService;
	public static ArticleService articleService;
	
	public static MemberController memberController;
	public static ArticleController articleController;
	
	public static Board board;
	
	
	static {
		
		sc = new Scanner(System.in);
		session = new Session();
		
		articleDao = new ArticleDao();
		memberDao = new MemberDao();
		
		articleService = new ArticleService();
		memberService = new MemberService();
		
		articleController = new ArticleController();
		memberController = new MemberController();
		
		board = new Board();
				
	}
	
}
