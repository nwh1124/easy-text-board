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
			add(1, 1, "제목 " + i, "내용 " + i);
		}
		for(int i = 17; i <= 32; i++) {
			add(1, 2, "제목 " + i, "내용 " + i);
		}
		
	}

	public int add(int selectedBoardId, int nowLoginedId, String title, String body) {
		
		lastArticleNum++;
		articles.add(new Article(selectedBoardId ,lastArticleNum, nowLoginedId, title, body));
		
		return lastArticleNum;
		
	}

	public int getArticleSize() {
		return articles.size();
	}
	
	public int getArticleSize(int i) {
		ArrayList<Article> size = new ArrayList<>();
		
		for(Article article : articles) {
			if(article.boardId == (i+1)) {
				size.add(article);
			}
		}		
		return size.size();
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

	public ArrayList<Article> getArrayListBySearchWord(String searchWord) {
		ArrayList<Article> search = new ArrayList<>();
		
		for(Article article : search) {
			if(article.title.contains(searchWord) || article.body.contains(searchWord)) {
				search.add(article);
			}
		}
		
		return search;
	}

	public ArrayList<Article> getArrayListListing(int i) {
		ArrayList<Article> list = new ArrayList<>();
		for(Article article : articles) {
			if(article.boardId == i) {
				list.add(article);
			}
		}
		return list;
	}
	
}
