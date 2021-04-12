package Comunication;

public class LogIn extends Message{
    private String name;
    private String password;

    public LogIn(String comand, String name, String password){
        super(comand);
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
