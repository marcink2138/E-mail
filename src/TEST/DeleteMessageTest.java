package TEST;

public class DeleteMessageTest extends Message_test {
    int messageId;
    public DeleteMessageTest(String alert, String account, int messageId) {
        super(alert, account);
        this.messageId = messageId;
    }

    public int getMessageId(){
        return messageId;
    }
}
