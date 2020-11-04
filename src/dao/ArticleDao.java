package dao;

import java.util.ArrayList;

import dto.Article;

public class ArticleDao {

	ArrayList<Article> articles;
	int lastArticleNum;
	
	public ArticleDao(){
		
		articles = new ArrayList<>();
		lastArticleNum = 0;
		
		makeTestArticle();
	}

	private void makeTestArticle() {
		
		for(int i = 1; i <= 16; i++) {
			add(1, "제목 " + i, "내용 " + i);
		}
		for(int i = 17; i <= 32; i++) {
			add(2, "제목 " + i, "내용 " + i);
		}
		
	}

	public int add(int nowLoginedId, String title, String body) {
		
		lastArticleNum++;
		articles.add(new Article(lastArticleNum, nowLoginedId, title, body));
		
		return lastArticleNum;
		
	}

	public int getArticleSize() {
		return articles.size();
	}

	public ArrayList<Article> getArticle() {
		return articles;
	}

	public Article getArticleByInput(int inputedId) {
		for(Article article : articles) {
			if(article.num == inputedId) {
				return article;
			}
		}
		return null;
	}

	public void modify(int inputedId, String modTitle, String modBody) {
		
		articles.get(inputedId-1).title = modTitle;
		articles.get(inputedId-1).body = modBody;
				
	}

	public int getArticleMemberIdByInput(int inputedId) {
		for(Article article : articles) {
			if(article.num == inputedId) {
				return article.memberId;
			}
		}
		return 0;
	}

	public void delete(int inputedId) {
		articles.remove(inputedId-1);
	}
	
}
