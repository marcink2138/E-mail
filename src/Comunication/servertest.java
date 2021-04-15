package Comunication;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class servertest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
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
    }
}
