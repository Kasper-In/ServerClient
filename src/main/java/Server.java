import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private static final int PORT= 8989;

    public static void main(String[] args){

        try (ServerSocket serverSocket = new ServerSocket(PORT)){
            System.out.println("Сервер стратовал... ");
            List<String> cities = new ArrayList<>();
            while (true){
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
                     ) {
                    if (cities.isEmpty()){
                        out.println("???");
                        String newCity = in.readLine();
                        cities.add(newCity.toLowerCase());
                        out.println("OK");
                    } else {
                        String lastCity = cities.get(cities.size() - 1);
                        char lastLetterCity = lastLetter(lastCity);
                        String newCity;
                        out.println("Послдений город " + lastCity + ". Введите новый город на букву <" + lastLetterCity + ">");
                        do {
                            newCity = in.readLine();
                            if (cities.contains(newCity.toLowerCase())) {
                                out.println("Такой город уже называли. Попробуй еще раз");
                            }
                        } while (cities.contains(newCity.toLowerCase()));
                        char firstLetter = newCity.toLowerCase().charAt(0);
                        if (firstLetter == lastLetterCity) {
                            cities.add(newCity.toLowerCase());
                            out.println("OK");
                        } else {
                            out.println("Не OK");
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            System.out.println(e.getMessage());
        }
    }

    private static char lastLetter(String name){
        String pattern = "ьъы";
        for (int i = name.length()-1; i >= 0 ; i--) {
            char letter = name.toLowerCase().charAt(i);
            if (pattern.indexOf(letter) == -1){
                return letter;
            }
        }
        return name.toLowerCase().charAt(name.length()-1);
    }
}
