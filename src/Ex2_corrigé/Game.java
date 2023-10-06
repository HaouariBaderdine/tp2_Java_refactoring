package Ex2_corrigé;

import java.util.ArrayList;
import java.util.List;

public class Game {
	private List<Player> players;
	private GameLogic gameLogic;

    public Game() {
        this.players = new ArrayList<>();
        this.setGameLogic(null);
    }

    public void addPlayer(String playerName) {
        Player player = new Player(playerName);
        players.add(player);
        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
    }

    public boolean isPlayable() {
        return players.size() >= 2;
    }

	public GameLogic getGameLogic() {
		return gameLogic;
	}

	public void setGameLogic(GameLogic gameLogic) {
		this.gameLogic = gameLogic;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	
	

}
