package Visual;


import Comunication.ChangePassword;
import Comunication.Client;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;

public class ChangePasswordAlert {
    String password = null;




    public String dispplay(String oldPassword) throws IOException {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("ChangePassword");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ChangePasswordAlertScene.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        ChangePasswordAlertControler changePasswordAlertControler = fxmlLoader.getController();
        changePasswordAlertControler.CloseButton.setOnAction(e -> window.close());
        changePasswordAlertControler.ApplyButton.setOnAction(e -> {
            if (!changePasswordAlertControler.NewPasswordTextField.getText().equals("") && !changePasswordAlertControler.ConfitmNewPasswordTextField.getText().equals("")
            && !changePasswordAlertControler.OldPasswordTextField.getText().equals("") && changePasswordAlertControler.OldPasswordTextField.getText().equals(oldPassword)) {
                if (changePasswordAlertControler.NewPasswordTextField.getText().equals(changePasswordAlertControler.ConfitmNewPasswordTextField.getText())) {
                    password = changePasswordAlertControler.NewPasswordTextField.getText();
                    window.close();
                }
            }else {

            }
        });
        Scene scene = new Scene(root, 300,325);
        window.setScene(scene);
        window.setResizable(false);
        window.showAndWait();
        return password;
    }

}
