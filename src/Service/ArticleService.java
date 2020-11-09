package Service;

import java.util.ArrayList;

import dto.Board;
import Container.Container;
import dao.ArticleDao;
import dto.Article;

public class ArticleService {

	private ArticleDao articleDao;

	public ArticleService(){
		
		articleDao = Container.articleDao;
		
	}

	public int add(int selectedBoardId, int nowLoginedId, String title, String body) {
		return articleDao.add(selectedBoardId, nowLoginedId, title, body);
	}

	public int getArticleSize() {
		return articleDao.getArticleSize();
	}
	
	public int getArticleSize(int i) {
		return articleDao.getArticleSize(i);
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

	public ArrayList<Article> getArrayListBySearchWord(String searchWord) {
		return articleDao.getArrayListBySearchWord(searchWord);
	}

	public ArrayList<Article> getArrayListListing(int i) {
		return articleDao.getArrayListListing(i);
	}

	public int makeBoard(String boardName) {
		return articleDao.makeBoard(boardName);		
	}
	
	public String getBoardName(int inputedId) {
		return articleDao.getBoardName(inputedId);
	}

	public ArrayList<Board> getArrayListBoard() {
		return articleDao.getArrayListBoard();
	}

	public int getBoardSize() {
		return articleDao.getBoardSize();
	}

	public int iniBoardId() {
		ArrayList<Board> board = articleDao.getBoard();
		return board.get(0).num;
	}
	
}
