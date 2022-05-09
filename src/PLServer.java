

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class PLServer {

    public static void main(String[] args) {

        Map<String, String> translationMap = Map.of(
                "dom","dom",
                "kubek", "kubek",
                "kawa", "kawa");


        try {
            ServerSocket serverSocket = new ServerSocket(2001);

            while(true) {
                Socket socket = serverSocket.accept();
                new Thread(new ServerHandler(socket, translationMap)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
