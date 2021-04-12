package Comunication;

public class Register extends Message{
    private String name = null;
    private String password = null;
    private String passwordConfimation = null;

    public Register(String comand, String name, String password, String passwordConfimation){
        super(comand);
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
