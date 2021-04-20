package Comunication;

public class DeleteMail extends Message {
    int messageId;
    public DeleteMail(String alert, String account, boolean status, int messageId) {
        super(alert, account, status);
        this.messageId = messageId;
    }

    public int getMessageId(){
        return messageId;
    }
}
