package Comunication;

import java.util.ArrayList;

public class GetMailStatus extends Message{
    private ArrayList<Message> listOfMails = new ArrayList<>();

    public GetMailStatus(String alert, String account, boolean status, ArrayList<Message> listOfMails){
        super(alert, account, status);
        this.listOfMails = listOfMails;
    }

    public ArrayList<Message> getListOfMails() {
        return listOfMails;
    }
}
