package quiz;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import frame.Quiz;


public class HangMan implements Quiz {

	@Override
	public int make() {
		//제시어 : 과일
				String[] quiz = {"apple", "banana", "blueberry", "grape", "lemon", "mango","melon", 
						"orange", "peach", "strawberry", "watermelon"};
				
				ArrayList<String> inputs = new ArrayList<String>(); //맞춘 알파벳
				
				Scanner sc = new Scanner(System.in);
				
				int n = new Random().nextInt(quiz.length);
				String word = quiz[n];
				int count = 10;
				//System.out.println(word);

				System.out.println("행맨 게임을 시작합니다. 제한횟수 10번!");

				HangmanGame hg = new HangmanGame(word);
				hg.setDefault();

				do{
					
					System.out.println("\n남은 횟수: " + count);
					count--;
					System.out.println("------------------------------");

					System.out.println("1.알파벳맞추기");
					System.out.println("2.정답맞추기");
					System.out.println("etc.중도포기");
					System.out.print(">> ");

					String select = sc.next();

					if(select.equals("1")){

						System.out.print(">> ");
						String input = sc.next();
						inputs.add(input.trim());
						
						hg.setWord(inputs);
						//System.out.println(inputs);
										
					}else if(select.equals("2")){
						System.out.print(">> ");
						String input2 = sc.next();
						
						if(word.equals(input2.trim())){
							System.out.println("성공!");
							break;
						}

					}else{
						System.out.println("포기");
						break;
					}

				}while(count>0);
				
				
		return 1;
	}

	@Override
	public String[] story() {
		String[] msg = {"당신은 무인도에 갇혔습니다.",
				        "행맨을 시작합니다.",
						"3번을 먼저 이겨야 미션을 클리어 할 수 있습니다."};
		return msg;
		
	}


}
