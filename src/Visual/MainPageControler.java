package Visual;

import Comunication.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class MainPageControler {
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
       /*
        ArrayList<Button> listOfButtons = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            Button buttonmail = new Button(Integer.toString(i));
            listOfButtons.add(buttonmail);
        }
        VBox layout = new VBox();
        for(int i = 0; i < 3; i++){
            layout.getChildren().add(listOfButtons.get(i));
        }
        this.ListOfMailsView = layout;

        */
        TitleLabel.setText(client.getAccount().getEmailAdress());
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

    public void DeleteAccountButtonClick(ActionEvent actionEvent) {
    }

    public void ShowMailClick(ActionEvent actionEvent) {

    }
}
