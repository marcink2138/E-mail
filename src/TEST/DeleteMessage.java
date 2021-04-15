package TEST;

public class DeleteMessage extends Message {
    int messageId;
    public DeleteMessage(String alert, String account, int messageId) {
        super(alert, account);
        this.messageId = messageId;
    }

    public int getMessageId(){
        return messageId;
    }
}
