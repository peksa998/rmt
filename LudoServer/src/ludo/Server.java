package ludo;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class Server {

	private static List<Room> listOfRooms = new LinkedList<>();
	private static List<Game> Games = new LinkedList<>();
	
	public static List<Room> getListOfRooms() {
		return listOfRooms;
	}

	public static void setListOfRooms(List<Room> listOfRooms) {
		Server.listOfRooms = listOfRooms;
	}

	public static List<Game> getGames() {
		return Games;
	}

	public static void setGames(List<Game> games) {
		Games = games;
	}
	

	public static void main(String[] args) {
		int port = 9000;
		try (ServerSocket serverSocket = new ServerSocket(port)){
			while(true) {
				Socket socket = serverSocket.accept();
				ServerThreadForClientImpl thread = new ServerThreadForClientImpl(socket);
				thread.start();
			}
		}catch(Exception e){
			
		}	
	}
}
