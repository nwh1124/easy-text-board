package Service;

import Container.Container;
import dao.MemberDao;
import dto.Member;

public class MemberService {

	private MemberDao memberDao;

	public MemberService() {
		
		memberDao = Container.memberDao;
		
	}

	public boolean checkIdById(String loginId) {
		return memberDao.checkIdById(loginId);
	}

	public int join(String loginId, String loginPw, String name) {
		return memberDao.join(loginId, loginPw, name);
	}

	public Member getMemberById(String loginId) {
		return memberDao.getMemberById(loginId);
	}

	public Member getMemberByNum(int nowLoginedId) {
		return memberDao.getMemberByNum(nowLoginedId);
	}

	public void modify(int nowId, String modId, String modPw, String modName) {
		memberDao.modify(nowId, modId, modPw, modName);
	}

	public String getMemberNameByNum(int num) {
		return memberDao.getMemberNameByNum(num);
	}
	
	
	
}
