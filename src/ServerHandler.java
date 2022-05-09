

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ServerHandler implements Runnable {


    Map<String, String> translationMap;

    Socket socket;

    public ServerHandler(Socket socket, Map<String, String> translationMap) {
        this.socket = socket;
        this.translationMap=translationMap;
    }

    public void run() {
        String command=null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            out =new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            command = in.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] array = command.split(",");
        String word=array[0];
        String ipAddress=array[1];
        String port=array[2];



        try {
            String translatedWord = translationMap.get(word);
            if(translatedWord.equals(null)){
                throw new NullPointerException();
            }
            Socket clientSocket= new Socket(ipAddress, Integer.parseInt(port));
            PrintWriter outToClient =new PrintWriter(clientSocket.getOutputStream(), true);
            outToClient.println(translatedWord);
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }catch(NullPointerException e){
            Socket clientSocket= null;
            try {
                clientSocket = new Socket(ipAddress, Integer.parseInt(port));
                PrintWriter outToClient =new PrintWriter(clientSocket.getOutputStream(), true);
                outToClient.println("Word translation not supported");
                clientSocket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
