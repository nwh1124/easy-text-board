package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sbs.example.mysqlutil.MysqlUtil;
import com.sbs.example.mysqlutil.SecSql;

import dto.Article;

public class ArticleDao {
	
	public ArticleDao() {
		
	}

	public int add(String title, String body, int loginedId, int selectedBoardId) {
		
		SecSql sql = new SecSql();
		
		sql.append("INSERT INTO article");
		sql.append("SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", title = ?", title);
		sql.append(", `body` = ?", body);
		sql.append(", hit = 10");
		sql.append(", memberId = ?", loginedId);
		sql.append(", boardID = ?", selectedBoardId);
		
		int id = MysqlUtil.insert(sql);
		
		return id;
	
	}

	public List<Article> getListArticle() {
		
		SecSql sql = new SecSql();
		
		sql.append("SELECT *");
		sql.append("FROM article");
		
		List<Map<String, Object>> articleListMap = MysqlUtil.selectRows(sql);
		
		List<Article> articles = new ArrayList<>();
		
		for(Map<String, Object> map : articleListMap) {
			articles.add(new Article(map));
		}
		
		return articles;

	}

	public Article getArticle(int inputedId) {
		
		SecSql sql = new SecSql();
		
		sql.append("SELECT *");
		sql.append("FROM article");
		sql.append("WHERE id = ?", inputedId);
		
		Map<String, Object> map = MysqlUtil.selectRow(sql);
		
		Article article = new Article(map);
		
		return article;
		
	}

	public void delete(int inputedId) {
		
		SecSql sql = new SecSql();
		
		sql.append("DELETE FROM article");
		sql.append("WHERE id = ?",inputedId);
		
	}

	public int getArticleWriterId(int inputedId) {
		
		SecSql sql = new SecSql();
		
		sql.append("SELECT name");
		sql.append("FROM article");	
		sql.append("WHERE id = ?", inputedId);
		
		int id = MysqlUtil.selectRowIntValue(sql);
		
		return id;
	}

	public String getBoardNameById(int boardId) {
		
		SecSql sql = new SecSql();
		
		sql.append("SELECT name");
		sql.append("FROM board");
		sql.append("WHERE id = ?", boardId);
		
		String name = MysqlUtil.selectRowStringValue(sql);
		
		return name;
	}

}
