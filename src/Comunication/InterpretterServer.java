package Comunication;

public class InterpretterServer {

    public void Do(Message message){

        switch (message.getCommand()){
            case "Mail":
                break;
            case "LogIn":
                break;
            case "Register":
                break;
            case "SendMails":
                break;
            case "DeleteAccount":
                break;
            case "DeleteMail":
                break;
            case "ChangePassword":
                break;
            case "":
                break;
            default:

        }
    }
}
