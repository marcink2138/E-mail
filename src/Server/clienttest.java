package Server;

import Comunication.ChangePassword;
import Comunication.LoginRegisterDeleteAccount;
import Comunication.Message;

import java.io.*;
import java.net.Socket;

public class clienttest {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Socket socket = new Socket("192.168.178.69", 6666);
        OutputStream outputStream = socket.getOutputStream();
        InputStream inputStream = socket.getInputStream();
        // create an object output stream from the output stream so we can send an object through it
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        /*
        Message m = new Message("SendMails", "koszkakoszka",true);
        objectOutputStream.writeObject(m);
        Message c = (Message)objectInputStream.readObject();

        for (int i = 0; i < c.getListOfMails().size(); i++)
            System.out.println(c.getListOfMails().get(i).getTitle());
        Message p = new ChangePassword("ChangePassword", "koszkakoszka", true, "polska1", "marcin1");
        objectOutputStream.writeObject(p);
        Message d = (Message) objectInputStream.readObject();
        System.out.println(d.isStatus());

         */

        Message m = new LoginRegisterDeleteAccount("DeleteAccount", "Abc", true,"jdjdjd");
        //while (true) {
            objectOutputStream.writeObject(m);
            Message j = (Message) objectInputStream.readObject();
            System.out.println(j.isStatus());
            Thread.sleep(4000);
        //}
        socket.close();
    }
}
