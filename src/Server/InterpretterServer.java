package Server;

import Comunication.*;

import java.sql.SQLException;

public class InterpretterServer {
    private ConnEmails connEmails;
    private ConnAcc connAcc;
    private boolean status;

    public InterpretterServer() throws SQLException {
        connAcc = new ConnAcc();
        connEmails = new ConnEmails();
    }

    public Message Do(Message message) throws SQLException {

        switch (message.getCommand()) {
            case "Mail":
                status = insertMail(message);
                return new Message("Mail", message.getAccount(), null, status);

            case "LogIn":
                status = logIn(message);
                return new Message("LogIn", message.getAccount(), null, status);

            case "Register":
                status = register(message);
                return new Message("Register", message.getAccount(), null, status);

            case "SendMails":
                return sendMails(message);

            case "DeleteAccount":
                status = deleteAccount(message);
                return new Message("DeleteAccount", message.getAccount(), null, status);

            case "DeleteMail":
                status = deleteMail(message);
                return new Message("DeleteMail", message.getAccount(), null, status);

            case "ChangePassword":
                status = changePassword(message);
                return new Message("ChangePassword", message.getAccount(), null, status);

            default:
                return new Message("Fail", message.getAccount(), null, false);
        }
    }

    public boolean deleteAccount(Message message) throws SQLException {
        String account = message.getAccount();
        String password = message.getPassword();
        return connAcc.deleteAccount(account, password);
    }

    public boolean insertMail(Message message) throws SQLException {
        String account = message.getReciver();
        String sender = message.getAccount();
        String title = message.getTitle();
        String date = message.getDate();
        String messege = message.getText();
        return connEmails.insertMess(account, sender, title, date, messege);
    }

    public boolean logIn(Message message) throws SQLException {
        String account = message.getAccount();
        String password = message.getPassword();
        return connAcc.isAcc(account, password);
    }

    public boolean changePassword(Message message) throws SQLException {
        String account = message.getAccount();
        String password = message.getPassword();
        String newPassword = message.getNewPassword();
        return connAcc.changePassword(account, password, newPassword);
    }

    public boolean register(Message message) throws SQLException {
        String account = message.getAccount();
        String password = message.getPassword();
        return connAcc.createAccount(account, password);
    }

    public boolean deleteMail(Message message) throws SQLException {
        String account = message.getAccount();
        String password = message.getPassword();
        int id = message.getMessageId();
        return connEmails.deleteMess(account, password, id);
    }

    public Message sendMails(Message message) throws SQLException {
        String account = message.getAccount();
        String password = message.getPassword();
        return connEmails.getListMess(account, password);
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        InterpretterServer a = new InterpretterServer();
        //Message b = new LoginRegisterDeleteAccount("Register", "koszkakoszka", true, "polska");
        //Message b = new Mail("Mail", "koszkakoszka", true, "koszkakoszka", -1, "asd", "dsa","sdaasdsad");
        //Message b = new ChangePassword("ChangePassword", "koszkakoszka", true, "polska", "polska1");
        //Message b = new LoginRegisterDeleteAccount("LogIn","koszkakoszka", true, "polska1");
        //Message b = new DeleteMail("DeleteMail", "koszkakoszka",true, 3);
        //Message b = new LoginRegisterDeleteAccount("LogIn","koszkakoszka", true, "apolska1");
        // Message b = new Message("SendMails", "koszkakoszka1", true);

        //Message c = a.Do(b);
        //for (int i = 0; i < c.getListOfMails().size(); i++)
        //   System.out.println(c.getListOfMails().get(i).getMessageId());
    }
}
