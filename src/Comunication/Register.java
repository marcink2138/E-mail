package Comunication;

public class Register extends Message{
    private String name = null;
    private String password = null;
    private String passwordConfimation = null;

    public Register(String text, String name, String password, String passwordConfimation){
        super(text);
        this.name = name;
        this.password = password;
        this.passwordConfimation = passwordConfimation;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordConfimation() {
        return passwordConfimation;
    }
}
