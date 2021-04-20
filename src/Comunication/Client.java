package Comunication;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client {
    private Socket socket = null;
    private ObjectOutputStream objectOutputStream = null;
    private boolean isWorking = false;
    private InterpretterClient interpretterClient = null;
    private ObjectInputStream objectInputStream= null;
    private Account account = null;

    public Client(int port, String addres) throws IOException {
        this.socket = new Socket(addres, port);
        OutputStream outputStream = socket.getOutputStream();
        this.objectOutputStream = new ObjectOutputStream(outputStream);
        this.isWorking = true;
        this.interpretterClient = new InterpretterClient();
        this.account = new Account("Brak", "Brak");
    }

    public void closeClient() throws IOException {
        this.socket.close();
        this.isWorking = false;
    }

    public void send(Message message) throws IOException, ClassNotFoundException {
        objectOutputStream.writeObject(message);
        read();
        closeClient();
    }

    public void read() throws IOException, ClassNotFoundException {
        InputStream inputStream = socket.getInputStream();
        this.objectInputStream = new ObjectInputStream(inputStream);
        Message message = (Message) objectInputStream.readObject();
        interpretterClient.Do(message, account);
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

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Client client = new Client(6666, "localhost");
        ArrayList<Message> maillist = new ArrayList<>();
        maillist.add(new Mail("mail", "dis", true, "ja", 22, "xd", "jak pan jezus powiedzial", "xd"));
        maillist.add(new Mail("mail", "dis", true, "ja", 23, "xdd", "jak pan jezus powiedzial", "xd"));
        SendMaills mail = new SendMaills("Register", "papiez", true, maillist);
        client.send(mail);

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