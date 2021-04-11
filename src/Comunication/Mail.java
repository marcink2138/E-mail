package Comunication;

public class Mail {
    private String reciver = null;
    private String sender = null;
    private String title = null;
    private String text = null;
    private String date = null;
    private int id = 0;

    public Mail(String reciver, String sender, int id, String title, String text, String date){
        this.reciver = reciver;
        this.sender = sender;
        this.title = title;
        this.text = text;
        this.date = date;
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
