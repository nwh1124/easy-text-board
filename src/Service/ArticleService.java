package Service;

import java.util.ArrayList;

import Container.Container;
import dao.ArticleDao;
import dto.Article;

public class ArticleService {

	private ArticleDao articleDao;

	public ArticleService(){
		
		articleDao = Container.articleDao;
		
	}

	public int add(int nowLoginedId, String title, String body) {
		return articleDao.add(nowLoginedId, title, body);
	}

	public int getArticleSize() {
		return articleDao.getArticleSize();
	}

	public ArrayList<Article> getArticle() {
		return articleDao.getArticle();
	}

	public Article getArticleByInput(int inputedId) {
		return articleDao.getArticleByInput(inputedId);
	}

	public void modify(int inputedId, String modTitle, String modBody) {
		articleDao.modify(inputedId, modTitle, modBody);	
	}

	public int getArticleMemberIdByInput(int inputedId) {
		return articleDao.getArticleMemberIdByInput(inputedId);
	}

	public void delete(int inputedId) {
		articleDao.delete(inputedId);
	}
	
}
