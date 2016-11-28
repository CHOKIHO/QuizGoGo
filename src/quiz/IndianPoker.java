package quiz;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import frame.Quiz;

public class IndianPoker implements Quiz{

	@Override
	public int make() {
		int life = 3;
		int wincount = 0;
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> arr = new ArrayList<>();
		ArrayList<Integer> arr2 = new ArrayList<>();
		for(int i =1; i <= 12; i++){
			arr.add(i);//내카드
			arr2.add(i);//상대카드
		}

		while(true){
			int com = new Random().nextInt((arr2.size()-1));
			int user = new Random().nextInt((arr.size()-1));
			System.out.println("com 카드 : "+arr.get(com));
			System.out.println("내카드 : "+"?");
			System.out.println("1.배팅 : ");
			System.out.println("2.다이 : ");
			System.out.print(">>");
			int list = sc.nextInt();
			switch(list){
			case 1: 
				if(arr2.get(com) >= arr2.get(user)){
					System.out.println("내카드 : "+ arr.get(user));
					life--;
					System.out.println("YOU LOSE    "+"목숨 : "+life);
					arr.remove(user);
					arr2.remove(com);

				}
				if(arr2.get(com) < arr2.get(user)){
					System.out.println("내카드 : "+arr.get(user));
					System.out.println("NAME WIN    "+"목숨 : "+life);
					wincount++;
					arr.remove(user);
					arr2.remove(com);
				}
				break;
			case 2:
				if(arr2.get(com) >= arr2.get(user)){
					System.out.println("내카드 : " +arr.get(user));
					System.out.println("굳굳   "+"목숨 : "+life);
					arr.remove(user);
					arr2.remove(com);
				}
				if(arr2.get(com) < arr2.get(user)){
					System.out.println("내카드 : "+arr.get(user));
					--life;
					System.out.println("실수OTL    "+"목숨 : "+life);
					arr.remove(user);
					arr2.remove(com);
				}
				break;
			}//switch
			if(life ==0){
				System.out.println("NAME LOSE");
				break;
			}//if
			if(wincount ==5){
				System.out.println("GAME WIN");
				System.out.println("주사위 RoLL");
				break;
			}
		}//while
		return 1;
	}

	@Override
	public String[] story() {
		String[] msg = { "당신은 강원랜드에 놀러왔습니다.", "인디언포커를 시작합니다.", "3번을 먼저 이겨야 미션을 클리어 할 수 있습니다." };
		return msg;

	}

}
