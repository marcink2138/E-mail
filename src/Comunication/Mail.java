package Comunication;

//u want to send mail
public class Mail extends Message{
    private String reciver = null;
    private String sender = null;
    private String title = null;
    private String date = null;
    private int id = 0;


    public Mail(String text, String reciver, String sender, int id, String title, String date) {
        super(text);
        this.reciver = reciver;
        this.sender = sender;
        this.title = title;
        this.date = date;
        this.id = id;
    }

    public void display(){
        System.out.println("Odbiorca: " + reciver + ".");
        System.out.println("Nadawca: " + sender + ".");
        System.out.println("Tytul: " + title + ".");
        System.out.println("Data: " + date + ".");
        System.out.println(getText());
    }

    public String getReciver() {
        return reciver;
    }

    public String getSender() {
        return sender;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }


}
