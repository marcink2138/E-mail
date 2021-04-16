package Comunication;

public class InterpretterClient {

    public void Do(Message message, Account account){

        switch (message.getCommand()){
            case "Mail":
                if(message.isStatus()) {
                    account.addMail(message);
                    System.out.println("Udało sie wysłac");
                    break;
                }
                else {
                    System.out.println("Nie udało sie wysłac");
                    break;
                }
            case "LogIn":
                if(message.isStatus()) {
                    account.setEmailAdress(message.getAccount());
                    account.setPassword(message.getPassword());
                    //wyslac by pobrac maile
                    System.out.println("Zalogowano");
                    break;
                }
                else {
                    System.out.println("Nie udało sie zalogowac");
                    break;
                }
            case "Register":
                if(message.isStatus()) {
                    System.out.println("Zarejestrowano");
                    break;
                }
                else {
                    System.out.println("Nie udalo sie zarejestrowac");
                    break;
                }
            case "SendMails":
                if(message.isStatus()) {
                    account.clear();
                    account.setListOfMails(message.getListOfMails());
                    break;
                }
                else {
                    System.out.println("Nie udalo sie pobrac maili");
                    break;
                }
            case "DeleteAccount":
                if(message.isStatus()) {
                    System.out.println("Usunieto");
                    break;
                }
                else {
                    System.out.println("Nie udalo sie usunac");
                    break;
                }
            case "DeleteMail":
                if(message.isStatus()) {
                    account.deleteMail(message);
                    System.out.println("Usunieto");
                    break;
                }
                else {
                    System.out.println("Nie udalo sie usunac");
                    break;
                }
            case "ChangePassword":
                if(message.isStatus()) {
                    account.setPassword(message.getNewPassword());
                    System.out.println("Zmieniono haslo");
                    break;
                }
                else {
                    System.out.println("Nie udalo sie zmienic hasla");
                    break;
                }
            case "":
                break;
            default:
                System.out.println("Bład");
                break;
        }
    }
}
