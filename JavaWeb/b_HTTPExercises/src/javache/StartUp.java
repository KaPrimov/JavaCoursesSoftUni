package javache;

import java.io.IOException;

public class StartUp {

	public static void main(String[] args) {
		Server server = new Server(8123);
		try {
			server.run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
