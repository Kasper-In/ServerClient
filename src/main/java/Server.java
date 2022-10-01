import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final int PORT= 8989;

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(PORT)){
            System.out.println("Сервер стратовал... ");
            while (true){
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
                     ) {
                    String name = in.readLine();
                    out.println("Привет, это сервер. Ты прислал - <<" + name + ">> с потра " + clientSocket.getPort());
                    System.out.println("Сообщение клиенту отправлено");
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
