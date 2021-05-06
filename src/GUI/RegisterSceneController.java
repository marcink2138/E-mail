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

public class RegisterSceneController {
    public Button loginButton;
    public Button registerButton;
    public TextField confirmPasswordTextField;
    public Label confirmPasswordLabel;
    public TextField passwordTextField;
    public Label passwordLabel;
    public TextField loginTextField;
    public Label loginLabel;
    public Label alertLabel;
    private Client client;
    public void loginClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoginScene.fxml")));
        //bierzemy scene głowna
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 800, 500));
        window.show();
    }

    public void registerClick(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        if (!loginTextField.getText().equals("") && !passwordTextField.getText().equals("") &&
                !confirmPasswordTextField.getText().equals("") && passwordTextField.getText().equals(confirmPasswordTextField.getText())){
            try {
                client = new Client(6666, "192.168.178.69");
                client.openConnection();
                Message message = new Message("Register", loginTextField.getText(), passwordTextField.getText(), true);
                client.send(message);
                if (client.read()) {
                    client.getAccount().setEmailAddress(loginTextField.getText());
                    client.getAccount().setPassword(loginTextField.getText());
                    message = new Message("SendMails", client.getAccount().getEmailAddress(), client.getAccount().getPassword(), true);
                    client.send(message);
                    client.read();
                    new Alert().display("Account successfully registered!");
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginScene.fxml"));
                    Parent root = (Parent) fxmlLoader.load();
                    LoginSceneController loginSceneController = fxmlLoader.getController();
                    loginSceneController.setClient(client);
                    loginSceneController.refreshLabels();
                    //bierzemy scene głowna
                    Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    window.setScene(new Scene(root, 800, 500));
                    window.show();
                } else {
                    new Alert().display("This account already exists!");
                    refreshLabels();
                }
            }catch (IOException e){
                new Alert().display("Cannot connect to the server!");
                refreshLabels();
            }
        }else {
            new Alert().display("Please complete empty boxes!");
            refreshLabels();
        }

    }
    public void refreshLabels(){
        loginTextField.clear();
        passwordTextField.clear();
        confirmPasswordTextField.clear();
    }
}
