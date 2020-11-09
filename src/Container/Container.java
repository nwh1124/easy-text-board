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
	
	public static MemberDao memberDao;
	public static MemberService memberService;
	public static MemberController memberController;
	
	public static ArticleDao articleDao;
	public static ArticleService articleService;
	public static ArticleController articleController;
	
	public static Board board;
	
	
	static {
		
		sc = new Scanner(System.in);
		session = new Session();
		
		memberDao = new MemberDao();
		memberService = new MemberService();
		memberController = new MemberController();
		
		articleDao = new ArticleDao();
		articleService = new ArticleService();
		articleController = new ArticleController();
		
		board = new Board();
				
	}
	
}
