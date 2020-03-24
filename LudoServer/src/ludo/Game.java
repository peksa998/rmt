package ludo;

import java.util.LinkedList;
import java.util.List;

import javax.management.timer.*; 

public class Game {

	private int round;
	private int whichPlayerIsOnTurn;
	private boolean isEnd;
	private Timer timerInGame;
	private Timer timeInLobby;
	private Room room;
	
	private static List<Player> Players = new LinkedList<>();
	
	
	public Game(Room room, Player player) {
		super();
		this.room = room;
		Game.Players.add(player);
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

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

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
	
	
	
	
}
