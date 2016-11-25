package quiz;

import java.util.ArrayList;
import java.util.Scanner;

import frame.Quiz;



public class WordQuiz implements Quiz {
	
	ArrayList<String> arr = new ArrayList<>();
	String[] data = { "apple" };
	//String[] data = { "apple", "pineapple", "strawberry", "grape", "pear" };
	
	@Override
	public int make() {
		System.out.println("------워드퀴즈------");
		
		for (int i=0;i<data.length;i++) {
			arr.add(data[i]);
		}

		WordGame wg = new WordGame(arr, data);
		wg.start();

		Scanner sc = new Scanner(System.in);

		while (true) {

			if (arr.size() == 0) {
				System.out.println("클리어");
				wg.setPlaying(false);
				break;
			}

			System.out.println(arr);
			System.out.print(">> ");

			String input = sc.next();

			// 정답처리
			for (int i = 0; i < arr.size(); i++) {
				if (input.equals(arr.get(i))) {
					arr.remove(i);
				}
			}
		}
		
		return 1;
	}

}

	




