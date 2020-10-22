import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class App {
	
	Article[] articles = new Article[32];
	int id;
	int articlesCount;
	
	public App(){
		
		id = 0;
		articlesCount = 0;
		
	}

	public void run() {
				
		Scanner sc = new Scanner(System.in);
		
		//게시물 32개 생성
		for(int i = 0; i < 32; i++) {
			
			String title = "0"+(i+1);
			String body = "0"+(i+1);
			String pass = "0000";
			id++;
			
			Add(id, articlesCount,title, body, pass );
			articlesCount++;
		}
		
		while(true) {
			System.out.println("명령어 ) ");
			String command = sc.nextLine();
			
			if ( command.equals("article add")) {

				System.out.println("== 게시물 등록 ==");
				
				if( articlesCount < articles.length ) {
					System.out.printf("제목 : ");
					String title = sc.nextLine();
					System.out.printf("내용 : ");
					String body = sc.nextLine();
					System.out.printf("비밀번호 : ");
					String pass = sc.nextLine();
					id++;
					
					Add(id, articlesCount, title, body, pass);
					
					articlesCount++;
				}
			}
			else if( command.startsWith("article list ")) {
				System.out.println("== 게시물 목록 ==");
				if ( articlesCount <= 0 ) {
					System.out.println("== 등록된 게시물이 없습니다 ==");
				}else {
					Listing(articlesCount,command);
				}
			}
			else if(command.equals("system exit")) {
				System.out.println("== 프로그램 종료 ==");
				break;				
			}
			else if ( command.startsWith("article detail ")) {
				System.out.println("== 게시물 상세 ==");
				int idCount = getCount(command, articlesCount);
				if(idCount == -1 || articlesCount <= 0 ) {
					System.out.println("== 게시물을 찾을 수 없습니다 ==");
				}else {
					detail(idCount);					
				}
			}else if( command.startsWith("article modify ")) {
				System.out.println("== 게시물 수정 ==");
				int idCount = getCount(command, articlesCount);
				if(idCount == -1 || articlesCount <= 0) {
					System.out.println("== 게시물을 찾을 수 없습니다 ==");
				}else {
					modify(idCount, sc);
				}
			}else if( command.startsWith("article delete ")) {
				int idCount = getCount(command, articlesCount);
				if(idCount == -1 || articlesCount <= 0) {
					System.out.println("== 게시물을 찾을 수 없습니다 ==");
				}else {
					articlesCount = delete(idCount, articlesCount, sc);
				}
			}else if (command.startsWith("article search ")) {
				articleSearch(command, articlesCount);
			}
			else if(command.equals("array")) {
				for (int i = 0; i < articlesCount;i++) {
					System.out.printf("articles[%d] .id = %d .title = %s .body = %s .pass %s .time = %s\n",
							i, articles[i].id, articles[i].title, articles[i].body, articles[i].pass, articles[i].time);
				}
			}
			
			else {
				System.out.println("== 잘못된 명령어 입력 ==");
			}
				
		}
		sc.close();	
	}

	private void detail(int idCount) {
		System.out.printf("날짜 : %s\n번호 : %d\n제목 : %s\n내용 : %s\n",articles[idCount].time,
				articles[idCount].id,articles[idCount].title,articles[idCount].body);
	}

	Article[] Artinc(int articlesCount) {
		
		Article[] newArticles = new Article[articles.length*2];
		
		for ( int i = 0; i < articlesCount; i++ ) {
			newArticles[i] = new Article();
			newArticles[i] = articles[i];
		}		
		
		return newArticles;
	}

	private void articleSearch(String command, int articlesCount) {
		
		int[] searchId = new int[articlesCount];
		int searchStack = 0;
		String[] commandBits = command.split(" ");
		
		for (int i = 0; i < articlesCount; i++) {
			if ( articles[i].title.contains(commandBits[2]) || articles[i].body.contains(commandBits[2])) {
				searchId[searchStack] = i;
				searchStack++;
			}
		}
			if (searchStack == 0){
				System.out.println("== 게시물을 찾을 수 없습니다 ==");
			}else {
				System.out.printf("== 일치하는 게시물 ==\n");
				for (int j = 0; j < searchStack; j++ ) {
					int i = searchId[j]; 
					System.out.printf("%d번 게시물\n",articles[i].id);
				}
			}
		
	}

	public int delete(int idCount, int articlesCount, Scanner sc) {
		
		
		System.out.println("== 비밀번호를 입력하세요 ==\n(초기 설정 '0000')\n");	
		String passCheck = sc.nextLine();
		if (passCheck.equals(articles[idCount].pass)) {
			for (int i = idCount; i < articlesCount-1; i++) {
				articles[i].id = articles[i+1].id;
				articles[i].title = articles[i+1].title;
				articles[i].body = articles[i+1].body;
				articles[i].time = articles[i+1].time;
			}
			System.out.printf("== %d번 게시물이 삭제되었습니다 ==\n",articles[idCount].id);
			return --articlesCount;
		}else {
			System.out.println("== 비밀번호가 일치하지 않습니다 ==");
			return articlesCount;
		}
	}

	private void modify(int idCount, Scanner sc) {

		String passCheck;
		System.out.println("== 비밀번호를 입력하세요 ==\\n(초기 설정 '0000')\\n");	
		passCheck = sc.nextLine();
		
		
		if (passCheck.equals(articles[idCount].pass)) {
			System.out.println("제목 변경 : ");
			String modTitle = sc.nextLine();
			System.out.println("내용 변경 : ");
			String modBody = sc.nextLine();
			
			System.out.printf("제목 : %s -> %s\n내용 : %s -> %s\n== 수정되었습니다 ==\n",
			articles[idCount].title, modTitle, articles[idCount].body, modBody);
			
			articles[idCount].title = modTitle;
			articles[idCount].body = modBody;
		}else {
			System.out.println("== 비밀번호가 일치하지 않습니다 ==");
		}
	}

	private int getCount(String command, int articlesCount) {
		
		String[] commandBits = command.split(" ");
		int inputedId = Integer.parseInt(commandBits[2]);
		
		for (int i = 0 ; i < articlesCount; i++ ) {
			if( articles[i].id == inputedId ) {
				return i;
			}
		}
		return -1;
	}

	private void Listing(int articlesCount, String command) {
		String[] commandBits = command.split(" ");
		int inputedId = Integer.parseInt(commandBits[2]);
		//입력받은 '리스트 번호'를 정수화하여 inputedId에 넣음
		
		int forCount = (articlesCount-1) - (10*(inputedId-1)) ;
		int page = articlesCount/10;
		//반복문을 실행할 변수들을 선언
		
		if (inputedId > 0 && inputedId <= page) {
			System.out.println("번호 / 제목");
			for (int i = forCount; i >= forCount-9; i--) {
				System.out.printf("%d / %s\n",articles[i].id,articles[i].title);
			}
		}//입력받은 값대로 최신 게시물부터 페이지 출력
		
		else if( inputedId == page+1){
			System.out.println("번호 / 제목");
			for (int i = ((articlesCount-1)-(10*(inputedId-1))); i >= 0; i--) {
				System.out.printf("%d / %s\n",articles[i].id,articles[i].title);
			}
		}//입력받은 값이 마지막 리스트 페이지를 가리킬 경우 남아있는 10개 미만의 리스트 출력
		
		else {
			System.out.println("== 게시물 페이지가 잘못되었습니다 ==");
		}//입력받은 값이 0, 음수, 게시물보다 많은 수를 가리킬 경우 에러 메세지 출력
	}

	public void Add(int id, int articlesCount, String title, String body, String pass) {
				if ( articlesCount >= articles.length ) {
					
					articles = Artinc(articlesCount);
					
					System.out.println("== 저장소가 확장되었습니다 ==");
				}
			
				Date date;
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				
				articles[articlesCount] = new Article();
				articles[articlesCount].id = id;
				articles[articlesCount].title = title;
				articles[articlesCount].body = body;
				articles[articlesCount].pass = pass;
				
				date = new Date();
				articles[articlesCount].time = format.format(date);
				if ( id > 33) {
				System.out.printf("== %d번 게시물이 생성되었습니다 ==\n", id);
				}
	}
}