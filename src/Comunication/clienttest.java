package Comunication;
/*
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class clienttest {
    public static void main(String[] args) throws IOException {
        // need host and port, we want to connect to the ServerSocket at port 7777
        Socket socket = new Socket("localhost", 6666);
        System.out.println("Connected!");

        // get the output stream from the socket.
        OutputStream outputStream = socket.getOutputStream();
        // create an object output stream from the output stream so we can send an object through it
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

        // make a bunch of messages to send.
        ArrayList<Message> maillist = new ArrayList<>();
        maillist.add(new SendMail("mail", "dis", true, "ja", 22, "xd", "jak pan jezus powiedzial", "xd"));
        maillist.add(new SendMail("mail", "dis", true, "ja", 23, "xdd", "jak pan jezus powiedzial", "xd"));
        List<Message> messages = new ArrayList<>();
        messages.add((new GetMailStatus("xd", "xd", true, maillist)));

        System.out.println("Sending messages to the ServerSocket");
        objectOutputStream.writeObject(messages);

        //new SendMail("mail", "dis", true, "ja", 22, "xd", "jak pan jezus powiedzial", "xd")
        System.out.println("Closing socket and terminating program.");
        socket.close();
    }
}
*/