package Comunication;

import java.util.ArrayList;

public class Accaunt {
    private String emailAdress;
    private String password;
    private ArrayList<Mail> listOfMails = new ArrayList<>();

    public Accaunt(String emailAdress, String password){
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
        listOfMails.remove(mail.getId());
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
        Accaunt konto = new Accaunt("jebedisa", "polska");
        Mail mail1 = new Mail("dis to zwierz", "jajkos", "dis", 0, "Polska", "20.20.20");
        konto.addMail(mail1);
        konto.getListOfMails().get(0).display();
    }
}
