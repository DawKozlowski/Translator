

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientHandler {

    String command=null;
    String port=null;

    public ClientHandler(String command, String port) {
        this.command = command;
        this.port=port;
    }

    public void sendCommand() {
        try {
            Socket socket = new Socket("localhost", 2000);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String translation(){
        ServerSocket inputSocket = null;
        BufferedReader in=null;
        String respond=null;
        try {
            inputSocket = new ServerSocket(Integer.parseInt(port));
            Socket socket = inputSocket.accept();
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            respond = in.readLine();
            inputSocket.close();
        }catch(IOException e){
            respond = "Wrong port";
        }
        return respond;
    }
}
