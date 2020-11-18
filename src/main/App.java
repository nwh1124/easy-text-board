package main;

import java.util.Scanner;

import com.sbs.example.mysqlutil.MysqlUtil;
import container.Container;
import controller.ArticleController;
import controller.Controller;
import controller.MemberController;

public class App {

	Scanner sc;	
	private MemberController memberController;
	private ArticleController articleController;
	private boolean cmdToExit;
	
	public App() {
		
		sc = Container.sc;
		memberController = Container.memberController;
		articleController = Container.articleController;
		
		cmdToExit = false;
	}
	
	public void run() {
				
		while( true ) {
			
			System.out.printf("명령어 입력 ) ");
			String cmd = sc.nextLine();
			
			MysqlUtil.setDBInfo("localhost", "sbsst", "sbs123414", "textBoard");
						
			if(cmd.equals("system exit")) {
				System.out.println("= 종료 =");
				cmdToExit = true;
			}
			
			Controller controller = getControllerByCmd(cmd);				
			if(controller != null) {
				controller.doCommand(cmd);
				MysqlUtil.closeConnection();
			}
			
			if(cmdToExit) {
				MysqlUtil.closeConnection();
				break;
			}
			
		}
		
	}

	private Controller getControllerByCmd(String cmd) {
		if(cmd.startsWith("member")) {
			return memberController;
		}else if(cmd.startsWith("article")) {
			return articleController;
		}
		return null;
	}
	
}
