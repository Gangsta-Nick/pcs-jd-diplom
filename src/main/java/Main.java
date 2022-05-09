import com.google.gson.*;

import java.io.*;
import java.net.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BooleanSearchEngine engine = new BooleanSearchEngine(new File("pdfs"));
        int port = 8989;

        while (true) {
            System.out.println("Starting server at " + port);
            try (ServerSocket serverSocket = new ServerSocket(port);
                 Socket clientSocket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                String word = in.readLine();

                List<PageEntry> search = engine.search(word);
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.setPrettyPrinting().create();
                Collections.sort(search);
                out.println("{" + word + "} -> ");
                for (PageEntry pageEntry : search) {
                    out.println(gson.toJson(pageEntry));
                }
            }
        }
    }
}