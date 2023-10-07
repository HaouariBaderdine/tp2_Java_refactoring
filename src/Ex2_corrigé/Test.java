package Ex2_corrigé;

public class Test {
	public static void main(String[] args) {

		Game game = new Game();

		game.addPlayer("Player 1");
		game.addPlayer("Player 2");

		if (!game.isPlayable()) {
			System.out.println("Le jeu n'est pas jouable avec moins de 2 joueurs.");
			return;
		}

		GameLogic gameLogic = new GameLogic(game.getPlayers());

		while (true) {
			int roll = (int) (Math.random() * 6) + 1;
			System.out.println("\nNew turn!");
			gameLogic.roll(roll);

			boolean isCorrectAnswer = Math.random() < 0.5; // 50% de chances d'une réponse correcte
			if (isCorrectAnswer) {
				System.out.println("Player answered correctly!");
				gameLogic.wasCorrectlyAnswered();
			} else {
				System.out.println("Player answered incorrectly!");
				gameLogic.wrongAnswer();
			}

			if (gameLogic.didPlayerWin()) {
				break;
			}
		}
	}
}
