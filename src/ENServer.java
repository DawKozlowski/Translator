

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class ENServer {

    public static void main(String[] args) {

        Map<String, String> translationMap = Map.of(
                "dom","house",
                "kubek", "cup",
                "kawa", "coffee");


        try {
            ServerSocket serverSocket = new ServerSocket(2003);

            while(true) {
                Socket socket = serverSocket.accept();
                new Thread(new ServerHandler(socket, translationMap)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
