package Comunication;

public class DeleteUser extends Message{
    private String name;
    private String password;

    public DeleteUser(String text, String name, String password){
        super(text);
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
