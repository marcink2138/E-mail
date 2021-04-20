package Comunication;

public class InterpretterClient {

    public InterpretterClient(){
    }

    public boolean Do(Message message, Account account){

        switch (message.getCommand()){
            case "Mail":
                if(message.isStatus()) {
                    account.addMail(message);
                    System.out.println("Udało sie wysłac");
                    return true;
                }
                else {
                    System.out.println("Nie udało sie wysłac");
                    return false;
                }
            case "LogIn":
                if(message.isStatus()) {
                    account.setEmailAdress(message.getAccount());
                    account.setPassword(message.getPassword());
                    //wyslac by pobrac maile
                    System.out.println("Zalogowano");
                    return true;
                }
                else {
                    System.out.println("Nie udało sie zalogowac");
                    return false;
                }
            case "Register":
                if(message.isStatus()) {
                    System.out.println("Zarejestrowano");
                    return true;
                }
                else {
                    System.out.println("Nie udalo sie zarejestrowac");
                    return false;
                }
            case "SendMails":
                if(message.isStatus()) {
                    account.clear();
                    account.setListOfMails(message.getListOfMails());
                    return true;
                }
                else {
                    System.out.println("Nie udalo sie pobrac maili");
                    return false;
                }
            case "DeleteAccount":
                if(message.isStatus()) {
                    System.out.println("Usunieto");
                    return true;
                }
                else {
                    System.out.println("Nie udalo sie usunac");
                    return false;
                }
            case "DeleteMail":
                if(message.isStatus()) {
                    account.deleteMail(message);
                    System.out.println("Usunieto");
                    return true;
                }
                else {
                    System.out.println("Nie udalo sie usunac");
                    return false;
                }
            case "ChangePassword":
                if(message.isStatus()) {
                    account.setPassword(message.getNewPassword());
                    System.out.println("Zmieniono haslo");
                    return true;
                }
                else {
                    System.out.println("Nie udalo sie zmienic hasla");
                    return false;
                }
            default:
                System.out.println("Bład");
                return false;
        }
    }
}
