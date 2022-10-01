import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private static final int PORT = 8989;
    private static final String HOST = "localHost";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try (Socket clientSocket = new Socket(HOST, PORT);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
             ){
             String serverStart = in.readLine();
             if (serverStart.equals("???")){
                System.out.println("Игра началась. Введите первый город: ");
             } else {
                 System.out.println(serverStart);
             }
             String serverAnswer;
              do {
                 String city = sc.nextLine();
                 out.println(city);
                 serverAnswer = in.readLine();
                 System.out.println(serverAnswer);
             } while (!serverAnswer.equals("OK") && !serverAnswer.equals("Не OK"));
        } catch (IOException e) {
              System.out.println(e.getMessage());
        }
    }
}
