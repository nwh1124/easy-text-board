package Controller;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import 게시판연습_1.dto.Members;

public class MeemberController extends Controller {
	
	ArrayList<Members> member;
	int lastMembersNum;
	
	public MeemberController(){
		
		member = new ArrayList<Members>();
		lastMembersNum = 0;
		
	}
	
	public void run(Scanner sc, String command) {
				
		if(command.equals("member join")) {
			
			boolean missCheck = false;
			int missCount = 0;
			int missCountMax = 3;
			String loginId = "";
			String loginPass = "";
			String loginName = "";
			
			while(true) {
				if(missCount >= missCountMax) {
					System.out.println("== 입력 횟수 초과 ==");
					return;
				}
				
				System.out.printf("아이디 : ");
				loginId = sc.nextLine().trim();
				
				if(loginId.length() == 0) {
					System.out.println("== 정확히 입력해주세요 ==");
					missCount++;
					continue;
				}
				else if(idEquarlCheck(loginId)) {
					System.out.println("== 이미 존재하는 아이디입니다 ==");
					missCount++;
					continue;
				}
				while(true) {
					System.out.printf("비밀번호 : ");
					loginPass = sc.nextLine().trim();
					if(loginPass.length() > 0) {
						break;
					}
				}
				while(true) {
					System.out.printf("이름 : ");
					loginName = sc.nextLine().trim();
					if(loginName.length() > 0) {
						break;
					}
				}
				SimpleDateFormat form = new SimpleDateFormat("yyyy-MMMM-dddd");
				String time = form.format(new Date());
				lastMembersNum ++;
				member.add(new Members(lastMembersNum, loginId, loginPass, loginName, time));
				System.out.println("== 가입되었습니다 ==");
				break;
			}
		}
		
	}

	boolean idEquarlCheck(String loginId){
		for(int i = 0; i < member.size(); i++) {
			if(member.get(i).id.equals(loginId)) {
				return true;
			}
		}
		
		return false;
	}
}
