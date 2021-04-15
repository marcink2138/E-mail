package TEST;

public class Mail extends Message {
    private String title;
    private String sender;
    private String date;
    private String text;
    private int messageId;

    public Mail(String account, String sender, int messageId, String title, String date, String text) {
        super("mess", account);
        this.sender = sender;
        this.messageId = messageId;
        this.title = title;
        this.date = date;
        this.text = text;
    }

    public String getSender() {
        return sender;
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

    public static void main(String args[]) {
        Message test = new Mail("jd", "ab", 2, "wf", "tr", "asdasddsa");

    }

    public void display() {

    }
}
