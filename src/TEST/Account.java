package TEST;

import java.util.ArrayList;

public class Account {
    private String emailAdress;
    private String password;
    private ArrayList<Mail> listOfMails = new ArrayList<>();

    public Account(String emailAdress, String password){
        this.emailAdress = emailAdress;
        this.password = password;
    }

    public void display(){

    }

    public void addMail(Mail mail){
        listOfMails.add(mail);
    }

    //czy zamiast tego Mail nie wystarczy int
    public void deleteMail(Mail mail){
        listOfMails.remove(mail.getMessageId());
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Mail> getListOfMails() {
        return listOfMails;
    }

    public static void main(String[] args) {
        Account konto = new Account("jebedisa", "polska");
        Mail mail1 = new Mail("kd", "sad",22,"dasda", "dasads", "dsadsa");
        konto.addMail(mail1);
        konto.getListOfMails().get(0).display();
    }
}