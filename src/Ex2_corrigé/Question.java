package Ex2_corrigé;

import java.util.LinkedList;

public class Question {
	private LinkedList<String> questions = new LinkedList<>();

	public Question(String category) {
        for (var i = 0; i < 50; i++) {
            questions.addLast(category + " Question " + i);
        }
    }

	public String getNextQuestion() {
        return questions.poll();
	}
}
