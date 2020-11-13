package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.Article;

public class ArticleDao {
	
	List<Article> articles;
	
	Connection con;
	ResultSet rs;
	PreparedStatement pstmt;
	
	String jdbcUrl;
	String jdbcLoginId;
	String jdbcLoginPw;
	
	public ArticleDao() {
		
		articles = new ArrayList<>();
		
		con = null;
		rs = null;
		pstmt = null;
		
		jdbcUrl = "jdbc:mysql://127.0.0.1:3306/etb";
		jdbcLoginId = "sbsst";
		jdbcLoginPw = "sbs123414";
		
	}

	public int add(String title, String body, int loginedId) {
		
		try {
			con = DriverManager.getConnection(jdbcUrl, jdbcLoginId, jdbcLoginPw);
			
			String sql = "insert into article set "
					+ "regDate = now(), updateDate = now(), "
					+ "title = ?, body = ?, memberId = ?";
			
			pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, title);
			pstmt.setString(2, body);
			pstmt.setInt(3, loginedId);
			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			rs.next();
			int id = rs.getInt(1);
			
			return id;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public List<Article> getListArticle() {

		try {
			con = DriverManager.getConnection(jdbcUrl, jdbcLoginId, jdbcLoginPw);
			
			String sql = "select * from article";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			List<Article> article = new ArrayList<>();	
			
			while(rs.next()) {
								
				int id = rs.getInt("id");
				String regDate = rs.getString("regDate");
				String updateDate = rs.getString("updateDate");
				String title = rs.getString("title");
				String body = rs.getString("body");
				int memberId = rs.getInt("memberId");
				
				article.add(new Article(id, regDate, updateDate, title, body, memberId));
				
			}
			
			return article;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public Article getArticle(int inputedId) {
		
		try {
			con = DriverManager.getConnection(jdbcUrl, jdbcLoginId, jdbcLoginPw);
			
			String sql = "select * from article where id = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, inputedId);
			
			rs = pstmt.executeQuery();
			
			Article article = new Article();	
			
			while(rs.next()) {
								
				int id = rs.getInt("id");
				String regDate = rs.getString("regDate");
				String updateDate = rs.getString("updateDate");
				String title = rs.getString("title");
				String body = rs.getString("body");
				int memberId = rs.getInt("memberId");
				
				article = new Article(id, regDate, updateDate, title, body, memberId);
				
			}
			
			return article;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public void delete(int inputedId) {

		try {
			con = DriverManager.getConnection(jdbcUrl, jdbcLoginId, jdbcLoginPw);
			
			String sql = "delete from article where id = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, inputedId);			
			pstmt.executeUpdate();			
						
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
