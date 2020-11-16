package main;

import java.util.Scanner;

import com.sbs.example.mysqlutil.MysqlUtil;
import com.sbs.example.mysqlutil.SecSql;

import container.Container;
import controller.ArticleController;
import controller.MemberController;

public class App {

	Scanner sc;
	
	private MemberController memberController;
	private ArticleController articleController;
	
	MysqlUtil mutil;
	SecSql sql;
	
	public App() {
		
		sc = Container.sc;
		memberController = Container.memberController;
		articleController = Container.articleController;
	}
	
	public void run() {
				
		while( true ) {
			
			System.out.printf("명령어 입력 ) ");
			String cmd = sc.nextLine();
			
			mutil.setDBInfo("localhost", "sbsst", "sbs123414", "textBoard");
			
			if(cmd.equals("test")) {
				 
				sql.append(" select * from article ");
				
			}
			
			if(cmd.equals("system exit")) {
				System.out.println("= 종료 =");
				break;
			}else if(cmd.startsWith("member")) {
				memberController.doCommand(cmd);
			}else if(cmd.startsWith("article")) {
				articleController.doCommand(cmd);
			}
			
		}
		
	}
	
}
