package ludoClient;

import java.util.LinkedList;
import java.util.List;

import javax.management.timer.Timer;



public class Game {
	
	private static List<Player> Players = new LinkedList<>();
	
	static Player PlayerYou = new Player();
	
	private int round;
	private int whichPlayerIsOnTurn;
	private boolean isEnd;
	private Timer timerInGame;
	private Timer timeInLobby;
	private int room;
	
	public static List<Player> getPlayers() {
		return Players;
	}
	
	public static void setPlayers(List<Player> players) {
		Players = players;
	}
	
	public int getRound() {
		return round;
	}
	
	public void setRound(int round) {
		this.round = round;
	}
	
	public int getWhichPlayerIsOnTurn() {
		return whichPlayerIsOnTurn;
	}
	
	public void setWhichPlayerIsOnTurn(int whichPlayerIsOnTurn) {
		this.whichPlayerIsOnTurn = whichPlayerIsOnTurn;
	}
	
	public boolean isEnd() {
		return isEnd;
	}
	
	public void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}
	
	public Timer getTimerInGame() {
		return timerInGame;
	}
	
	public void setTimerInGame(Timer timerInGame) {
		this.timerInGame = timerInGame;
	}
	
	public Timer getTimeInLobby() {
		return timeInLobby;
	}
	
	public void setTimeInLobby(Timer timeInLobby) {
		this.timeInLobby = timeInLobby;
	}
	
	public int getRoom() {
		return room;
	}
	
	public void setRoom(int room) {
		this.room = room;
	}
	
	

}
