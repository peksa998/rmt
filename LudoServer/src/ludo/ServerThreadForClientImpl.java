package ludo;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ServerThreadForClientImpl extends Thread implements ServerThreadForClient{

	private Socket socket;
	private DataOutputStream dataOut;
	private DataInputStream dataIn;
	private static PrintStream textOut;
	private static BufferedReader textIn;
	private byte[] buffer = new byte[4096];
	private int counter = 0;
	private int round = 1;
	private Player player;
	
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	private int receivedCode = 0;
	
	public ServerThreadForClientImpl(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void throwDice() throws IOException {
		int numberOnDice = (int)(Math.random()*6 + 1);
		dataOut.writeInt(numberOnDice);
	}

	@Override
	public void getOutOfHouse(Pawn pawn) {
		
		
		
	}

	@Override
	public void movePawn(Pawn pawn, int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eatPawn(Pawn pawn) {
		// TODO Auto-generated method stub
		
	}
	
	public void serverExecutes() throws IOException {
		
		while (dataIn.available() == 0);
		receivedCode = dataIn.readInt();
		
		switch(receivedCode) {
		case REFRESH:
			refresh();
			break;
			
		case THROW_DICE:
			throwDice();
			receivedCode = 0;
			break;
			
		case GO_START:
			////////////////
			go_Start();
			receivedCode = 0;
			break;
			
		case CREATE_ROOM:
			createRoom();
			receivedCode = 0;
			break;
			
		case SEND_COLOR:
			send_colour();
			receivedCode = 0;
			break;
			
		default:
			receivedCode = 0;
			break;
		}
	}


	private void refresh() throws IOException {
		
		while (dataIn.available() == 0);
		int room = dataIn.readInt();
		
		refresh_mainManu(room);
		
	}

	private void refresh_mainManu(int room) throws IOException {
		
		Player p1 = findPlayer(room, 1);
		Player p2 = findPlayer(room, 2);
		Player p3 = findPlayer(room, 2);
		Player p4 = findPlayer(room, 4);
		
		int b1 = p1.getColor();
		int b2 = p2.getColor();
		int b3 = p3.getColor();
		int b4 = p4.getColor();
		
		dataOut.writeInt(b1);
		dataOut.writeInt(b2);
		dataOut.writeInt(b3);
		dataOut.writeInt(b4);
	}

	@Override
	public void go_Start() throws IOException {
		
		while (dataIn.available() == 0);
		int room = dataIn.readInt();

		
			
		for(int i=0; i<Server.getListOfRooms().size(); i++){ 		// prolaz kroz listu
			if(Server.getListOfRooms().get(i).getRoomId() == room) {
				
				if(Server.getListOfRooms().get(i).getNumberOfPlayers() < 4) {
					if(Server.getListOfRooms().get(i).getNumberOfPlayers() == 0) {
						Server.getGames().add(new Game(Server.getListOfRooms().get(i), new Player(Server.getListOfRooms().get(i).getNumberOfPlayers() + 1)));
						
						dataOut.writeInt(Server.getListOfRooms().get(i).getNumberOfPlayers() + 1);
					}
					
					for(int j=0; j < Server.getGames().size(); j++){
						
						if(Server.getGames().get(j).getRoom().getRoomId() == room) {
							Server.getGames().get(j).getPlayers().add(new Player(Server.getListOfRooms().get(i).getNumberOfPlayers() + 1));
							
							dataOut.writeInt(Server.getListOfRooms().get(i).getNumberOfPlayers() + 1);
							break;
						}
					}
					
					Server.getListOfRooms().get(i).setNumberOfPlayers(Server.getListOfRooms().get(i).getNumberOfPlayers() + 1);
					dataOut.writeInt(YES_GOOD);
				} else {
					dataOut.writeInt(NO_ERROR);
				}
				
			} else {
				dataOut.writeInt(NO_ERROR);		
			}
		}	
	}

	@Override
	public void createRoom() throws IOException {
		int numberOfRoom;
		do {
			 numberOfRoom =	(int)(Math.random() * ((999 - 100) + 1)) + 100;
			 
		}
		while(Server.getListOfRooms().contains(new Room(numberOfRoom)));
		Server.getListOfRooms().add(new Room(numberOfRoom));

		dataOut.writeInt(numberOfRoom);
	}

	@Override
	public void run() {
		try {
			dataOut = new DataOutputStream(socket.getOutputStream());
			dataIn = new DataInputStream(socket.getInputStream());
			textIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			textOut = new PrintStream(socket.getOutputStream());
			
			serverExecutes();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void send_colour() throws IOException {
		
		while (dataIn.available() == 0);
		int room = dataIn.readInt();
		
		while (dataIn.available() == 0);
		int playerID = dataIn.readInt();

		while (dataIn.available() == 0);
		int colorOfPlayer = dataIn.readInt();

		Player p = findPlayer(room, playerID);
		p.setColor(colorOfPlayer);
		System.out.println(colorOfPlayer); ////// obrisi

		
		
	}
	
	public Player findPlayer(int room, int id) {
		
		for(int j=0; j < Server.getGames().size(); j++){
			if(Server.getGames().get(j).getRoom().getRoomId() == room) {
				
				for(int i=0; i< Server.getGames().get(j).getPlayers().size(); i++){
					if(Server.getGames().get(j).getPlayers().get(i).getPlayerId() == id) {
						
						return Game.getPlayers().get(i);
					}
				}
					
			}
		}
		return player;
	}
	
}
