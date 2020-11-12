package dao;

import dao.ArticleDao;
import dto.Article;
import Container.Container;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Connect {
	
	private ArticleDao articleDao;
	
	Connection con;
	ResultSet rs;
	Statement qu;
	
	
	public ArrayList<Article> connent() {
		
		articleDao = Container.articleDao;
		
	    try{ 	
	    	
	    	int id = 0;
	    	String regDate = "";
	    	String writer = "";
	    	String title = "";
	    	String body = "";
	    	int hit = 0;	    	
	    	
	    	Class.forName("org.mariadb.jdbc.Driver");
	    	
	    	con = DriverManager.getConnection(
	    			"jdbc:mariadb://127.0.0.1:3306/a2", 
	    			"sbsst", 
	    			"sbs123414");
	    	
	    	if(con != null) {
	    		System.out.println("연동 성공");
	    	}
	    	
	    	qu = con.createStatement();
	    	
	    	rs = qu.executeQuery(
	    			"select * from article"
	    			);
	    	
	    	ArrayList<Article> existsArticle = new ArrayList<>();    	
	    	
	    	while(rs.next()) {
	    		
	    		id = rs.getInt("id");
	    		regDate = rs.getString("regDate");
	    		writer = rs.getString("nickname");
	    		title = rs.getString("title");
	    		body = rs.getString("body");
	    		hit = rs.getInt("hit");	    		
	    		    		
	    		existsArticle.add(new Article(1, id, 1, regDate, writer, title, body, hit));
	    		
	    	}
	    	
	    	return existsArticle;
	    		
	    }
	    catch(ClassNotFoundException e){
	        System.out.println("드라이버 로딩 실패");
	    }
	    catch(SQLException e){
	        System.out.println("에러: " + e);	        	        
	    }
		return null;        
	}

	public void add(ArrayList<Article> articles, String writer, String title, String body) {
		
		 try{ 	
		    	
		    	Class.forName("org.mariadb.jdbc.Driver");
		    	
		    	con = DriverManager.getConnection(
		    			"jdbc:mariadb://127.0.0.1:3306/a2", 
		    			"sbsst", 
		    			"sbs123414");
		    	
		    	if(con != null) {
		    		System.out.println("연동 성공");
		    	}
		    	
		    	qu = con.createStatement();
		    	
		    	rs = qu.executeQuery(
		    			"insert into article set "
		    			+ "regDate = now(),"
		    			+ "nickname = '"+writer+"', "
		    			+ "title = '"+title+"', "
		    			+ "`body` = '"+body+"', "
		    			+ "hit = 9"
		    			); 		    	

		    	con.close();
		    	qu.close();
		    	rs.close();
		    	
		    	return;
		    		
		    }
		    catch(ClassNotFoundException e){
		        System.out.println("드라이버 로딩 실패");
		    }
		    catch(SQLException e){
		        System.out.println("에러: " + e);	        	        
		    }
		
	}

	public void delete(int inputedId) {
		 try{ 	
		    	
		    	Class.forName("org.mariadb.jdbc.Driver");
		    	
		    	con = DriverManager.getConnection(
		    			"jdbc:mariadb://127.0.0.1:3306/a2", 
		    			"sbsst", 
		    			"sbs123414");
		    	
		    	if(con != null) {
		    		System.out.println("연동 성공");
		    	}
		    	
		    	qu = con.createStatement();
		    	
		    	rs = qu.executeQuery(
		    			"delete from article where id = "+inputedId
		    			);
		    	
		    	con.close();
		    	qu.close();
		    	rs.close();
		    	
		    	return;
		    		
		    }
		    catch(ClassNotFoundException e){
		        System.out.println("드라이버 로딩 실패");
		    }
		    catch(SQLException e){
		        System.out.println("에러: " + e);	        	        
		    }
		
	}

	public void modify(int inputedId, String modTitle, String modBody) {

		try{ 	
	    	
	    	Class.forName("org.mariadb.jdbc.Driver");
	    	
	    	con = DriverManager.getConnection(
	    			"jdbc:mariadb://127.0.0.1:3306/a2", 
	    			"sbsst", 
	    			"sbs123414");
	    	
	    	if(con != null) {
	    		System.out.println("연동 성공");
	    	}
	    	
	    	qu = con.createStatement();
	    	
	    	rs = qu.executeQuery(
	    			"update article set "
	    			+"title = '"+modTitle+"', "
	    			+"`body` = '"+modBody+"' "
	    			+"where id = "+inputedId
	    			);
	    	
	    	con.close();
	    	qu.close();
	    	rs.close();
	    	
	    	return;
	    		
	    }
	    catch(ClassNotFoundException e){
	        System.out.println("드라이버 로딩 실패");
	    }
	    catch(SQLException e){
	        System.out.println("에러: " + e);	        	        
	    }
	
		
	}
}
