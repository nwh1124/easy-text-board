package 케릭터연습_1;

import java.text.SimpleDateFormat;
import java.util.Date;

class 케릭터 {
	
	int 번호;
	String 이름;
	int 태어난_해;
	String 직업;
	int 힘;
	int 지능;
	int 민첩;
	
	무기 a무기;
	
	int get나이(int 태어난_해){
		
		Date 현재 = new Date();
		SimpleDateFormat 년도형식 = new SimpleDateFormat("yyyy");
		int 현재년도 = Integer.parseInt(년도형식.format(현재));
		int 나이 = 현재년도 - 태어난_해;
		return 나이;
		
	}
	
	void 자기소개() {
		System.out.println("== 자기소개 시작 ==");
		
		System.out.printf("번호 : %d번\n", 번호);
		System.out.printf("이름 : %s\n", 이름);
		System.out.printf("캐릭터 종류 : %s\n", 직업);
		System.out.printf("태어난 해 : %04d년\n", 태어난_해);
		System.out.printf("나이 : %d살\n", get나이(태어난_해));
		System.out.printf("힘 : %d\n", 힘);
		System.out.printf("지능 : %d\n", 지능);
		System.out.printf("민첩 : %d\n", 민첩);
		
		System.out.println("== 자기소개 끝 ==");
	}
	
	void 공격() {
		System.out.printf("== 공격 시작 ==\n");
		new 무기().작동(직업, 이름, 힘, 민첩);
		System.out.printf("== 공격 끝 ==\n");
	}
}
