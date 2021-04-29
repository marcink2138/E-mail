package Comunication;

import Server.StreamProcessing;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;

public class Client {
    private Socket socket = null;
    private final int port;
    private final String address;
    private boolean isWorking = false;
    private InterpretterClient interpretterClient;
    private Account account;
    private StreamProcessing streamProcessing;
    private Message messageTosend;

    public Client(int port, String addres) {
        this.port = port;
        this.address = addres;
        this.interpretterClient = new InterpretterClient();
        this.account = new Account(null, null);
    }

    public void openConection() throws IOException {

        this.socket = new Socket(address, port);
        streamProcessing = new StreamProcessing(this.socket);
        this.isWorking = true;


    }

    public void closeConection() throws IOException {
        this.socket.close();
        this.isWorking = false;
    }

    public void send(Message messageTosend) throws IOException {
        if (isWorking) {
            this.messageTosend = messageTosend;
            streamProcessing.sendData(messageTosend);
        }
    }

    public boolean read() throws IOException, ClassNotFoundException {
        if (isWorking) {
            Message recivedMessage = streamProcessing.readData();
            return interpretterClient.Do(recivedMessage, account, messageTosend);
        }
        return false;
    }

    public boolean isWorking() {
        return isWorking;
    }

    public Socket getSocket() {
        return socket;
    }

    public Account getAccount() {
        return account;
    }
/*
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Client client = new Client(6666, "192.168.178.69");
        Message m = new LoginRegisterDeleteAccount("LogIn", "koszkakoszka", true, "marcin1");
        client.openConection();

        client.send(m);
        System.out.println(client.read());
        System.out.println(client.getAccount().getPassword());
        Message c = new Message("SendMails", "koszkakoszka", true);

        client.send(m);
        System.out.println(client.read());
        for (int i = 0; i < client.getAccount().getListOfMails().size(); i++)
            System.out.println(client.getAccount().getListOfMails().get(i).getText());
        client.closeClient();

        client.openConection();
        client.send(m);
        System.out.println(client.read());
        System.out.println(client.getAccount().getPassword());
        m = new LoginRegisterDeleteAccount("Register", "marianczello", true, "kocham psy");

        client.send(m);

    }

*/
}