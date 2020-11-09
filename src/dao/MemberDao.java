package dao;

import java.util.ArrayList;
import java.util.List;

import dto.Member;

public class MemberDao {
	
	List<Member> members;
	int lastMemberNum;

	public MemberDao() {
		
		members = new ArrayList<>();
		lastMemberNum = 0;
		
	}

	public boolean checkIdById(String loginId) {
		for(Member member : members) {
			if(member.loginId.equals(loginId)) {
				return true;
			}
		}		
		return false;
	}

	public int join(String loginId, String loginPw, String name) {		
		lastMemberNum++;
		members.add(new Member(lastMemberNum, loginId, loginPw, name));
		
		return lastMemberNum;
	}

	public Member getMemberById(String loginId) {
		for(Member member : members) {
			if(member.loginId.equals(loginId)) {
				return member;
			}
		}
		return null;
	}

	public Member getMemberByNum(int nowLoginedId) {
		for(Member member : members) {
			if(member.num == nowLoginedId) {
				return member;
			}
		}
		return null;
	}

	public void modify(int nowId, String modId, String modPw, String modName) {
		Member modMember = new Member();
		
		modMember.num = nowId;
		modMember.loginId = modId;
		modMember.loginPw = modPw;
		modMember.name = modName;
		
		members.set(nowId-1, modMember);		
	}

	public String getMemberNameByNum(int num) {
		for(Member member : members) {
			if(member.num == num) {
				return member.name;
			}
		}
		return null;
	}
	
}
