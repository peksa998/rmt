package ludoClient;

import java.io.IOException;

import ludo.Pawn;

public interface ClientThread {

	public static final int REFRESH = 0;
	public static final int GO_START = 100; // LudoStart GO button
	public static final int THROW_DICE = 10;
	public static final int PLAYER_IS_READY = 11;
	public static final int CREATE_ROOM = 101;
	
	public void throwDice() throws IOException;
	public void getOutOfHouse(Pawn pawn);
	public void movePawn(Pawn pawn, int index);
	public void eatPawn(Pawn pawn);	
	
}
