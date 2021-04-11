package Comunication;

public class Alert {
    private String reciver = null;
    private String sender = null;
    private String title = null;
    private String text = null;
    private String date = null;
    private int whatToDo = 0;

    //klasyczna wiadomosc email
    public Alert(String reciver, String sender, int What, String title, String text, String date){
        this.reciver = reciver;
        this.sender = sender;
        this.title = title;
        this.text = text;
        this.date = date;
        this.whatToDo = What;
    }

    //server zrobi cos w zaleznosci od what np 1 = utworz konto 2 = usun konto 3 = zaloguj 4 = daj liste emaili 5 = ...
    public Alert(String reciver, String sender, int What){
        this.reciver = reciver;
        this.sender = sender;
        this.whatToDo = What;
    }



    public void display(){

    }

    public String getReciver() {
        return reciver;
    }

    public String getSender() {
        return sender;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getDate() {
        return date;
    }

    public int getWhatToDo() {
        return whatToDo;
    }
}
