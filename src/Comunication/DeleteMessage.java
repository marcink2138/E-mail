package Comunication;

public class DeleteMessage extends Message{
    private String name = null;
    private int id = 0;

    public DeleteMessage(String text, String name, int id){
        super(text);
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
