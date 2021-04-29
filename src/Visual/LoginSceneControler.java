package Visual;

import Comunication.Client;
import Comunication.LoginRegisterDeleteAccount;
import Comunication.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginSceneControler {
    public Client client;
    public Button LoginButton;
    public Button RegisterButton;
    public Label LoginLabel;
    public Label PasswordLabel;
    public TextField LoginTextField;
    public TextField PasswordTextField;
    public Label AlertLabel;
    public Button tempButton;


    public void RegisterClick(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("RegisterScene.fxml")));
        //bierzemy scene głowna
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 800, 500));
        window.show();

    }

    public void LoginClick(ActionEvent actionEvent) throws ClassNotFoundException, IOException {
        if (!LoginTextField.getText().equals("")&&!PasswordTextField.getText().equals("")) {
            try {
                client = new Client(6666, "192.168.178.69");
                Message message = new Message("LogIn", LoginTextField.getText(), PasswordTextField.getText(), true);
                client.openConection();
                client.send(message);
                if (client.read()) {
                    client.getAccount().setEmailAdress(LoginTextField.getText());
                    client.getAccount().setPassword(PasswordTextField.getText());
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
                    window.setScene(new Scene(root, 600, 500));
                    window.show();
                } else {
                    new Alert().dispplay("This account does not exists.");
                    refreshLabels();
                }
            }catch (IOException e){
                new Alert().dispplay("Cannot connect to the server!");
            }
        }else {
            new Alert().dispplay("Please complete empty boxes!");
            refreshLabels();
        }
    }
    public void refreshLabels(){
        LoginTextField.clear();
        PasswordTextField.clear();
    }

    public void setClient(Client client){
        this.client = client;
    }
    public void TempClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainPage.fxml")));
        //bierzemy scene głowna
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 600, 500));
        window.show();
    }
}
