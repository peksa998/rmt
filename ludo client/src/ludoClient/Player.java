package ludoClient;


public class Player {
	
	private String name;
	private int color;
	private boolean isOnTurn;
	private int playerId;
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	public boolean isOnTurn() {
		return isOnTurn;
	}
	
	public void setOnTurn(boolean isOnTurn) {
		this.isOnTurn = isOnTurn;
	}
	
	public int getPlayerId() {
		return playerId;
	}
	
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	

}
