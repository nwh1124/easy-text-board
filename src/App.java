import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class App {

	Article[] articles = new Article[100];
	int id = 0;
	int articlesCount = 0;

	public void run() {
		

		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.printf("명령어 ) ");
			String command = sc.nextLine();

			if (command.equals("article add")) {
				System.out.println("== 게시물 등록 ==");
				if (articlesCount < articles.length) {
					System.out.printf("제목 : ");
					String title = sc.nextLine();
					System.out.printf("내용 : ");
					String body = sc.nextLine();
					id++;
					
					articles[articlesCount] = new Article();
					articles[articlesCount].id = id;
					articles[articlesCount].title = title;
					articles[articlesCount].body = body;
					Date date = new Date();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					articles[articlesCount].time = dateFormat.format(date) ; 
					articlesCount++;
				} else {
					System.out.println("== 저장 공간이 부족합니다 ==");
				}

			} else if (command.equals("article list")) {
				System.out.println("== 게시물 목록 ==");

				if (articlesCount <= 0) {
					System.out.println("== 게시물이 없습니다 ==");
				} else {
					System.out.println("번호 / 내용");					
					for (int i = articlesCount-1; i >= 0; i--) {
						System.out.printf("%d / %s\n", articles[i].id, articles[i].title);
					}
				}

			} else if (command.startsWith("article detail ")) {
				System.out.println("== 게시물 상세 ==");
				int index = FinIndex(command);
				if (index < 0) {
					System.out.println("== 게시물이 존재하지 않습니다 ==");
				} else {
					
					System.out.printf("날짜 : %s\n번호 : %d\n제목 : %s\n내용 : %s\n",articles[index].time, articles[index].id, articles[index].title,
							articles[index].body);
				}

			} else if (command.startsWith("article delete ")) {
				System.out.println("== 게시물 삭제 ==");
				int index = FinIndex(command);
				if (index < 0) {
					System.out.println("== 게시물이 존재하지 않습니다 ==");
				} else {
					for (int i = index; i < (articlesCount - 1); i++) {
						articles[i].id = articles[i + 1].id;
						articles[i].title = articles[i + 1].title;
						articles[i].body = articles[i + 1].body;
						articles[i].time = articles[i + 1].time;
					}
					articlesCount--;
					System.out.printf("%d번 게시물이 삭제되었습니다\n",index);
				}
			}else if (command.equals("array")) {
				for (int i = 0; i < articlesCount; i++) {
					System.out.printf("articles[%d], Num %d, title %s, body %s\n", i, articles[i].id, articles[i].title,
							articles[i].body);
				}
			}else if (command.startsWith("article modify ")) {
				System.out.println("== 게시물 수정 ==");
				int index = FinIndex(command);
				if (index < 0) {
					System.out.println("== 게시물이 존재하지 않습니다 ==");
				} else {
					System.out.printf("제목 수정 : ");
					String modTitle = sc.nextLine();
					System.out.printf("내용 수정 : ");
					String modBody = sc.nextLine();
					
					System.out.printf("%d번 게시물 수정\n제목 : %s -> %s\n내용 : %s -> %s\n",
							index,articles[index].title,modTitle,articles[index].body,modBody);

					articles[index].title = modTitle;
					articles[index].body = modBody;

					System.out.printf("== 게시물이 수정되었습니다 ==\n");
				}
			}
			
			else if(command.startsWith("article search ")) {
				System.out.println("== 게시물 검색 ==");
				int searchStack = 0; 
				int[] searchNum = new int[articlesCount];
				String search[] = command.split(" ");
								
				for ( int i = 0; i < articlesCount; i++ ) {
					if ( articles[i].title.contains(search[2]) || articles[i].body.contains(search[2]) ) {
						searchNum[searchStack] = articles[i].id;
						searchStack++;
					}
				}
				if ( searchStack == 0 ) {
					System.out.println("== 일치하는 내용을 찾지 못했습니다 ==");
				}
				else {
					System.out.printf("== 일치하는 게시물 ==\n");
					for(int i = 0; i < searchStack; i++) {						
						System.out.printf("%d번 게시물\n",searchNum[i]);
					}
				}				
			}

			else if (command.equals("system exit")) {
				System.out.println("== 종료 ==");
				break;

			} else {
				System.out.println("== 잘못된 명령어 입력 ==");
			}
		}
		sc.close();
	}
/*
	private int ArraySize() {

		int arraySize;

		System.out.printf("저장소의 크기를 지정하세요 : ");
		arraySize = arraySc.nextInt();

		return arraySize;
	}
*/
	private int FinIndex(String command) {
		String[] commandBits = command.split(" ");
		int inputedId = Integer.parseInt(commandBits[2]);

		for (int i = 0; i < articlesCount; i++) {
			if (inputedId == articles[i].id) {
				return i;
			}
		}
		return -1;
	}
}