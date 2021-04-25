package Comunication;

import java.util.ArrayList;

public class SendMaills extends Message{
    private ArrayList<Message> listOfMails = new ArrayList<>();

    public SendMaills(String alert, String account, String password, boolean status, ArrayList<Message> listOfMails){
        super(alert, account, password,status);
        this.listOfMails = listOfMails;
    }

    public ArrayList<Message> getListOfMails() {
        return listOfMails;
    }
}
