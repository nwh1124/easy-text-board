package 케릭터연습_4;

import java.util.Scanner;

public class main {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		
		케릭터[] 케릭터들 = new 케릭터[num];
		
		for(int i = 0; i < num ; i++) {
			
			int 번호 = i + 1;
			String 이름 = sc.next();
			int 태어난_해 = Integer.parseInt(sc.next().replace("년",""));
			String 직업 = sc.next();
			
			케릭터들[i] = null;
			
			if(직업.equals("의적")) {
				케릭터들[i] = new 의적();			
			}else if(직업.equals("도적")) {
				케릭터들[i] = new 도적();			
			}else if(직업.equals("상인")) {
				케릭터들[i] = new 상인();
			}
			
			케릭터들[i].번호 = 번호;
			케릭터들[i].이름 = 이름;
			케릭터들[i].태어난_해 = 태어난_해;
			케릭터들[i].직업 = 직업;
			
		}
		
		for(int i = 0; i < num ; i++) {
			
			String 능력치 = sc.next();
			String[] 능력치들 = 능력치.split(",");
			
			케릭터들[i].힘 = Integer.parseInt(능력치들[0]);
			케릭터들[i].지능 = Integer.parseInt(능력치들[1]);
			케릭터들[i].민첩 = Integer.parseInt(능력치들[2]);
			
		}
		sc.close();
		for(int i = 0; i < num; i++) {
			케릭터들[i].자기소개();
			케릭터들[i].공격();
		}
		
	}

}
