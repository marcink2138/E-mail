package Comunication;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client {
    private Socket socket = null;
    private ObjectOutputStream objectOutputStream = null;
    private boolean isWorking = false;

    public Client(int port, String addres) throws IOException {
        this.socket = new Socket(addres, port);
        OutputStream outputStream = socket.getOutputStream();
        this.objectOutputStream = new ObjectOutputStream(outputStream);
        this.isWorking = true;
    }

    public void closeClient() throws IOException {
        this.socket.close();
        this.isWorking = false;
    }

    public void send(Message message) throws IOException {
        objectOutputStream.writeObject(message);
    }

    public void read(){

    }

    public boolean isWorking() {
        return isWorking;
    }

    public Socket getSocket() {
        return socket;
    }

    public ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }

    public static void main(String[] args) throws IOException{
        Client client = new Client(6666, "localhost");
        ArrayList<Message> maillist = new ArrayList<>();
        maillist.add(new Mail("mail", "dis", true, "ja", 22, "xd", "jak pan jezus powiedzial", "xd"));
        maillist.add(new Mail("mail", "dis", true, "ja", 23, "xdd", "jak pan jezus powiedzial", "xd"));
        SendMaills mail = new SendMaills("dis", "papiez", true, maillist);
        client.send(mail);
        client.closeClient();

    }

    /*public static void main(String[] args) throws IOException {
        // need host and port, we want to connect to the ServerSocket at port 7777
        Socket socket = new Socket("localhost", 6666);
        System.out.println("Connected!");

        // get the output stream from the socket.
        OutputStream outputStream = socket.getOutputStream();
        // create an object output stream from the output stream so we can send an object through it
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

        // make a bunch of messages to send.
        ArrayList<Message> maillist = new ArrayList<>();
        maillist.add(new Mail("mail", "dis", true, "ja", 22, "xd", "jak pan jezus powiedzial", "xd"));
        maillist.add(new Mail("mail", "dis", true, "ja", 23, "xdd", "jak pan jezus powiedzial", "xd"));
        List<Message> messages = new ArrayList<>();
        messages.add((new SendMaills("xd", "xd", true, maillist)));

        System.out.println("Sending messages to the ServerSocket");
        objectOutputStream.writeObject(messages);

        System.out.println("Closing socket and terminating program.");
        socket.close();
    }
*/
}