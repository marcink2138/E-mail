package Comunication;

import java.io.Serializable;

public class Message implements Serializable{
    private String text = null;

    public Message(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
