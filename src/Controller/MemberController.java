package Controller;

import java.util.Scanner;

import Container.Container;
import Service.MemberService;
import dto.Member;

public class MemberController extends Controller {
	
	Container container;
	private MemberService memberService;
	Scanner sc;
	
	public MemberController() {
		
		memberService = Container.memberService;
		sc = Container.sc;
		
	}
	
	public void doCommand(String cmd) {
		
		if(cmd.equals("member join")) {
			join();
		}else if(cmd.equals("member login")) {
			login();
		}else if(cmd.equals("member whoami")) {
			whoami();
		}else if(cmd.equals("member logout")) {
			logout();
		}else if(cmd.equals("member modify")) {
			modify();
		}else {
			System.out.println("= 잘못된 회원 명령어 입력 =");
		}
		
	}

	private void modify() {
		
		if(Container.session.getNowLogined() == false) {
			System.out.println("= 로그인 후 이용해주세요 =");
			return;
		}
		
		System.out.println("= 회원 정보 수정 =");

		System.out.printf("수정할 아이디 : ");
		String modId = Container.sc.nextLine();		
		System.out.printf("수정할 비밀번호 : ");
		String modPw = Container.sc.nextLine();		
		System.out.printf("수정할 이름 : ");
		String modName = Container.sc.nextLine();
		
		int nowId = Container.session.getNowLoginedId();
		memberService.modify(nowId, modId, modPw, modName);
		
	}

	private void logout() {
		
		Container.session.logout();
		System.out.println("= 로그아웃 되었습니다 =");
		
	}

	private void whoami() {
		
		if(Container.session.getNowLogined() == false) {
			System.out.println("= 비회원입니다 =");
			return;
		}
		
		System.out.println("= 현재 회원 정보 =");
		
		Member whoamiMember = memberService.getMemberByNum(Container.session.getNowLoginedId());
		
		System.out.printf("아이디 : %s\n이름 : %s\n", whoamiMember.loginId, whoamiMember.name);
	}

	private void login() {
		if(Container.session.getNowLogined()) {
			System.out.println("= 이미 로그인 중입니다 =");
			return;
		}
		
		int missCount;
		int missCountMax;
		String loginId;
		String loginPw;
		
		missCountMax = 3;
		missCount = 0;
		
		System.out.println("= 회원 로그인 =");
		
		while(true) {
			if(missCount >= missCountMax) {
				System.out.println("= 로그인 취소 =");
				return;
			}
			
			System.out.printf("로그인 아이디 : ");
			loginId = sc.nextLine();
			
			if(loginId.trim().length() == 0) {
				System.out.println("= 아이디를 정확히 입력해주세요 =");
				missCount++;
				continue;
			}else if(memberService.checkIdById(loginId) == false) {
				System.out.println("= 존재하지 않는 아이디입니다 =");
				missCount++;
				continue;				
			}else {				
				break;				
			}				
		}
		
		missCount = 0;
		Member loginMember = memberService.getMemberById(loginId);
		
		while(true) {
			if(missCount >= missCountMax) {
				System.out.println("= 로그인 취소 =");
				return;
			}
			
			System.out.printf("로그인 비밀번호 : ");
			loginPw = sc.nextLine();		
			
			if(loginMember.loginPw.equals(loginPw) == false) {
				System.out.println("= 비밀번호가 일치하지 않습니다 =");
				missCount++;
				continue;
			}else {				
				break;				
			}				
		}		
		
		Container.session.login(loginMember.num);
		System.out.printf("= %d번 회원님, 환영합니다! =\n", loginMember.num);
		
	}

	private void join() {
		
		System.out.println("= 회원 가입 =");
		
		int missCount = 0;
		int missCountMax = 3;
		String loginId;
		String loginPw;
		String name;
		
		while(true) {
			if(missCount >= missCountMax) {
				System.out.println("= 회원 가입 취소 =");
				return;
			}
			
			System.out.printf("로그인 아이디 : ");
			loginId = sc.nextLine();
			
			if(loginId.trim().length() == 0) {
				System.out.println("= 아이디를 정확히 입력해주세요 =");
				missCount++;
				continue;
			}else if(memberService.checkIdById(loginId)) {
				System.out.println("= 이미 존재하는 아이디입니다 =");
				missCount++;
				continue;
				
			}else {				
				break;				
			}				
		}
		
		missCount = 0;
		
		while(true) {
			if(missCount >= missCountMax) {
				System.out.println("= 회원 가입 취소 =");
				return;
			}
			
			System.out.printf("로그인 비밀번호 : ");
			loginPw = sc.nextLine();
			
			if(loginPw.trim().length() == 0) {
				System.out.println("= 비밀번호를 정확히 입력해주세요 =");
				missCount++;
				continue;
			}else {				
				break;				
			}				
		}
		
		missCount = 0;
		
		while(true) {
			if(missCount >= missCountMax) {
				System.out.println("= 회원 가입 취소 =");
				return;
			}
			
			System.out.printf("이름 : ");
			name = sc.nextLine();
			
			if(name.trim().length() == 0) {
				System.out.println("= 정확히 입력해주세요 =");
				missCount++;
				continue;
			}else {				
				break;				
			}				
		}
		
		int idNum = memberService.join(loginId, loginPw, name);
		
		System.out.printf("= %d번 회원으로 가입되었습니다 =\n", idNum);
		
	}
	
}
