package Comunication;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server extends Thread{
    private Socket socket = null;
    private ServerSocket serverSocket = null;
    private ObjectInputStream objectInputStream = null;
    private boolean isWorking = false;
    private InterpretterServer interpretterServer;
    private ObjectOutputStream objectOutputStream = null;

    public Server(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        this.objectInputStream = new ObjectInputStream(inputStream);
        this.isWorking = true;
    }

    public void closeServer() throws IOException {
        serverSocket.close();
        socket.close();
        this.isWorking = false;
    }

    public void listen() throws IOException, ClassNotFoundException {
        Message message = (Message) objectInputStream.readObject();
        OutputStream outputStream = socket.getOutputStream();
        this.objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(message);
        System.out.println(message.getCommand());
    }

    public boolean isWorking() {
        return isWorking;
    }

    public Socket getSocket() {
        return socket;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public ObjectInputStream getObjectInputStream() {
        return objectInputStream;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Server server = new Server(6666);
        server.listen();
        server.closeServer();

    }

    /*public static void main(String[] args) throws IOException, ClassNotFoundException {
        // don't need to specify a hostname, it will be the current machine
        ServerSocket ss = new ServerSocket(6666);
        System.out.println("ServerSocket awaiting connections...");
        Socket socket = ss.accept(); // blocking call, this will wait until a connection is attempted on this port.
        System.out.println("Connection from " + socket + "!");

        // get the input stream from the connected socket
        InputStream inputStream = socket.getInputStream();
        // create a DataInputStream so we can read data from it.
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

        // read the list of messages from the socket
        List<Message> listOfMessages = (List<Message>) objectInputStream.readObject();
        //System.out.println("Received [" + listOfMessages.size() + "] messages from: " + socket);
        // print out the text of every message
        //System.out.println("All messages:");
        //listOfMessages.forEach((msg)-> System.out.println(msg.getAlert()));

        System.out.println("Closing sockets.");

        System.out.println(listOfMessages.get(0).getListOfMails().get(1).getTitle());
        ss.close();
        socket.close();
    }*/
}
