package Comunication;

public class Login_register extends Message {
    private String password;
    public Login_register(String alert, String account, String password) {
        super(alert, account);
        this.password = password;
    }
    public String getPassword(){
        return password;
    }
}
