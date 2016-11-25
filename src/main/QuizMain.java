package main;

import java.util.ArrayList;
import java.util.Random;

import frame.GameQuiz;
import info.QuizInfo;
import quiz.RspQuiz;
import quiz.WordPerfect;
import quiz.WordQuiz;
import splash.Splash;

public class QuizMain {
	
	static private int stagePosition = 0;
	static private int recordTime = 0;
	static private int stage = 0;
	static final int MAX_QUIZ_COUNT = 3;
	
	static final int MAX_QUIZ_NUMBER =15;
	
	static ArrayList<QuizInfo> arrQuiz = new ArrayList<>();
	int[] lotto;
	
	
	

	public static void main(String[] args) throws InterruptedException {
		
		Splash.displaySplash(1);

		QuizMain qm = new QuizMain();
		GameQuiz quiz = new GameQuiz();
		
		
		
		//ArrayList 퀴즈만들기
		qm.assignQuiz();
		
		
		
		while (stagePosition < MAX_QUIZ_NUMBER) {
			
			Splash.displayStage(stagePosition);
			
			//주사위던지기
			
			int diceCount = qm.throwDice();
			
			stagePosition += diceCount;
			if (stagePosition > MAX_QUIZ_NUMBER ) stagePosition = MAX_QUIZ_NUMBER;
			//Splash.clear();
			
			Splash.displayStage(stagePosition);

			
/*			//랜덤 퀴즈 만들기
			int quizNo = new Random().nextInt(MAX_QUIZ_COUNT)+1;
			
			switch (quizNo) {
			case 1:
				//단어 빨리치기 게임
				quiz.setQuiz(new WordQuiz());
				break;
			case 2:
				//가위바위보 문제
				quiz.setQuiz(new RspQuiz());
				break;
			case 3:
				//단어 맞추기 게임
				quiz.setQuiz(new WordPerfect());
				break;

			default:
				break;
			}*/
			
			// ArrayList에서 만든 퀴즈 출제하기
			switch (arrQuiz.get(stagePosition-1).getQuizType()) {
				case 0:
				case 1:
				case 3:
				case 4:
				case 5:
					// 단어 빨리치기 게임
					quiz.setQuiz(new WordQuiz());
					break;
				case 6:
				case 7:
				case 8:
				case 9:
				case 10:
					// 가위바위보 문제
					quiz.setQuiz(new RspQuiz());
					break;
				case 11:
				case 12:
				case 13:
				case 14:
					// 단어 맞추기 게임
					quiz.setQuiz(new WordPerfect());
					break;

			}
			
			//퀴즈 story
			quiz.displayStory();
			
			//퀴즈 실패
			if (quiz.makeQuiz()==0) {
				
				// position -1만큼 옮기기
				if (stagePosition > 0) {
					stagePosition--;
					System.out.println("퀴즈 미션에 실패하여 -1 만큼 되돌아갑니다.");
					Thread.sleep(1000);
					
				}
				
			}
			
			Splash.clear();
			Splash.displaySplash(stagePosition);		
			
			
			//모든 퀴즈 완료
			if (stagePosition == MAX_QUIZ_NUMBER) {
				//기록 측정
				System.out.println("모든 단계를 통과했습니다.");
				break;
			}
			 
		}
	}


	public int throwDice() throws InterruptedException {
		
		System.out.println();
		String msg1 ="주사위를 던집니다.";
		for(int i=0;i<msg1.length();i++) {
			Thread.sleep(100);
			System.out.print(msg1.charAt(i));
		}
				
		System.out.println();
		System.out.println();
			
		for (int i = 0; i < 20; i++) {
			Thread.sleep(100);

			if (i % 2 == 0) {
				System.out.print("-●");
			} else {
				System.out.print("-○");
			}
		}
		
		Random rnd = new Random();
		int result = rnd.nextInt(3)+1;		
		Thread.sleep(100);
		System.out.print("  [" + result + "]");
		System.out.println();
		System.out.println();
		System.out.println(result + "칸 전진합니다.");
		System.out.println();
		Thread.sleep(100);
		return result;
		

	}
	
	
	//퀴즈 문제 및 난이도 배정
	
	private void assignQuiz() {
		
		//로또 중복제거
		lottoNumber();

		for (int i=0; i<MAX_QUIZ_NUMBER; i++) {
			
			//퀴즈번호, 퀴즈타입, 퀴즈제한시간, 퀴즈난이도
			QuizInfo qinfo = new QuizInfo(i, lotto[i],0,0);
			arrQuiz.add(qinfo);
		}
		
		System.out.println(arrQuiz);
	}
	
	private void lottoNumber() {
		
		lotto = new int[MAX_QUIZ_NUMBER];

		outer : for (int i = 0; i < MAX_QUIZ_NUMBER;) {
			lotto[i] = new Random().nextInt(MAX_QUIZ_NUMBER);
			for (int j=0;j<i;j++) {
				if (lotto[i] == lotto[j]) {
					continue outer;  //outer의 증감식으로 이동
				}
			}
			i++;
		}

	}
	
	
	
}
