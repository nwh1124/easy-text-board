package 케릭터연습_3;

import java.util.Calendar;
import java.util.Scanner;

public class main {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int 첫입력 = sc.nextInt();
		
		케릭터[] 케릭터들 = new 케릭터[첫입력];
		
		for(int i = 0; i < 첫입력 ; i ++) {
			
			int 번호 = i + 1 ;
			
			String 이름 = sc.next();
			int 생년 = Integer.parseInt(sc.next().replace("년", ""));
			String 직업 = sc.next();
			
			케릭터들[i] = new 케릭터();
			
			if(직업.equals("의적")) {
				케릭터들[i] = new 의적();
			}else if(직업.equals("도적")) {
				케릭터들[i] = new 도적();
			}if(직업.equals("상인")) {
				케릭터들[i] = new 상인();
			}
			
			케릭터들[i].번호 = 번호;
			케릭터들[i].이름 = 이름;
			케릭터들[i].생년 = 생년;
			케릭터들[i].직업 = 직업;
			
		}
		
		for(int i = 0; i < 첫입력 ; i ++) {
			
			String 능력치 = sc.next();
			String[] 능력치들 = 능력치.split(",");
			int 힘 = Integer.parseInt(능력치들[0]);
			int 지능 = Integer.parseInt(능력치들[1]);
			int 민첩 = Integer.parseInt(능력치들[2]);
			
			케릭터들[i].힘 = 힘;
			케릭터들[i].민첩 = 민첩;
			케릭터들[i].지능 = 지능;			
						
		}
		sc.close();
		
		for(int i = 0 ; i < 첫입력; i++) {
			케릭터들[i].자기소개();
			케릭터들[i].공격();
		}
		
	}

}

class 케릭터{
	
	int 번호;
	String 이름;
	int 생년;
	String 직업;
	int 힘;
	int 지능;
	int 민첩;
	
	무기 a무기;
	
	int get나이(int 생년){
		
		int 올해 = Calendar.getInstance().get(Calendar.YEAR);
		int 나이 = 올해 - 생년;
		
		return 나이;
	}
	
	void 자기소개(){
		
		System.out.println("==자기소개 시작==");
		System.out.printf("번호 : %d\n",번호);
		System.out.printf("이름 : %s\n", 이름);
		System.out.printf("케릭터 종류 : %s\n", 직업);
		System.out.printf("태어난 해 : %04d년\n",생년);
		System.out.printf("나이 : %d\n",get나이(생년));
		System.out.printf("힘 : %d\n",힘);
		System.out.printf("지능 : %d\n",지능);
		System.out.printf("민첩 : %d\n",민첩);
		System.out.println("==자기소개 끝==");
				
	}
	
	void 공격(){
		
		System.out.println("==공격 시작==");
		a무기.작동(이름,직업,힘,민첩);
		System.out.println("==공격 끝==");
		
	}
	
}

class 의적 extends 케릭터{
	
	의적(){
		a무기 = new 검();
		직업 = "의적";
	}
	
}

class 도적 extends 케릭터{
	도적(){
		a무기 = new 도끼();
		직업 = "도적";
	}
	
}

class 상인 extends 케릭터{
	상인(){
		a무기 = new 지팡이();
		직업 = "상인";
	}
}

class 무기{
	
	String 이름;
	int 공격력;
	
	void 작동(String 사용자_이름, String 사용자_직업, int 사용자_힘, int 사용자_민첩){
		System.out.printf("- 직업이 %s인 %s(이)가 %s(으)로 공격합니다.\n",사용자_직업, 사용자_이름, 이름);
		System.out.printf("- 무기공격력 : %d\n",공격력);
		System.out.printf("- 데미지 : %d\n",공격력*사용자_힘*사용자_민첩);
	}
	
}
class 검 extends 무기{
	검(){
		이름 = "검";
		공격력 = 10;
	}	
}
class 도끼 extends 무기{
	도끼(){
		이름 = "도끼";
		공격력 = 15;
	}	
}
class 지팡이 extends 무기{
	지팡이(){
		이름 = "지팡이";
		공격력 = 2;
	}	
}