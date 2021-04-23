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

    public void LoginClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoginScene.fxml")));
        //bierzemy scene głowna
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 800, 500));
        window.show();
    }

    public void RegisterClick(ActionEvent actionEvent) {
        AlertLabel.setText("Couldnt Register");
        AlertLabel.setTextFill(Color.web("Red"));
    }
}
