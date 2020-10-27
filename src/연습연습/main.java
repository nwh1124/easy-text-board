package 연습연습;

import java.util.Scanner;

public class main {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);		
		int num = sc.nextInt();
		
		케릭터[] 케릭터들 = new 케릭터[num];
		
		for(int i = 0; i < num; i++) {
			int 번호 = i + 1;
			
			String 이름 = sc.next();
			int 태어난_해 = Integer.parseInt(sc.next().replace("년", ""));
			String 직업 = sc.next();
			
			케릭터 a케릭터 = new 케릭터();
						
			if(직업.equals("의적")) {
				케릭터들[i] = new 의적();
			}else if(직업.equals("도적")) {
				케릭터들[i] = new 도적();
			}else if(직업.equals("상인")) {
				케릭터들[i] = new 상인();
			}
			
			a케릭터.번호 = 번호;
			a케릭터.이름 = 이름;
			a케릭터.태어난_해 = 태어난_해;
			a케릭터.직업 = 직업;
			
			케릭터들[i] = a케릭터;
		}
		
		for (int i = 0 ; i < num ; i ++) {
			
			String 능력치;
			능력치 = sc.next();
			String[] 능력치들 = 능력치.split(",");
			
			int 힘 = Integer.parseInt(능력치들[0]);
			int 지능 = Integer.parseInt(능력치들[1]);
			int 민첩 = Integer.parseInt(능력치들[2]);
			
			케릭터들[i].힘 = 힘;
			케릭터들[i].지능 = 지능;
			케릭터들[i].민첩 = 민첩;
			
		}sc.close();	
		
		for(int i = 0 ; i < num ; i ++) {
			케릭터들[i].자기소개();
			케릭터들[i].공격();
		}
	
	}

}
