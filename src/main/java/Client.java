import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String host = "127.0.0.1";
        int port = 8989;

        while (true) {
            try (Socket socket = new Socket(host, port);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
                System.out.println("1. Search\n" + "0. Exit");
                System.out.print("Сhoose an action: ");
                String action = scanner.nextLine();
                if (action.equals("0")) {
                    System.out.println("Thank you for use us program. Goodbye.");
                    break;
                }
                System.out.print("Enter word for search: ");
                String word = scanner.nextLine();
                out.println(word);

                while (true) {
                    String currentLine = in.readLine();
                    if (currentLine == null)
                        break;
                    System.out.println(currentLine);
                }
            }
        }
    }
}