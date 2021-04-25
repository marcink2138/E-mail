package Server;

import Comunication.Message;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

public class ClientHandling extends Thread{
    private Socket socket;
    private InterpretterServer interpretterServer;
    private StreamProcessing streamProcessing;
    private Message incomingMessage;
    private Message outcomingMessage;
    public ClientHandling(Socket socket) throws SQLException {
        this.socket = socket;
        interpretterServer = new InterpretterServer();
    }
    public void run(){
        try {
            streamProcessing = new StreamProcessing(this.socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true){
            try {
                incomingMessage = streamProcessing.readData();
                outcomingMessage = interpretterServer.Do(incomingMessage);
                streamProcessing.sendData(outcomingMessage);
            } catch (IOException | ClassNotFoundException | SQLException e) {
                try {
                    socket.close();
                } catch (IOException ioException) {
                    System.out.println("Client " + socket.getRemoteSocketAddress() + "Zerwal polaczenie");
                }
                System.out.println("Zakonczono polaczenie z " + socket.getRemoteSocketAddress());
                return;
            }
        }
    }

}
