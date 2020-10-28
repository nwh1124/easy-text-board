package 케릭터연습_2;

public class 무기 {
	
	String 이름;
	int 공격력;
	
	void 작동(String 사용자_직업, String 사용자_이름, int 사용자_힘, int 사용자_민첩){
		System.out.printf("- 직업이 %s인 %s(이)가 %s(으)로 공격합니다.\n", 사용자_직업, 사용자_이름, 이름);
		System.out.printf("- 무기공격력 : %d\n",공격력);
		System.out.printf("- 데미지 : %d\n", 공격력*사용자_힘*사용자_민첩);
		
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