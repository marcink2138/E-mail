package Client;

import Comunication.Message;

import java.util.ArrayList;

public class Account {
    private String emailAdress;
    private String password;
    private ArrayList<Message> listOfMails = new ArrayList<>();

    public Account(String emailAdress, String password){
        this.emailAdress = emailAdress;
        this.password = password;
    }

    public void addMail(Message mail){
        listOfMails.add(mail);
    }

    public void deleteMail(Message mail){
        for(int j = (listOfMails.size() - 1); j >= 0; j--){
            if(listOfMails.get(j).getMessageId() == mail.getMessageId()){
                listOfMails.remove(j);
                return;
            }
        }
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Message> getListOfMails() {
        return listOfMails;
    }

    public void clear(){
        this.listOfMails.clear();
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setListOfMails(ArrayList<Message> listOfMails) {
        this.listOfMails = listOfMails;
    }
}
