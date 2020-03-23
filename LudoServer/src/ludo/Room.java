package ludo;

import java.util.Timer;

public class Room {
	private int numberOfPlayers;
	private int roomId;
	private Timer timeInRoom;
	

	public Room(int roomId) {
		super();
		this.roomId = roomId;
	}

	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public Timer getTimeInRoom() {
		return timeInRoom;
	}

	public void setTimeInRoom(Timer timeInRoom) {
		this.timeInRoom = timeInRoom;
	}
	
	
	
	

}
