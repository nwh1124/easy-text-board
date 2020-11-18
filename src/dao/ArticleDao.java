package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sbs.example.mysqlutil.MysqlUtil;
import com.sbs.example.mysqlutil.SecSql;

import dto.Article;
import dto.Board;
import dto.Reply;

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
		
		sql.append("SELECT article.*, `member`.name AS extra__writer, board.name AS extra__boardName");
		sql.append("FROM article");
		sql.append("Inner Join `member`, board");
		sql.append("WHERE article.memberId = `member`.id AND article.boardId = board.id");
		
		List<Map<String, Object>> articleListMap = MysqlUtil.selectRows(sql);
		
		List<Article> articles = new ArrayList<>();
		
		for(Map<String, Object> map : articleListMap) {
			articles.add(new Article(map));
		}
		
		return articles;

	}

	public List<Article> getListArticle(int selectedBoardId) {
		
		SecSql sql = new SecSql();
		
		sql.append("SELECT * ");
		sql.append("FROM article ");
		sql.append("WHERE boardId = ?", selectedBoardId);
		
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
		
		if(map.size() == 0) {
			return null;
		}
		
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
		
		sql.append("SELECT memberId");
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

	public void modify(String modTitle, String modBody, int inputedId) {

		SecSql sql = new SecSql();
		
		sql.append("UPDATE article");
		sql.append("SET updateDate = NOW()");
		sql.append(", title = ?", modTitle);
		sql.append(", `body` = ?", modBody);
		sql.append("WHERE id = ?", inputedId);
		
		MysqlUtil.update(sql);
		
	}
	
	public List<Article> getSearchArticles(String searchWord) {
		
		searchWord = "%"+searchWord+"%";
		
		SecSql sql = new SecSql();
		
		sql.append("SELECT *");
		sql.append("FROM article");
		sql.append("WHERE title like ?", searchWord);
		sql.append("OR body like ?", searchWord);
		
		List<Map<String, Object>> articleListMap = MysqlUtil.selectRows(sql);
		
		List<Article> articles = new ArrayList<>();
		
		for(Map<String, Object> article : articleListMap) {
			articles.add(new Article(article));
		}
		
		return articles;
	}

	public void writeReply(String body, int loginedId, int inputedId) {

		SecSql sql = new SecSql();
		
		sql.append("INSERT INTO reply ");
		sql.append("SET regDate = NOW() ");
		sql.append(", `body` = ?", body);
		sql.append(", articleId = ?", inputedId);
		sql.append(", memberId = ?", loginedId);
		
		MysqlUtil.update(sql);
		
	}

	public List<Reply> getReplysByArticleId(int inputedId) {

		SecSql sql = new SecSql();
		
		sql.append("SELECT * ");
		sql.append("FROM reply");
		sql.append("WHERE articleId = ?", inputedId);
		
		List<Map<String, Object>> replyListMap = MysqlUtil.selectRows(sql);
		
		List<Reply> replys = new ArrayList<>();
		
		for(Map<String, Object> map : replyListMap) {
			replys.add(new Reply(map));
		}		
		
		if(replys.size() == 0)	return null;
		
		return replys;
	}

	public int makeBoard(String boardName) {
		
		SecSql sql = new SecSql();
		
		sql.append("INSERT INTO board ");
		sql.append("SET regDate = NOW() ");
		sql.append(", updateDate = NOW()");
		sql.append(", name = ?", boardName);
		
		int id = MysqlUtil.insert(sql);
		
		return id;
		
	}

	public Board getBoardById(int inputedId) {

		SecSql sql = new SecSql();
		
		sql.append("SELECT * ");
		sql.append("FROM board ");
		sql.append("WHERE id = ?", inputedId);
		
		Map<String, Object> map = MysqlUtil.selectRow(sql);
		
		if(map.size() == 0) {
			return null;
		}
		
		Board board = new Board(map);
		
		return board;
	}

	public List<Reply> getReplysByMemberId(int loginedId) {

		SecSql sql = new SecSql();
		
		sql.append("SELECT reply.*, article.title AS extra__title");
		sql.append("FROM reply ");
		sql.append("INNER JOIN article");
		sql.append("ON reply.articleId = article.id");
		sql.append("WHERE reply.memberId = ?", loginedId);
		
		List<Map<String, Object>> replysListMap = MysqlUtil.selectRows(sql);
		
		if(replysListMap.size() == 0) {
			return null;
		}
		
		List<Reply> replys = new ArrayList<>();
		
		for(Map<String, Object> map : replysListMap) {
			replys.add(new Reply(map));
		}
		
		return replys;
	}

	public Reply getReplysById(int modCmd) {
		
		SecSql sql = new SecSql();
		
		sql.append("SELECT * ");
		sql.append("FROM reply ");
		sql.append("WHERE id = ?", modCmd);
		
		Map<String, Object> map = MysqlUtil.selectRow(sql);
		
		if(map == null) {
			return null;
		}
		
		Reply reply = new Reply(map);
		
		return reply;
	}

	public void modReply(int modRepCmd, String modRepBody) {
		
		SecSql sql = new SecSql();
		
		sql.append("UPDATE reply");
		sql.append("SET `body` = ?", modRepBody);
		sql.append("WHERE id = ?", modRepCmd);
		
		MysqlUtil.update(sql);
		
	}

	public void delReply(int modRepCmd) {

		SecSql sql = new SecSql();
		
		sql.append("DELETE FROM reply");
		sql.append("WHERE id = ?", modRepCmd);
		
		MysqlUtil.delete(sql);
		
	}

}
