package quiz;

import java.util.Random;
import java.util.Scanner;

import frame.Quiz;

public class RspQuiz implements Quiz {

	@Override
	public void make() {
		
		Scanner sc = new Scanner(System.in);
		int win=0, lose=0, draw=0;

		while (true) {
			//컴퓨터
			int com = new Random().nextInt(3);
			//0:가위, 1:바위 2:보 

			System.out.print("가위(s) | 바위 (r) | 보 (p) : ");
			String  user = sc.next();
			int usercount=0;
			
			if (user.equalsIgnoreCase("s")) 
				usercount=0; 
			else if (user.equalsIgnoreCase("r")) 
				usercount=1;
			else if (user.equalsIgnoreCase("p")) 
				usercount=2;
			
			//판정
			if (usercount-com == -2 || usercount-com == 1) {
				System.out.println("당신이 이겼습니다.");
				win++;
			} else if (usercount-com ==0) {
				System.out.println("무승부입니다.");
				draw++;
			} else {
				System.out.println("당신이 졌습니다.");
				lose++;
			}
			
			System.out.println(win + "승 " + draw + "무 " + lose + "패");

			if (win == 3) {
				break;
			}
		}
		System.out.println("미션 클리어");
	}
}