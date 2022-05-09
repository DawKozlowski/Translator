

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Handler implements Runnable{

    Map<String, Integer> languageServers = Map.of(
            "PL",2001,
            "DE", 2002,
            "EN", 2003);


    Socket socket;

    public Handler(Socket socket) {
        this.socket = socket;
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
        String code=array[1];
        String port=array[2];


        try {
            int languageServerPort=languageServers.get(code);
            Socket languageServerSocket= new Socket("localhost", languageServerPort);
            PrintWriter outToLanguageServer =new PrintWriter(languageServerSocket.getOutputStream(), true);
            outToLanguageServer.println(word+","+"localhost"+","+port);
            languageServerSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (NullPointerException e){
            Socket toClientSocket= null;
            try {
                toClientSocket = new Socket("localhost", Integer.parseInt(port));
                PrintWriter outToClient =new PrintWriter(toClientSocket.getOutputStream(), true);
                outToClient.println("Wrong language code");
            } catch (IOException ex) {
                e.printStackTrace();
            }
        }
    }



}
