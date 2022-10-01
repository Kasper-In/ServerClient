import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private static final int PORT = 8989;
    private static final String HOST = "localHost";

    public static void main(String[] args) {

          try (Socket clientSocket = new Socket(HOST, PORT);
               PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
               BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
               ){
                out.println("Привет!");
                System.out.println(in.readLine());
          } catch (IOException e) {
              System.out.println(e.getMessage());
          }
    }
}
