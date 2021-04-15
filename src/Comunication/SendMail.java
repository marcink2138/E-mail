package Comunication;

public class SendMail extends Message{
    private Mail mail;

    public SendMail(String alert, String account, boolean status, Mail mail){
        super(alert, account, status);
        this.mail = mail;
    }

    @Override
    public Mail getMail() {
        return mail;
    }
}
