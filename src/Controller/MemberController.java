package controller;

import java.util.Scanner;

import container.Container;
import dto.Member;
import service.MemberService;

public class MemberController {

	Scanner sc;
	private MemberService memberService;
	
	public MemberController() {
		
		sc = Container.sc;
		memberService = Container.memberService;
		
	}
	
	public void doCommand(String cmd) {
		
		if(cmd.equals("member join")) {
			join();
		}else if(cmd.equals("member login")) {
			login();
		}else if(cmd.equals("member logout")) {
			logout();
		}else if(cmd.equals("member whoami")) {
			whoami();
		}
		
	}

	private void whoami() {
		if(Container.session.getLogined() == false) {
			System.out.println("= 비회원입니다 =");
			return;
		}
		
		System.out.println("= 회원 상세 정보 =");
				
		Member whoamiMember = memberService.getMemberById(Container.session.getLoginedId());
		
		System.out.printf("회원 번호 : %d\n회원 아이디 : %s\n회원 이름 : %s\n", whoamiMember.id, whoamiMember.loginId, whoamiMember.name);
		
	}

	private void logout() {
		if(Container.session.getLogined() == false) {
			System.out.println("= 로그인 후 이용해주세요 =");
			return;
		}
		
		Container.session.logout();
		
		System.out.println("= 로그아웃 되었습니다 =");
		
	}

	private void login() {
		if(Container.session.getLogined()) {
			System.out.println("= 로그인 중입니다 =");
			return;
		}
		
		int missCountMax = 3;
		String loginId;
		String loginPw;
		Member loginMember;
			
		System.out.println("== 로그인 ==");
		
		int missCount = 0;
		while( true ) { 
			if(missCount >= missCountMax) {
				System.out.println("= 취소 =");
				return;
			}
			
			System.out.printf("로그인 아이디 : ");
			loginId = sc.nextLine();
			
			loginMember = memberService.getMemberByLoginId(loginId);
			
			if(loginId.trim().length() == 0) {
				System.out.println("= 아이디를 입력해주세요 =");
				missCount++;
				continue;
			}else if(loginMember == null) {
				System.out.println("= 존재하지 않는 아이디입니다 =");
				missCount++;
				continue;
			}else {
				break;	
			}
		}
		
		missCount = 0;
		while( true ) { 
			if(missCount >= missCountMax) {
				System.out.println("= 취소 =");
				return;
			}
			
			System.out.printf("로그인 비밀번호 : ");
			loginPw = sc.nextLine();
			
			if(loginPw.trim().length() == 0) {
				System.out.println("= 비밀번호를 입력해주세요 =");
				missCount++;
				continue;
			}else if(loginMember.loginPw.equals(loginPw) == false){
				System.out.println("= 비밀번호가 일치하지 않습니다 =");
				missCount++;
				continue;
			}else {
				break;	
			}
		}
		
		Container.session.login(loginMember.id);
		
		System.out.printf("= %s 회원님 환영합니다! =\n", loginMember.name);
		
	}

	private void join() {
		if(Container.session.getLogined()) {
			System.out.println("= 로그아웃 후 이용해주세요 =");
			return;
		}
		
		int missCountMax = 3;
		String loginId;
		String loginPw;
		String name;
		Member loginMember;
			
		System.out.println("= 회원 가입 =");
		
		int missCount = 0;
		while( true ) { 
			if(missCount >= missCountMax) {
				System.out.println("= 취소 =");
				return;
			}
			
			System.out.printf("로그인 아이디 : ");
			loginId = sc.nextLine();
			
			loginMember = memberService.getMemberByLoginId(loginId);
			
			if(loginId.trim().length() == 0) {
				System.out.println("= 아이디를 입력해주세요 =");
				missCount++;
				continue;
			}else if(loginMember != null) {
				System.out.println("= 이미 존재하는 아이디입니다 =");
				missCount++;
				continue;
			}else {
				break;	
			}
		}
		
		missCount = 0;
		while( true ) { 
			if(missCount >= missCountMax) {
				System.out.println("= 취소 =");
				return;
			}
			
			System.out.printf("로그인 비밀번호 : ");
			loginPw = sc.nextLine();
			
			if(loginPw.trim().length() == 0) {
				System.out.println("= 비밀번호를 입력해주세요 =");
				missCount++;
				continue;
			}else {
				break;	
			}
		}
		
		missCount = 0;
		while( true ) { 
			if(missCount >= missCountMax) {
				System.out.println("= 취소 =");
				return;
			}
			
			System.out.printf("이름 : ");
			name = sc.nextLine();
			
			if(name.trim().length() == 0) {
				System.out.println("= 비밀번호를 입력해주세요 =");
				missCount++;
				continue;
			}else {
				break;	
			}
		}
		
		int id = memberService.join(loginId, loginPw, name);
		System.out.printf("= %d번 회원으로 가입되었습니다 =\n", id);
		
	}
	
}
