package Visual;

import Comunication.Account;
import Comunication.Client;
import Comunication.Mail;
import Comunication.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class MainPageControler {
    public javafx.scene.layout.BorderPane BorderPane;
    public ListView xd;
    public TextField fromTextfield;
    public TextField dateTextfield;
    public TextField toTextfield;
    public TextField titleTextfield;
    private Client client;
    public Button RefreshButton;
    public Button NewMailButton;
    public Button DeleteMailButton;
    public Button ChangePasswordButton;
    public Button LogOotButton;
    public Button DeleteAccountButton;
    public Label TitleLabel;
    public Label FromLabel;
    public Label ToLabel;
    public Label DateLabel;
    public TextArea TextAreaField;
    public VBox ListOfMailsView;



    public void setClient(Client client){
        this.client = client;
    }

    public void RefreshButtonClick(ActionEvent actionEvent) {
        Account account = new Account("xd", "xd");
        account.addMail(new Mail("Mail", "xd", "xd",true, "dis", 1, "1", "2", "xd"));
        account.addMail(new Mail("Mail", "xd", "xd",true, "dis", 1, "2", "2", "xd"));
        account.addMail(new Mail("Mail", "xd", "xd",true, "dis", 1, "3", "2", "xd"));
        account.addMail(new Mail("Mail", "xd", "xd",true, "dis", 1, "4", "2", "xd"));

        xd.getItems().addAll(account.getListOfMails().get(0).getTitle());
        xd.getItems().addAll(account.getListOfMails().get(1).getTitle());


    }

    public void NewMailButtonClick(ActionEvent actionEvent) {
        TextAreaField.setText("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    public void DeleteMailButtonClick(ActionEvent actionEvent) {
    }

    public void ChangePasswordButtonClick(ActionEvent actionEvent) {
    }

    public void LogOotButtonClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoginScene.fxml")));
        //bierzemy scene głowna
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 800, 500));
        window.show();
    }
    public void refreshLabels(){
        int i = client.getAccount().getListOfMails().size();
        if (i != 0) {
            titleTextfield.setText(client.getAccount().getListOfMails().get(i - 1).getTitle());
            fromTextfield.setText(client.getAccount().getListOfMails().get(i - 1).getReciver());
            dateTextfield.setText(client.getAccount().getListOfMails().get(i - 1).getDate());
            toTextfield.setText(client.getAccount().getListOfMails().get(i - 1).getAccount());
        }else {
            titleTextfield.setText("");
            fromTextfield.setText("");
            dateTextfield.setText("");
            toTextfield.setText("");
        }
    }
    public void DeleteAccountButtonClick(ActionEvent actionEvent) {
    }

    public void ShowMailClick(ActionEvent actionEvent) {

    }
}
