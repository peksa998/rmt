package ludo;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class Server {

	private static List<Room> listOfRooms = new LinkedList<>();
	
	private static Game game;
	
	
	public static List<Room> getListOfRooms() {
		return listOfRooms;
	}

	public static void setListOfRooms(List<Room> listOfRooms) {
		Server.listOfRooms = listOfRooms;
	}

	public static Game getGame() {
		return game;
	}

	public static void setGame(Game game) {
		Server.game = game;
	}



	public static void main(String[] args) {
		int port = 9000;
		try (ServerSocket serverSocket = new ServerSocket(port)){
			while(true) {
				Socket socket = serverSocket.accept();
				ServerThreadForClientImpl thread = new ServerThreadForClientImpl(socket);
				thread.start();
				if(game == null)
					game = new Game(1, 1, 1, false);
				else game.setNumberOfPlayers(game.getNumberOfPlayers() + 1);
			}
		}catch(Exception e){
			
		}	
	}
}
