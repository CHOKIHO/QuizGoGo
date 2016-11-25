package quiz;

import java.util.Random;
import java.util.Scanner;

import frame.Quiz;

public class WordPerfect implements Quiz {

	@Override
	public void make() {
		
		String[] strArr = { "ELEPHANT", "SQUARREL", "RAINBOW", "INCREPAS" };
		Random random = new Random();

		Scanner sc = new Scanner(System.in);
		String answer = "";

		int select = random.nextInt(strArr.length);

		System.out.println("제시한 단어의 스펠링을 맞추어 보세요");
		RandomSpeller rs = new RandomSpeller(strArr[select]);
		System.out.println("문제 : " + rs.makeRandomWords());

		while (true) {

			System.out.print("정답은? : ");
			answer = sc.next();
			
//			if(sc.hasNextLine()){
//				answer=sc.next();
//			}
			
			

			if (answer.equalsIgnoreCase(strArr[select])) {
				System.out.println(answer + "는 정답입니다.");
				break;
			} else {
				System.out.println(answer + "는 오답입니다.");
			}

		}
		//sc.close();
	}

}

