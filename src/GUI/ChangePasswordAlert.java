package GUI;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;

public class ChangePasswordAlert {
    String password = null;




    public String display(String oldPassword) throws IOException {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("ChangePassword");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ChangePasswordAlertScene.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        ChangePasswordAlertControler changePasswordAlertControler = fxmlLoader.getController();
        changePasswordAlertControler.closeButton.setOnAction(e -> window.close());
        changePasswordAlertControler.applyButton.setOnAction(e -> {
            if (!changePasswordAlertControler.newPasswordTextField.getText().equals("") && !changePasswordAlertControler.confitmNewPasswordTextField.getText().equals("")
            && !changePasswordAlertControler.oldPasswordTextField.getText().equals("") && changePasswordAlertControler.oldPasswordTextField.getText().equals(oldPassword)) {
                if (changePasswordAlertControler.newPasswordTextField.getText().equals(changePasswordAlertControler.confitmNewPasswordTextField.getText())) {
                    password = changePasswordAlertControler.newPasswordTextField.getText();
                    window.close();
                }else{
                    changePasswordAlertControler.alertLabel.setText("Passwords are no identical.");
                }
            }else {
                changePasswordAlertControler.alertLabel.setText("Please complete all text boxes.");
            }
        });
        Scene scene = new Scene(root, 300,325);
        window.setScene(scene);
        window.setResizable(false);
        window.showAndWait();
        return password;
    }

}
