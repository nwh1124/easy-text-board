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

	public int add(String title, String body, int loginedId) {
		return articleDao.add(title, body, loginedId);
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

}
