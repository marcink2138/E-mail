package Comunication;

public class Mail {
    private String title;
    private String reciver;
    private String date;
    private String text;
    private int messageId;

    public Mail(String sender, int messageId, String title, String date, String text) {
        this.reciver = sender;
        this.messageId = messageId;
        this.title = title;
        this.date = date;
        this.text = text;
    }

    public String getReciver() {
        return reciver;
    }

    public int getMessageId() {
        return messageId;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getText() {
        return text;
    }

    public void display() {

    }
}
