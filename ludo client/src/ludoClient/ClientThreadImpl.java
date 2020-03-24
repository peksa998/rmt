package ludoClient;

import java.awt.Color;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import ludo.Pawn;

public class ClientThreadImpl extends Thread implements ClientThread {

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
		socket = new Socket(Client.address, port);
	}

	public void clientExecutes() throws IOException {
		switch (sendingCode) {

		case REFRESH:
			
			
			break;

		case CREATE_ROOM:
			createRoom();
			sendingCode = REFRESH;
			break;

		case GO_START:
			go_start();
			sendingCode = REFRESH;
			break;

		case PLAYER_IS_READY:
			playerIsReady();
			sendingCode = REFRESH;
			break;

		case THROW_DICE:
			throwDice();
			sendingCode = REFRESH;
			break;
		
		case SEND_COLOR:
			send_colour();
			sendingCode = REFRESH;
			break;

		default:
			dataOut.writeInt(REFRESH);
			break;
		}
	}
	
	public void go_start() throws IOException {
		
		dataOut.writeInt(GO_START);
		dataOut.writeInt(Client.getRoom());
		
		while (dataIn.available() == 0);
		int rc1 = dataIn.readInt(); // ovo je potencijalni playerID
		
		while (dataIn.available() == 0);
		int rc2 = dataIn.readInt();
		
		if(rc2 == YES_GOOD) {
			Client.firstPage.setVisible(false);
			Client.ludoMain.setVisible(true);
			// rc1 je player id upisi ga negde
			Game.PlayerYou.setPlayerId(rc1);
			return;
		} else {
			
			JOptionPane.showMessageDialog(new JFrame(), "The entered room does not exist or room is full!", "Error",
					JOptionPane.WARNING_MESSAGE);
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
			
			clientExecutes();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void send_colour() throws IOException {

		dataOut.writeInt(SEND_COLOR);
		dataOut.writeInt(Client.getRoom());
		dataOut.writeInt(Game.PlayerYou.getPlayerId());
		dataOut.writeInt(Game.PlayerYou.getColor());
		
	}

	@Override
	public void refresh() throws IOException {
		
		dataOut.writeInt(sendingCode);
		dataOut.writeInt(Client.getRoom());
		refresh_mainManu();
		
	}
	
	public void refresh_mainManu() throws IOException {
		
		while (dataIn.available() == 0);
		int b1 = dataIn.readInt();
		
		while (dataIn.available() == 0);
		int b2 = dataIn.readInt();
		
		while (dataIn.available() == 0);
		int b3 = dataIn.readInt();
		
		while (dataIn.available() == 0);
		int b4 = dataIn.readInt();
		
		
		if(b1 == BLUE || b2 == BLUE || b3 == BLUE || b4 == BLUE) {
			Client.ludoMain.getLblPawnBlue().setIcon(new ImageIcon(LudoMain.class.getResource("/Resource/PawnEdge/pawnBlueEdge.png")));
		}
		System.out.println("plava");
		System.out.println(b1);
		
		if(b1 == RED || b2 == RED || b3 == RED || b4 == RED) {
			Client.ludoMain.getLblPawnRed().setIcon(new ImageIcon(LudoMain.class.getResource("/Resource/PawnEdge/pawnRedEdge.png")));
		}
		
		if(b1 == GREEN || b2 == GREEN || b3 == GREEN || b4 == GREEN) {
			Client.ludoMain.getLblPawnGreen().setIcon(new ImageIcon(LudoMain.class.getResource("/Resource/PawnEdge/pawnGreenEdge.png")));
		}

		if(b1 == YELLOW || b2 == YELLOW || b3 == YELLOW || b4 == YELLOW) {
			Client.ludoMain.getLblPawnYellow().setIcon(new ImageIcon(LudoMain.class.getResource("/Resource/PawnEdge/pawnYellowEdge.png")));
		}
			
	}
}
