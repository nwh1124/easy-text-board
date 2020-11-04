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
		
		add(1, "주말이 빨리 왔으면", "코딩하면 시간 잘 간다");
		add(2, "워어어어얼화아아수우모옥금", "퇼");
		
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
