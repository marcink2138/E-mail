package Server;

import Comunication.Message;

import java.io.*;
import java.net.Socket;

public class StreamProcessing {
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private InputStream inputStream;
    private OutputStream outputStream;
    public StreamProcessing(Socket socket) throws IOException {
        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();
        outputStream.flush();
        objectInputStream = new ObjectInputStream(inputStream);
        objectOutputStream = new ObjectOutputStream(outputStream);
    }

    public Message readData() throws IOException, ClassNotFoundException {
        return (Message) objectInputStream.readObject();
    }

    public void sendData(Message message) throws IOException {
        objectOutputStream.writeObject(message);
    }
}