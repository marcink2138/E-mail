package Comunication;

public class ChangePassword extends Message{
    private String password;
    private String newpassword;

    public ChangePassword(String alert, String account, boolean status, String password, String newpassword) {
        super(alert, account, status);
        this.password = password;
        this.newpassword = newpassword;
    }
}
