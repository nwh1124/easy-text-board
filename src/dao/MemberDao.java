package dao;

import java.util.Map;
import com.sbs.example.mysqlutil.MysqlUtil;
import com.sbs.example.mysqlutil.SecSql;
import dto.Member;

public class MemberDao {
	
	public MemberDao() {
		
	}

	public Member getMemberByLoginId(String loginId) {
		
		SecSql sql = new SecSql();
		
		sql.append("SELECT *");
		sql.append("FROM `member`");
		sql.append("WHERE loginId = ?",loginId);
		
		Map<String, Object> map = MysqlUtil.selectRow(sql);
		
		if(map.size() == 0) {
			return null;
		}
		
		Member member = new Member(map);
		
		return member;
		
	}

	public int join(String loginId, String loginPw, String name) {
		
		SecSql sql = new SecSql();
		
		sql.append("INSERT INTO `member`");
		sql.append("SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", loginId = ?", loginId);
		sql.append(", loginPw = ?", loginPw);
		sql.append(", name = ?", name);
		
		int id = MysqlUtil.insert(sql);
		
		return id;		
		
	}

	public Member getMemberById(int loginedId) {
		
		SecSql sql = new SecSql();
		
		sql.append("SELECT *");
		sql.append("FROM `member`");
		sql.append("WHERE id = ?",loginedId);
		
		Map<String, Object> map = MysqlUtil.selectRow(sql);
		
		Member member = new Member(map);
		
		return member;
		
	}

	public String getMemberNameById(int memberId) {

		SecSql sql = new SecSql();
		
		sql.append("SELECT name");
		sql.append("FROM `member`");
		sql.append("WHERE id = ?", memberId);
		
		String name = MysqlUtil.selectRowStringValue(sql);
		
		return name;
		
	}

	public void modify(String modId, String modPw, String modName, int loginedId) {
		
		SecSql sql = new SecSql();
		
		sql.append("UPDATE `member`");
		sql.append("SET loginId = ?", modId);
		sql.append(", loginPw = ?", modPw);
		sql.append(", `name` = ?", modName);
		sql.append("WHERE id = ?", loginedId);
		
		MysqlUtil.update(sql);
		
	}

}
