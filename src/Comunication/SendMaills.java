package Comunication;

import java.util.ArrayList;

public class SendMaills extends Message{
    private ArrayList<Message> listOfMails = new ArrayList<>();

    public SendMaills(String alert, String account, boolean status, ArrayList<Message> listOfMails){
        super(alert, account, status);
        this.listOfMails = listOfMails;
    }

    public ArrayList<Message> getListOfMails() {
        return listOfMails;
    }
}
