package Ex2_corrigé;

public class Test {
    private static final int NUM_PLAYERS = 2; // Nombre de joueurs

    public static void main(String[] args) {
        Game game = new Game();

        for (int i = 1; i <= NUM_PLAYERS; i++) {
            game.addPlayer("Player " + i);
        }

        if (!game.isPlayable()) {
            System.out.println("Le jeu n'est pas jouable avec moins de " + NUM_PLAYERS + " joueurs.");
            return;
        }

        GameLogic gameLogic = new GameLogic(game.getPlayers());

        System.out.println("---- Début du jeu ----");

        while (true) {
            int roll = (int) (Math.random() * 6) + 1;
            System.out.println("\nNouveau tour !");
            System.out.println("Le dé a montré : " + roll);

            boolean isCorrectAnswer = Math.random() < 0.5; // 50% de chances d'une réponse correcte
            if (isCorrectAnswer) {
                System.out.println("Le joueur a répondu correctement !");
                gameLogic.wasCorrectlyAnswered();
            } else {
                System.out.println("Le joueur a répondu incorrectement !");
                gameLogic.wrongAnswer();
            }

            if (gameLogic.didPlayerWin()) {
                System.out.println("\n---- Fin du jeu ----");
                System.out.println("Le joueur gagnant est : " + gameLogic.didPlayerWin());
                break;
            }
        }
    }
}

