package ludoClient;

import java.io.IOException;

public class Client {
	private static boolean startClientThread = false;
	public static LudoStart firstPage = new LudoStart();
	private static int room;
	
	
	public static int getRoom() {
		return room;
	}

	public static void setRoom(int room) {
		Client.room = room;
	}

	public static boolean isStartClientThread() {
		return startClientThread;
	}

	public static void setStartClientThread(boolean startClientThread) {
		Client.startClientThread = startClientThread;
	}


	public static void main(String[] args) {
		firstPage.setVisible(true);
		try {
			while(true) {
				Thread.sleep(500);
				if(startClientThread == true) {
					while(true){
						Thread.sleep(350);
						ClientThreadImpl clientThread = new ClientThreadImpl();
						clientThread.start();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
