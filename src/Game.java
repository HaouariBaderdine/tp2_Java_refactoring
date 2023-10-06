import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
	private ArrayList<String> _players = new ArrayList<String>();
	private int[] _places = new int[6];
	private int[] _purses = new int[6];
	private boolean[] _inPenaltyBox = new boolean[6];
	private LinkedList<String> _popQuestions = new LinkedList<String>();
	private LinkedList<String> _scienceQuestions = new LinkedList<String>();
	private LinkedList<String> _sportsQuestions = new LinkedList<String>();
	private LinkedList<String> _rockQuestions = new LinkedList<String>();
	private int _currentPlayer;
	private boolean _isGettingOutOfPenaltyBox;

	public Game() {
		for (var i = 0; i < 50; i++) {
			_popQuestions.addLast("Pop Question " + i);
			_scienceQuestions.addLast(("Science Question " + i));
			_sportsQuestions.addLast(("Sports Question " + i));
			_rockQuestions.addLast(CreateRockQuestion(i));
		}
	}

	public String CreateRockQuestion(int index) {
		return "Rock Question " + index;
	}

	public boolean IsPlayable() {
		return (HowManyPlayers() >= 2);
	}

	public boolean Add(String playerName) {
		_players.add(playerName);
		_places[HowManyPlayers()] = 0;
		_purses[HowManyPlayers()] = 0;
		_inPenaltyBox[HowManyPlayers()] = false;
		System.out.println(playerName + " was added");
		System.out.println("They are player number " + _players.size());
		return true;
	}

	public int HowManyPlayers() {
		return _players.size();
	}

	public void Roll(int roll) {
		System.out.println(_players.get(_currentPlayer) + " is the current player");
		System.out.println("They have rolled a " + roll);
		if (_inPenaltyBox[_currentPlayer]) {
			if (roll % 2 != 0) {
				_isGettingOutOfPenaltyBox = true;
				System.out.println(_players.get(_currentPlayer) + " is getting out of the penalty box");
				_places[_currentPlayer] = _places[_currentPlayer] + roll;
				if (_places[_currentPlayer] > 11)
					_places[_currentPlayer] = _places[_currentPlayer] - 12;
				System.out.println(_players.get(_currentPlayer) + "'s new location is " + _places[_currentPlayer]);
				System.out.println("The category is " + CurrentCategory());
				AskQuestion();
			} else {
				System.out.println(_players.get(_currentPlayer) + " is not getting out of the penalty box");
				_isGettingOutOfPenaltyBox = false;
			}
		} else {
			_places[_currentPlayer] = _places[_currentPlayer] + roll;
			if (_places[_currentPlayer] > 11)
				_places[_currentPlayer] = _places[_currentPlayer] - 12;
			System.out.println(_players.get(_currentPlayer) + "'s new location is " + _places[_currentPlayer]);
			System.out.println("The category is " + CurrentCategory());
			AskQuestion();
		}
	}

	private void AskQuestion() {
		if (CurrentCategory() == "Pop") {
			System.out.println(_popQuestions.getFirst());
			_popQuestions.removeFirst();
		}
		if (CurrentCategory() == "Science") {
			System.out.println(_scienceQuestions.getFirst());
			_scienceQuestions.removeFirst();
		}
		if (CurrentCategory() == "Sports") {
			System.out.println(_sportsQuestions.getFirst());
			_sportsQuestions.removeFirst();
		}
		if (CurrentCategory() == "Rock") {
			System.out.println(_rockQuestions.getFirst());
			_rockQuestions.removeFirst();
		}
	}

	private String CurrentCategory() {
		if (_places[_currentPlayer] == 0)
			return "Pop";
		if (_places[_currentPlayer] == 4)
			return "Pop";
		if (_places[_currentPlayer] == 8)
			return "Pop";
		if (_places[_currentPlayer] == 1)
			return "Science";
		if (_places[_currentPlayer] == 5)
			return "Science";
		if (_places[_currentPlayer] == 9)
			return "Science";
		if (_places[_currentPlayer] == 2)
			return "Sports";
		if (_places[_currentPlayer] == 6)
			return "Sports";
		if (_places[_currentPlayer] == 10)
			return "Sports";
		return "Rock";
	}

	public boolean WasCorrectlyAnswered() {
		if (_inPenaltyBox[_currentPlayer]) {
			if (_isGettingOutOfPenaltyBox) {
				System.out.println("Answer was correct!!!!");
				_purses[_currentPlayer]++;
				System.out
						.println(_players.get(_currentPlayer) + " now has " + _purses[_currentPlayer] + " Gold Coins.");
				var winner = DidPlayerWin();
				_currentPlayer++;
				if (_currentPlayer == _players.size())
					_currentPlayer = 0;
				return winner;
			} else {
				_currentPlayer++;
				if (_currentPlayer == _players.size())
					_currentPlayer = 0;
				return true;
			}
		} else {
			System.out.println("Answer was corrent!!!!");
			_purses[_currentPlayer]++;
			System.out.println(_players.get(_currentPlayer) + " now has " + _purses[_currentPlayer] + " Gold Coins.");
			var winner = DidPlayerWin();
			_currentPlayer++;
			if (_currentPlayer == _players.size())
				_currentPlayer = 0;
			return winner;
		}
	}

	public boolean WrongAnswer() {
		System.out.println("Question was incorrectly answered");
		System.out.println(_players.get(_currentPlayer) + " was sent to the penalty box");
		_inPenaltyBox[_currentPlayer] = true;
		_currentPlayer++;
		if (_currentPlayer == _players.size())
			_currentPlayer = 0;
		return true;
	}

	private boolean DidPlayerWin() {
		return !(_purses[_currentPlayer] == 6);
	}
}