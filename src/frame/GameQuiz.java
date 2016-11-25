package frame;

public class GameQuiz {

	Quiz quiz;
	
	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}
	
	public int makeQuiz() {
		return quiz.make();

	}
	
	
}
