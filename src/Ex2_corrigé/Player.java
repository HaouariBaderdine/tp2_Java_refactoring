package Ex2_corrigé;

public class Player {
	private String name;
	private int position;
	private int score;
	private boolean inPenaltyBox;

	public Player(String name) {
		this.name = name;
		this.position = 0;
		this.score = 0;
		this.inPenaltyBox = false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void increaseScore() {
		this.score++;
	}

	public boolean isInPenaltyBox() {
		return inPenaltyBox;
	}

	public void setInPenaltyBox(boolean inPenaltyBox) {
		this.inPenaltyBox = inPenaltyBox;
	}

}
