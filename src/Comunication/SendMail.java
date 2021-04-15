package Comunication;

public class SendMail extends Message {
    private String title;
    private String reciver;
    private String date;
    private String text;
    private int messageId;

    public SendMail(String alert, String account, boolean status, String sender, int messageId, String title, String date, String text) {
        super(alert, account, status);
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

}