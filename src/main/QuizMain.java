package main;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import frame.QuizBank;
import info.PlayerInfo;
import info.QuizInfo;
import player.PlayerRank;
import quiz.BadChance;
import quiz.ChosungQuiz;
import quiz.CloverChance;
import quiz.HangMan;
import quiz.IndianPoker;
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
	static QuizTimeThread qthread;
	
	
	public static void main(String[] args) throws InterruptedException {

		Splash.displaySplash(1);

		// 랭킹 정보 불러오기
		PlayerRank.getInstance().showRanking();

		while (true) {
			showMenu();
			System.out.println();
			System.out.print(">> ");
			int choice = new Scanner(System.in).nextInt();

			if (choice == 1) {
				
				startGame();
				
				qthread = new QuizTimeThread();
				qthread.setDaemon(true);
				qthread.setTimeElepsed(0);
				qthread.start();
				
				
				

			} else if (choice == 2) {
				exerciseGame();

			} else if (choice == 0) {
				break;
			}
		}

	}
			 

	
	
	public static void startGame() throws InterruptedException {
		QuizMain qm = new QuizMain();
		QuizBank quiz = new QuizBank();

		while (stagePosition < MAX_QUIZ_NUMBER) {

			stage++;

			Splash.displayStage(stagePosition);

			// 랭킹표시

			// 주사위던지기
			int diceCount = qm.throwDice();

			stagePosition += diceCount;
			if (stagePosition > MAX_QUIZ_NUMBER)
				stagePosition = MAX_QUIZ_NUMBER;
			// Splash.clear();

			Splash.displayStage(stagePosition);
			
			
			// ArrayList 퀴즈만들기
			qm.assignQuiz();
			

			// ArrayList에서 만든 퀴즈 출제하기
			switch (arrQuiz.get(stagePosition - 1).getQuizType()) {
			case 0:
			case 1:
			case 2:
				//클로버행운
				quiz.setQuiz(new CloverChance());
				if (++stagePosition > MAX_QUIZ_NUMBER)
					stagePosition = MAX_QUIZ_NUMBER;
			case 3:
			case 4:
				//꽝처리
				quiz.setQuiz(new BadChance());
			case 5:
				// 초성퀴즈
				quiz.setQuiz(new ChosungQuiz());
				break;
			case 6:
				// 인디언포커
				quiz.setQuiz(new IndianPoker());
				break;
			case 7:
				// 초성퀴즈
				quiz.setQuiz(new ChosungQuiz());
				break;

			case 8:
				// 단어 빨리치기 게임
				quiz.setQuiz(new HangMan());
				break;

			case 9:
				// 초성퀴즈
				quiz.setQuiz(new ChosungQuiz());
				break;

			case 10:
				// 가위바위보 문제
				quiz.setQuiz(new RspQuiz());
				break;
			case 11:
				// 행맨
				quiz.setQuiz(new HangMan());
				break;

			case 12:
				//꽝처리
				quiz.setQuiz(new BadChance());
			case 13:
				// 초성퀴즈
				quiz.setQuiz(new ChosungQuiz());
				break;

			case 14:
				// 단어 맞추기 게임
				quiz.setQuiz(new WordPerfect());
				break;

			}


			
			// 퀴즈 story
			quiz.displayStory();

			// 퀴즈 실패
			if (quiz.makeQuiz() == 0) {

				// position -1만큼 옮기기
				if (stagePosition > 0) {
					stagePosition--;
					System.out.println("퀴즈 미션에 실패하여 -1 만큼 되돌아갑니다.");
					Thread.sleep(1000);
				}
			}

			Splash.clear();
			Splash.displaySplash(stagePosition);

			// 모든 퀴즈 완료
			if (stagePosition == MAX_QUIZ_NUMBER) {
					recordTime = qthread.getTimeElepsed();
				if (PlayerRank.getInstance().checkRanking(recordTime)) {

					// 랭킹 추가
					String id = "";
					System.out.print("아이디 : ");
					id = new Scanner(System.in).next();

					PlayerInfo pi = new PlayerInfo(id, stage, recordTime);
					PlayerRank.getInstance().insert(pi);
					// 파일 저장
					// PlayerRank.getInstance().saveRanking();

				}
			}
		}

	}

	private static void exerciseGame() throws InterruptedException {
		QuizMain qm = new QuizMain();
		QuizBank quiz = new QuizBank();
		
		outer : while (true) {
			Splash.clear();
			System.out.println("연습모드");
			System.out.println(" 1. 초성낱말퀴즈    2. 인디언포커         3. 행맨 ");
			System.out.println(" 4. 가위바위보      5. 영단어빨리치기     6. 영단어맞추기");
			System.out.println(" 7. GGG             8. HHH                9. III  ");
			System.out.println("10. JJJ            11. KKK               12. LLL");
			System.out.println(" 0. 연습모드 종료");
			System.out.print(">> ");
			
			int select = new Scanner(System.in).nextInt();
			
			switch (select) {
			case 0:
				Splash.clear();
				Splash.displaySplash(1);
				// 랭킹 정보 불러오기
				PlayerRank.getInstance().showRanking();
				break outer;
			case 1:
				quiz.setQuiz(new ChosungQuiz());
				break;	
			case 2:
				quiz.setQuiz(new IndianPoker());
				break;	
			case 3:
				quiz.setQuiz(new HangMan());
				break;	
			case 4:
				quiz.setQuiz(new RspQuiz());
				break;	
			case 5:
				quiz.setQuiz(new WordQuiz());
				break;	
			case 6:
				quiz.setQuiz(new WordPerfect());
				break;	
			case 7:
				quiz.setQuiz(new ChosungQuiz());
				break;	
			case 8:
				quiz.setQuiz(new ChosungQuiz());
				break;	
			case 9:
				quiz.setQuiz(new ChosungQuiz());
				break;	
			case 10:
				quiz.setQuiz(new ChosungQuiz());
				break;	
			case 11:
				quiz.setQuiz(new ChosungQuiz());
				break;	
			case 12:
				quiz.setQuiz(new ChosungQuiz());
				break;	
			default:
				break outer;
			}
			// 퀴즈 story
			quiz.displayStory();

			// 퀴즈 실패
			quiz.makeQuiz();
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
	public void assignQuiz() {
		
		//로또 중복제거
		lottoNumber();

		for (int i=0; i<MAX_QUIZ_NUMBER; i++) {
			
			//퀴즈번호, 퀴즈타입, 퀴즈제한시간, 퀴즈난이도
			QuizInfo qinfo = new QuizInfo(i, lotto[i],0,0);
			arrQuiz.add(qinfo);
		}
		
		//System.out.println(arrQuiz);
	}
	
	public void lottoNumber() {
		
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
	
	public static void showMenu(){
		System.out.println();
		System.out.println("1. 게임 시작");
		System.out.println("2. 게임 연습");
		System.out.println("0. 게임 종료");
		
	}
	
}
