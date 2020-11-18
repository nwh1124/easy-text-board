package service;

import container.Container;
import dto.Article;
import dto.Board;
import dto.Reply;

import java.util.List;
import dao.ArticleDao;

public class ArticleService {

	private ArticleDao articleDao;
	
	public ArticleService() {
		
		articleDao = Container.articleDao;
		
	}

	public int add(String title, String body, int loginedId, int selectedBoardId) {
		return articleDao.add(title, body, loginedId, selectedBoardId);
	}

	public Article getArticle(int inputedId) {
		return articleDao.getArticle(inputedId);
	}

	public void delete(int inputedId) {
		articleDao.delete(inputedId);		
	}

	public int getArticleWriterId(int inputedId) {
		return articleDao.getArticleWriterId(inputedId);
	}

	public String getBoardNameById(int boardId) {
		return articleDao.getBoardNameById(boardId);
	}

	public void modify(String modTitle, String modBody, int inputedId) {
		articleDao.modify(modTitle, modBody, inputedId);
	}

	public void writeReply(String body, int loginedId, int inputedId) {
		articleDao.writeReply(body, loginedId, inputedId);
	}

	public List<Reply> getReplysByArticleId(int inputedId) {
		return articleDao.getReplysByArticleId(inputedId);
	}

	public int makeBoard(String boardName) {
		return articleDao.makeBoard(boardName);
	}

	public Board getBoardById(int inputedId) {
		return articleDao.getBoardById(inputedId); 
	}

	public List<Article> getListArticle(int selectedBoardId) {
		return articleDao.getListArticle(selectedBoardId);
	}
	
	public List<Article> getListArticle() {
		return articleDao.getListArticle();
	}

	public List<Article> getSearchArticles(String searchWord) {
		return articleDao.getSearchArticles(searchWord);
	}

	public List<Reply> getReplysByMemberId(int loginedId) {
		return articleDao.getReplysByMemberId(loginedId);
	}

	public Reply getReplysById(int modCmd) {
		return articleDao.getReplysById(modCmd);
	}

	public void modReply(int modRepCmd, String modRepBody) {
		articleDao.modReply(modRepCmd, modRepBody);
	}

	public void delReply(int modRepCmd) {
		articleDao.delReply(modRepCmd);
	}

}
