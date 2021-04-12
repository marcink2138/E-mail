package Comunication;

import java.io.Serializable;

public class Message implements Serializable{
    private String comand = null;

    public Message(String comand) {
        this.comand = comand;
    }

    public String getComand() {
        return comand;
    }

}
