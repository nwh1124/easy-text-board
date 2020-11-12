package dao;

import dao.Connect;
import dao.Connect;
import java.util.ArrayList;

import Container.Container;
import dto.Board;
import dto.Article;

public class ArticleDao {

	private ArrayList<Article> articles;
	int lastArticleNum;
	
	private ArrayList<Board> boards;
	int lastBoardNum;
		
	private Connect con;
	
	public ArticleDao(){
		
		articles = new ArrayList<>();
		lastArticleNum = 0;
		
		boards = new ArrayList<>();
		lastBoardNum = 0;
		
		con = new Connect();
		
	}
	
	public int makeBoard(String boardName) {
		
		lastBoardNum++;
		boards.add(new Board(lastBoardNum, boardName)); 		
		
		return lastBoardNum;
		
	}

	public int add(int selectedBoardId, String writer, String title, String body) {
		
		lastArticleNum++;
		//articles.add(new Article(selectedBoardId, lastArticleNum, nowLoginedId, title, body));
		
		con.add(articles, writer, title, body);
		
		return lastArticleNum;
		
	}	

	public String getBoardName(int inputedId) {
		for(Board board : boards) {
			if(board.num == inputedId) {
				return board.name;
			}
		}
		return null;
	}
	
	public ArrayList<Board> getArrayListBoard() {
		return boards;
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
		
		con.modify(inputedId, modTitle, modBody);
		
//		articles.get(inputedId-1).title = modTitle;
//		articles.get(inputedId-1).body = modBody;
				
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
		con.delete(inputedId);
		//articles.remove(inputedId-1);
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
		articles = con.connent();
		for(Article article : articles) {
			if(article.boardId == i) {
				list.add(article);
			}
		}
		return list;
	}

	public int getBoardSize() {
		return boards.size();
	}

	public ArrayList<Board> getBoard() {
		return boards;
	}

	public void getDate() {
		articles = con.connent();
	}

	public ArrayList<Article> getExistsArticle() {
		return articles;
	}

}
