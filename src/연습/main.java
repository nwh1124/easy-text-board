package 연습;

import java.util.Scanner;

public class main {
	
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);		
		
		케릭터 레드탑 = new 다리우스();
		케릭터 블루탑 = new 티모();
		
		System.out.printf("레드 탑 챔피언 %s의 체력은 %d, 공격력은 %d, 사정거리는 %d입니다\n", 레드탑.이름,레드탑.체력,레드탑.공격력,레드탑.사정거리);
		System.out.printf("블루 탑 챔피언 %s의 체력은 %d, 공격력은 %d, 사정거리는 %d입니다\n", 블루탑.이름,블루탑.체력,블루탑.공격력,블루탑.사정거리);
		
		라인전(레드탑, 블루탑);
	}

	private static void 라인전(케릭터 레드탑, 케릭터 블루탑) {
				
		if(레드탑.사정거리 > 블루탑.사정거리) {
			레드탑.선공권 = true;
		}else {
			블루탑.선공권 = true;
		}
		
		int 거리 = 0;
		
		if(레드탑.선공권) {
			거리 = 레드탑.사정거리 - 블루탑.사정거리;
		}else {
			거리 = 블루탑.사정거리 - 레드탑.사정거리;
		}
		
		진행(거리, 레드탑, 블루탑);
		
		
		
	}

	private static void 진행(int 거리, 케릭터 레드탑, 케릭터 블루탑) {
		
		
		while(레드탑.체력 > 0 && 블루탑.체력 > 0) {
				레드탑.공격(거리,블루탑.이름,블루탑.체력);
				블루탑.피격(레드탑.공격력);
				블루탑.공격(거리,레드탑.이름,레드탑.체력);
				레드탑.피격(블루탑.공격력);
		}
	
	}
		
}

class 케릭터{
	
	String 이름;
	int 체력;
	int 공격력;
	int 사정거리;
	boolean 선공권;
	
	
	public void 공격(int 거리, String 상대방_이름, int 상대방_체력) {
		if( 사정거리 >= 거리) {
			System.out.printf("%s가 %s를 공격했습니다.\n",이름, 상대방_이름);
			System.out.printf("%s가 %d 만큼 데미지를 입었습니다.\n",상대방_이름,공격력);
		}else {
			System.out.printf("%s가 공격을 위해 다가섭니다.\n",이름);
		}
	}


	public void 피격(int 상대방_공격력) {
		체력 = 체력-상대방_공격력;
		System.out.printf("%s의 남은 체력 %d.\n",이름, 체력);			
	}	
}

class 다리우스 extends 케릭터{
	
	다리우스(){
		이름 = "다리우스";
		체력 = 10;
		공격력 = 2;
		사정거리 = 1;
	}
}
class 티모 extends 케릭터{
	
	티모(){
		이름 = "티모";
		체력 = 5;
		공격력 = 1;
		사정거리 = 4;
	}
}
