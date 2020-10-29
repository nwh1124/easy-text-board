package Controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import 게시판연습_1.dto.Members;

public class MemberController extends Controller{
	
	ArrayList<Members> member = new ArrayList<Members>();	
	ArrayList<Members> nowLogin = new ArrayList<Members>();
	int lastMembersNum = 0;
	
	public	MemberController(){
			nowLogin.add(new Members(0, "","","",""));
		}
	
		public void run(Scanner sc, String command){
					
			if(command.equals("member join")) {			
				join(sc);			
			}else if(command.endsWith("member login")) {			
				login(sc);			
			}else if(command.equals("whoami")) {			
				whoami();			
			}else if(command.endsWith("log out")) {			
				logOut();			
			}		
		}
		
	public void logOut() {
		
		if(nowLogin.get(0).num == 0) {
			System.out.println("== 로그인 중이 아닙니다 ==");
		}
		else {
		nowLogin.set(0, new Members(0, "", "", "",""));
		System.out.println("== 로그아웃 되었습니다 ==");
		return;
		}
		
	}

	public void whoami() {
		
		if(nowLogin.get(0).num == 0) {
			System.out.println("== 로그인 중이 아닙니다 ==");
			return;
		}
		else {
			System.out.println("== 현재 로그인 중인 회원 정보 ==");
			System.out.printf("등록일 : %s / Id %s / Password %s / 이름 %s\n",nowLogin.get(0).time,
					nowLogin.get(0).id,nowLogin.get(0).pass,nowLogin.get(0).name);
		}
	}

	public void login(Scanner sc) {
		
		if(nowLogin.get(0).num != 0) {
			System.out.println("== 이미 로그인 중입니다 ==");
		}else {
		
			System.out.println("== 로그인 ==");
	
			System.out.printf("ID : ");
			String logId = sc.nextLine();
			boolean searchId = true;
			
			for(Members mem : member) {
				if(mem.id.equals(logId)) {
					
					searchId = false;
					System.out.printf("Password : ");
					
					String logPass = sc.nextLine();
					if(mem.pass.equals(logPass)) {
						
						System.out.println("== 로그인 되었습니다 ==");
						nowLogin.set(0, mem);
						return;
						
					}else {
						System.out.println("== 비밀번호가 틀렸습니다 ==");
						return;
					}
				}
			}if(searchId) {
			System.out.println("== ID가 존재하지 않습니다 ==");	
		}
		
		}
		
	}

	public void join(Scanner sc) {

		System.out.println("== 회원 등록 ==");
		

		int loginIdMaxCount = 3;
		int loginIdCount = 0;
		boolean loginIsValid = false;
		String loginId = "";
		
		
		while(true) {
			
			if(loginIdCount >= loginIdMaxCount ) {
				System.out.println("== 가입할 수 없습니다 = 입력 실패 누적 ==");
				break;
			}
			System.out.printf("Login Id : ");
			loginId = sc.nextLine().trim();
			if(loginId.length() == 0) {
				System.out.println("== Login Id를 정확히 입력해주세요 ==");
				loginIdCount++;
				continue;
			}if(isJoinableLoginId(loginId)) {
				System.out.println("== 이미 사용중인 아이디입니다 ==");
				loginIdCount++;
				continue;
			}
			loginIsValid = true;
			break;
		}
		
		int passMaxCount = 3;
		int passCount = 0;
		String password = "";
	
		while(true) {	
			if(loginIdCount >= loginIdMaxCount ) {
			break;
			}			
			if(passCount >= passMaxCount) {
				System.out.println("== 로그인할 수 없습니다 = 입력 실패 누적 ==");
				break;
			}
			
			System.out.printf("Password : ");
			password = sc.nextLine().trim();
			
			if(password.length() == 0) {
				System.out.println("== 비밀번호를 정확히 입력해주세요 ==");
				passCount++;
				continue;
			}
			break;
		}
		
		int nameMaxCount = 3;
		int nameCount = 0;
		String name = "";
		
		while(true) {
			if(loginIdCount >= loginIdMaxCount ) {
				break;
				}
			if(passCount >= passMaxCount ) {
				break;
				}
			if(nameCount >= nameMaxCount) {
				System.out.println("== 로그인할 수 없습니다 = 입력 실패 누적 ==");
				break;
			}
			System.out.printf("Name : ");
			name = sc.nextLine().trim();
			if(name.length() == 0) {
				System.out.println("== 정확히 입력해주세요 ==");
				nameCount++;
				continue;
			}
			break;
		}
		
		if(nameCount < nameMaxCount && passCount < passMaxCount && loginIdCount < loginIdMaxCount) {
			SimpleDateFormat form = new SimpleDateFormat("yyyy-MMMM-dddd");
			String time = form.format(new Date());
			
			member.add(new Members(lastMembersNum, loginId, password, name, time));
			System.out.println("== 가입되었습니다 ==");
		}
		
		
				
	}

	boolean isJoinableLoginId(String loginId) {
		
		boolean idCheck = false;
		for(int i = 0; i < member.size(); i++) {
			if(member.get(i).id.equals(loginId)) {
				idCheck = true;
				break;
			}
		}
		return idCheck;
	}
	
}