import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server {

	public static void main(String[] args) throws IOException {
        int port = 9070;
        ServerSocket listener = new ServerSocket(port);
        System.out.println("Server started on 9070");
        try {
            while (true) {
                Socket socket = listener.accept();
                new ServerThread(socket).start();               
            }
        }
        finally {
            listener.close();
        }
    }
	
}