package Visual;

import Comunication.Client;
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
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class RegisterSceneControler {
    public Button LoginButton;
    public Button RegisterButton;
    public TextField ConfirmPasswordTextField;
    public Label ConfirmPasswordLabel;
    public TextField PasswordTextField;
    public Label PasswordLabel;
    public TextField LoginTextField;
    public Label LoginLabel;
    public Label AlertLabel;
    private Client client;
    public void LoginClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoginScene.fxml")));
        //bierzemy scene głowna
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 800, 500));
        window.show();
    }

    public void RegisterClick(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        if (LoginTextField.getText() != null && PasswordTextField.getText() != null &&
                ConfirmPasswordTextField.getText() != null && PasswordTextField.getText().equals(ConfirmPasswordTextField.getText())){
            client = new Client(6666, "192.168.178.69");
            client.openConection();
            Message message = new Message("Register", LoginTextField.getText(), PasswordTextField.getText(), true);
            client.send(message);
            if(client.read()){
                client.getAccount().setEmailAdress(LoginTextField.getText());
                client.getAccount().setPassword(LoginTextField.getText());
                message = new Message("SendMails", client.getAccount().getEmailAdress(), client.getAccount().getPassword(), true);
                client.send(message);
                client.read();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                MainPageControler mainPageControler = fxmlLoader.getController();
                mainPageControler.setClient(client);
                mainPageControler.refreshLabels();
                //bierzemy scene głowna
                Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                window.setScene(new Scene(root, 800, 500));
                window.show();
            }else{
                AlertLabel.setText("Takie konto znajduje sie w naszej bazie");
                AlertLabel.setTextFill(Color.web("Red"));
            }
        }else {
            AlertLabel.setText("Wprowadz poprawne wartosci");
            AlertLabel.setTextFill(Color.web("Red"));
        }

    }
}
