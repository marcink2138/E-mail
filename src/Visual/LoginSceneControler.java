package Visual;

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
    public Button LoginButton;
    public Button RegisterButton;
    public Label LoginLabel;
    public Label PasswordLabel;
    public TextField LoginTextField;
    public TextField PasswordTextField;
    public Label AlertLabel;

    public void RegisterClick(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("RegisterScene.fxml")));
        //bierzemy scene głowna
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 800, 500));
        window.show();

    }

    public void LoginClick(ActionEvent actionEvent) {
        AlertLabel.setText("Couldnt log in");
        AlertLabel.setTextFill(Color.web("Red"));
    }
}
