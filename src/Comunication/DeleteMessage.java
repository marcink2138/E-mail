package Comunication;

public class DeleteMessage extends Message{
    private String name = null;
    private int id = 0;

    public DeleteMessage(String comand, String name, int id){
        super(comand);
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
