package Comunication;

public class DeleteUser extends Message {
    public DeleteUser(String account) {
        super("del_usr", account);
    }
}
