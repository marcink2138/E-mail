package Comunication;

public class LoginRegisterDeleteAccount extends Message {
    private String password;
    public LoginRegisterDeleteAccount(String alert, String account, boolean status, String password) {
        super(alert, account, status);
        this.password = password;
    }
    public String getPassword(){
        return password;
    }
}
