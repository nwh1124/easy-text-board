package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.Member;

public class MemberDao {
	
	List<Member> members;
	
	Connection con;
	ResultSet rs;
	PreparedStatement pstmt;
	
	String jdbcUrl;
	String jdbcLoginId;
	String jdbcLoginPw;
	
	public MemberDao() {
		
		members = new ArrayList<>(); 
		
		con = null;
		rs = null;
		pstmt = null;
		
		jdbcUrl = "jdbc:mysql://127.0.0.1:3306/etb";
		jdbcLoginId = "sbsst";
		jdbcLoginPw = "sbs123414";
		
	}

	public Member getMemberByLoginId(String loginId) {
		
		try {			
			con = DriverManager.getConnection(jdbcUrl, jdbcLoginId, jdbcLoginPw);
			
			String sql = "select * from `member` where loginid = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, loginId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				int id = rs.getInt("id");
				String memberLoginId = rs.getString("loginId");
				String loginPw = rs.getString("loginPw");
				String name = rs.getString("name");
				
				Member member = new Member(id, memberLoginId, loginPw, name);
				
				return member;
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public int join(String loginId, String loginPw, String name) {
				
		try {
			con = DriverManager.getConnection(jdbcUrl, jdbcLoginId, jdbcLoginPw);
			
			String sql = "insert into `member` set "
					+ "loginId = ?, loginPw = ?, name = ?;";
			
			pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, loginId);
			pstmt.setString(2, loginPw);
			pstmt.setString(3, name);
			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			rs.next();
			int id = rs.getInt(1);
			
			return id;			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return 0;
	}

	public Member getMemberById(int loginedId) {

		try {
			con = DriverManager.getConnection(jdbcUrl, jdbcLoginId, jdbcLoginPw);
			
			String sql = "select * from `member` where id = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, loginedId);
			
			rs = pstmt.executeQuery();
			rs.next();
			
			int id = rs.getInt("id");
			String memberLoginId = rs.getString("loginId");
			String loginPw = rs.getString("loginPw");
			String name = rs.getString("name");
			
			Member member = new Member(id, memberLoginId, loginPw, name);
			
			return member;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public String getMemberNameById(int memberId) {

		try {
			con = DriverManager.getConnection(jdbcUrl, jdbcLoginId, jdbcLoginPw);
			
			String sql = "select * from `member` where id = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, memberId);
			
			rs = pstmt.executeQuery();
			
			rs.next();			
			String name = rs.getString("name");
			return name;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
