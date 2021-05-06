package GUI;

import Client.Client;
import Comunication.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginSceneControler {
    public Client client;
    public Button loginButton;
    public Button registerButton;
    public Label loginLabel;
    public Label passwordLabel;
    public TextField loginTextField;
    public TextField passwordTextField;
    public Label alertLabel;


    public void registerClick(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("RegisterScene.fxml")));
        //bierzemy scene głowna
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 800, 500));
        window.show();

    }

    public void loginClick(ActionEvent actionEvent) throws ClassNotFoundException, IOException {
        if (!loginTextField.getText().equals("") && !passwordTextField.getText().equals("")) {
            try {
                client = new Client(6666, "192.168.178.69");
                Message message = new Message("LogIn", loginTextField.getText(), passwordTextField.getText(), true);
                client.openConection();
                client.send(message);
                if (client.read()) {
                    client.getAccount().setEmailAdress(loginTextField.getText());
                    client.getAccount().setPassword(passwordTextField.getText());
                    message = new Message("SendMails", client.getAccount().getEmailAdress(), client.getAccount().getPassword(), true);
                    client.send(message);
                    client.read();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
                    Parent root = (Parent) fxmlLoader.load();
                    MainPageControler mainPageControler = fxmlLoader.getController();
                    mainPageControler.setClient(client);
                    mainPageControler.refreshLabels();
                    mainPageControler.loadListview();
                    //bierzemy scene głowna
                    Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    window.setScene(new Scene(root, 800, 500));
                    window.show();
                } else {
                    new Alert().dispplay("Wrong account or password!");
                    refreshLabels();
                }
            } catch (IOException e) {
                new Alert().dispplay("Cannot connect to the server!");
            }
        } else {
            new Alert().dispplay("Please complete empty boxes!");
            refreshLabels();
        }
    }

    public void refreshLabels() {
        loginTextField.clear();
        passwordTextField.clear();
    }

    public void setClient(Client client) {
        this.client = client;
    }

}
