package container;

import java.util.Scanner;

import controller.ArticleController;
import controller.MemberController;
import dao.ArticleDao;
import dao.MemberDao;
import service.ArticleService;
import service.MemberService;
import session.Session;

public class Container {

	public static Scanner sc;
	public static Session session;
	
	public static MemberDao memberDao;
	public static ArticleDao articleDao;
	
	public static MemberService memberService;
	public static ArticleService articleService;
	
	public static MemberController memberController;
	public static ArticleController articleController;
	
	
	
	
	static {
		
		sc = new Scanner(System.in);
		session = new Session();
		
		memberDao = new MemberDao(); 
		articleDao = new ArticleDao();
		
		memberService = new MemberService();
		articleService = new ArticleService();
		
		memberController = new MemberController();
		articleController = new ArticleController();
	}
	
}
