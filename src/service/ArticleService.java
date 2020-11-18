package service;

import container.Container;
import dto.Article;
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

	public List<Article> getListArticle() {
		return articleDao.getListArticle();
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

}
