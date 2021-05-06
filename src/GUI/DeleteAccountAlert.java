package GUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class DeleteAccountAlert {
    boolean decision = false;


    public boolean display(String oldPassword) throws IOException {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Delete account");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DeleteAccountAlertScene.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        DeleteAccountAlertController deleteAccountAlertController = fxmlLoader.getController();
        deleteAccountAlertController.closeButton.setOnAction(e -> window.close());
        deleteAccountAlertController.applyButton.setOnAction(e -> {
            if(!deleteAccountAlertController.passwordTextField.getText().equals("") && deleteAccountAlertController.passwordTextField.getText().equals(oldPassword)){
                decision = true;
                window.close();
            }else {
                deleteAccountAlertController.alertLabel.setText("Password is not correct.");
            }

        });
        Scene scene = new Scene(root, 300,200);
        window.setScene(scene);
        window.setResizable(false);
        window.showAndWait();
        return decision;
    }
}
