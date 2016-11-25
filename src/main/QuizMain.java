package main;

import java.util.Random;

import frame.GameQuiz;
import quiz.RspQuiz;
import quiz.WordPerfect;
import quiz.WordQuiz;
import splash.Splash;

public class QuizMain {
	
	static private int stagePosition= 0;

	public static void main(String[] args) throws InterruptedException {
		
		Splash.displaySplash();
		
		sliceTime();

		
		GameQuiz quiz = new GameQuiz();
		
		//가위바위보 문제
		quiz.setQuiz(new RspQuiz());
		quiz.makeQuiz();
		Splash.clear();
		Splash.displaySplash();
	
		sliceTime();
		
		//단어 빨리치기 게임
		quiz.setQuiz(new WordQuiz());
		quiz.makeQuiz();
		Splash.clear();
		Splash.displaySplash();		
		sliceTime();
		
		//단어 맞추기 게임
		quiz.setQuiz(new WordPerfect());
		quiz.makeQuiz();
		Splash.clear();
		Splash.displaySplash();		
		sliceTime();
		
	}

	static void sliceTime() throws InterruptedException {
		Thread.sleep(1000);
		System.out.println("주사위를 던집니다.");
		for (int i=0;i<30;i++) {
			Thread.sleep(50);
			System.out.print("-");
		}
		System.out.print("> ");
		Random rnd = new Random();
		int result = rnd.nextInt(3)+1;
		stagePosition += result;
		System.out.print(result);
		System.out.println();
		
		Splash.displayStage(stagePosition);
	}
}
