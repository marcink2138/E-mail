package Comunication;

public class LoginRegisterDeleteAccount extends Message {
    public LoginRegisterDeleteAccount(String alert, String account, String password, boolean status, String text) {
        super(alert, account, password, status);
    }

}
