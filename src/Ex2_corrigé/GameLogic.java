package Ex2_corrigé;

import java.util.List;

public class GameLogic {

	private List<Player> players;
	private Question currentQuestion;
	private int currentPlayerIndex;

	public GameLogic(List<Player> players) {
		this.players = players;
		this.currentQuestion = null;
		this.currentPlayerIndex = 0;
	}

	public void roll(int roll) {
		Player currentPlayer = players.get(currentPlayerIndex);
		System.out.println(currentPlayer.getName() + " is the current player");
		System.out.println("They have rolled a " + roll);

		if (currentPlayer.isInPenaltyBox()) {
			if (roll % 2 != 0) {
				System.out.println(currentPlayer.getName() + " is getting out of the penalty box");
				currentPlayer.setPosition(currentPlayer.getPosition() + roll);
				if (currentPlayer.getPosition() > 11)
					currentPlayer.setPosition(currentPlayer.getPosition() - 12);
				System.out.println(currentPlayer.getName() + "'s new location is " + currentPlayer.getPosition());
				askQuestion();
			} else {
				System.out.println(currentPlayer.getName() + " is not getting out of the penalty box");
			}
		} else {
			currentPlayer.setPosition(currentPlayer.getPosition() + roll);
			if (currentPlayer.getPosition() > 11)
				currentPlayer.setPosition(currentPlayer.getPosition() - 12);
			System.out.println(currentPlayer.getName() + "'s new location is " + currentPlayer.getPosition());
			askQuestion();
		}
	}

	public boolean wasCorrectlyAnswered() {
		Player currentPlayer = players.get(currentPlayerIndex);
		if (currentPlayer.isInPenaltyBox()) {
			if (currentPlayer.getPosition() % 2 != 0) {
				System.out.println("Answer was correct!!!!");
				currentPlayer.increaseScore();
				System.out.println(currentPlayer.getName() + " now has " + currentPlayer.getScore() + " Gold Coins.");
				boolean winner = didPlayerWin();
				currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
				return winner;
			}
		} else {
			System.out.println("Answer was correct!!!!");
			currentPlayer.increaseScore();
			System.out.println(currentPlayer.getName() + " now has " + currentPlayer.getScore() + " Gold Coins.");
			boolean winner = didPlayerWin();
			currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
			return winner;
		}
		return false;
	}

	public boolean wrongAnswer() {
		Player currentPlayer = players.get(currentPlayerIndex);
		System.out.println("Question was incorrectly answered");
		System.out.println(currentPlayer.getName() + " was sent to the penalty box");
		currentPlayer.setInPenaltyBox(true);
		currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
		return true;
	}

	private void askQuestion() {
		Player currentPlayer = players.get(currentPlayerIndex);
		String category = getCurrentCategory(currentPlayer.getPosition());
		currentQuestion = new Question(category);
		System.out.println("The category is " + category);
		System.out.println(currentQuestion.getNextQuestion());
	}

	private String getCurrentCategory(int position) {
		if (position == 0 || position == 4 || position == 8)
			return "Pop";
		if (position == 1 || position == 5 || position == 9)
			return "Science";
		if (position == 2 || position == 6 || position == 10)
			return "Sports";
		return "Rock";
	}

	public boolean didPlayerWin() {
		for (Player player : players) {
			if (player.getScore() == 6) {
				System.out.println(player.getName() + " wins with " + player.getScore() + " Gold Coins!");
				return true;
			}
		}
		return false;
	}

}
