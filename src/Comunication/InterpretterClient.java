package Comunication;

public class InterpretterClient {


    public boolean Do(Message recivedMessage, Account account, Message sendMessage) {

        switch (recivedMessage.getCommand()) {
            case "Mail":
                if (recivedMessage.isStatus()) {
                    account.addMail(sendMessage);
                    System.out.println("Udało sie wysłac");
                    return true;
                } else {
                    System.out.println("Nie udało sie wysłac");
                    return false;
                }
            case "LogIn":
                if (recivedMessage.isStatus()) {
                    account.setEmailAdress(sendMessage.getAccount());
                    account.setPassword(sendMessage.getPassword());
                    //wyslac by pobrac maile
                    System.out.println("Zalogowano");
                    return true;
                } else {
                    System.out.println("Nie udało sie zalogowac");
                    return false;
                }
            case "Register":
                if (recivedMessage.isStatus()) {
                    System.out.println("Zarejestrowano");
                    return true;
                } else {
                    System.out.println("Nie udalo sie zarejestrowac");
                    return false;
                }
            case "SendMails":
                if (recivedMessage.isStatus()) {
                    account.clear();
                    account.setListOfMails(recivedMessage.getListOfMails());
                    return true;
                } else {
                    System.out.println("Nie udalo sie pobrac maili");
                    return false;
                }
            case "DeleteAccount":
                if (recivedMessage.isStatus()) {
                    System.out.println("Usunieto");
                    return true;
                } else {
                    System.out.println("Nie udalo sie usunac");
                    return false;
                }
            case "DeleteMail":
                if (recivedMessage.isStatus()) {
                    account.deleteMail(recivedMessage);
                    System.out.println("Usunieto");
                    return true;
                } else {
                    System.out.println("Nie udalo sie usunac");
                    return false;
                }
            case "ChangePassword":
                if (recivedMessage.isStatus()) {
                    account.setPassword(sendMessage.getNewPassword());
                    System.out.println("Zmieniono haslo");
                    return true;
                } else {
                    System.out.println("Nie udalo sie zmienic hasla");
                    return false;
                }
            case "Fail":
                System.out.println("Server fatal error");
                return false;
            default:
                System.out.println("Bład");
                return false;
        }
    }
}
