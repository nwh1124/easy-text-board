package Service;

import java.util.ArrayList;

import Board.Board;
import Container.Container;
import dao.ArticleDao;
import dto.Article;

public class ArticleService {

	private ArticleDao articleDao;
	private ArrayList<Board> boards;
	int lastBoardNum;

	public ArticleService(){
		
		articleDao = Container.articleDao;
		boards = new ArrayList();
		lastBoardNum = 0;
		
		board_init();
		
	}

	private void board_init() {
		makeBoard("공지사항");
	}

	public int add(int nowLoginedId, String title, String body) {
		return articleDao.add(Container.session.getSelectedBoardId() , nowLoginedId, title, body);
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
		
		lastBoardNum++;
		boards.add(new Board(lastBoardNum, boardName)); 		
		
		return lastBoardNum;
		
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
	
}
