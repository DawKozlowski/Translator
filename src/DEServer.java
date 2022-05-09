

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class DEServer {

    public static void main(String[] args) {

        Map<String, String> translationMap = Map.of(
                "dom","haus",
                "kubek", "Becher",
                "kawa", "Kaffee");


        try {
            ServerSocket serverSocket = new ServerSocket(2002);

            while(true) {
                Socket socket = serverSocket.accept();
                new Thread(new ServerHandler(socket, translationMap)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
