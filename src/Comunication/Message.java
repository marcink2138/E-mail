package Comunication;

import java.io.Serializable;

public class Message implements Serializable {
    private String alert;
    private String account;

    public Message(String alert, String account) {
        this.alert = alert;
        this.account = account;
    }

    public String getAlert() {
        return alert;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccount() {
        return account;
    }

    public void setSender() {
    }

    public String getSender() {
        return null;
    }

    public void setTitle() {
    }

    public String getTitle() {
        return null;
    }

    public void setDate() {
    }

    public String getDate() {
        return null;
    }

    public void setId() {
    }

    public int getMessageId() {
        return -1;
    }

    public void setPassword(){

    }

    public String getPassword(){
        return null;
    }

    public String getText(){
        return null;
    }
}
