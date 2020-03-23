package ludoClient;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

import ludo.Pawn;

public class ClientThreadImpl extends Thread implements ClientThread {

	private static String address = "localhost";
	private int port = 9000;
	private Socket socket;
	private static DataOutputStream dataOut;
	private static DataInputStream dataIn;
	private static PrintStream textOut;
	private static BufferedReader textIn;
	private static String nameOfPlayer;
	private static String colorOfPlayer;
	private static int sendingCode = 0; // everything OK
	private static int numberOnDice;

	public static int getSendingCode() {
		return sendingCode;
	}

	public static void setSendingCode(int sendingCode) {
		ClientThreadImpl.sendingCode = sendingCode;
	}

	public static String getAddress() {
		return address;
	}

	public static void setAddress(String address) {
		ClientThreadImpl.address = address;
	}

	public static String getNameOfPlayer() {
		return nameOfPlayer;
	}

	public static void setNameOfPlayer(String nameOfPlayer) {
		ClientThreadImpl.nameOfPlayer = nameOfPlayer;
	}

	public static String getColorOfPlayer() {
		return colorOfPlayer;
	}

	public static void setColorOfPlayer(String colorOfPlayer) {
		ClientThreadImpl.colorOfPlayer = colorOfPlayer;
	}

	public ClientThreadImpl() throws UnknownHostException, IOException {
		socket = new Socket(address, port);
	}

	public void clientExecutes() throws IOException {
		switch (sendingCode) {

		case REFRESH:
			dataOut.writeInt(sendingCode);
			break;

		case CREATE_ROOM:
			createRoom();
			sendingCode = REFRESH;

			break;

		case GO_START:
			////////////////
			break;

		case PLAYER_IS_READY:
			playerIsReady();
			sendingCode = REFRESH;
			break;

		case THROW_DICE:
			throwDice();
			sendingCode = REFRESH;
			break;

		default:
			dataOut.writeInt(REFRESH);
			break;
		}
	}

	private void createRoom() throws IOException {
		dataOut.writeInt(CREATE_ROOM);
		
		while (dataIn.available() == 0);
		int room = dataIn.readInt();
		Client.setRoom(room);
	}

	private void playerIsReady() throws IOException {
		dataOut.writeInt(PLAYER_IS_READY);
		textOut.println(nameOfPlayer);
		textOut.println(colorOfPlayer);
	}

	public void throwDice() throws IOException {
		dataOut.writeInt(THROW_DICE);
		
		while (dataIn.available() == 0);
		numberOnDice = dataIn.readInt();
	}

	@Override
	public void getOutOfHouse(Pawn pawn) {
		// TODO Auto-generated method stub

	}

	@Override
	public void movePawn(Pawn pawn, int index) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eatPawn(Pawn pawn) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		try {
			dataIn = new DataInputStream(socket.getInputStream());
			dataOut = new DataOutputStream(socket.getOutputStream());
			textIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			textOut = new PrintStream(socket.getOutputStream());
			System.out.println("proba");
			clientExecutes();
			System.out.println(numberOnDice);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
