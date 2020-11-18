package service;

import dto.Member;
import container.Container;
import dao.MemberDao;

public class MemberService {

	private MemberDao memberDao;
	
	public MemberService () {
		
		memberDao = Container.memberDao;
		
	}

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}

	public int join(String loginId, String loginPw, String name) {
		return memberDao.join(loginId, loginPw, name);		
	}

	public Member getMemberById(int loginedId) {
		return memberDao.getMemberById(loginedId);
	}

	public String getMemberNameById(int memberId) {
		return memberDao.getMemberNameById(memberId);
	}

	public void modify(String modId, String modPw, String modName, int loginedId) {
		memberDao.modify(modId, modPw, modName, loginedId);
	}

}
