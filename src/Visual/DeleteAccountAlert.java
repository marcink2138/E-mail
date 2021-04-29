package Visual;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class DeleteAccountAlert {
    boolean decision = false;


    public boolean dispplay(String oldPassword) throws IOException {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Delete account");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DeleteAccountAlertScene.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        DeleteAccountAlertControler deleteAccountAlertControler = fxmlLoader.getController();
        deleteAccountAlertControler.closeButton.setOnAction(e -> window.close());
        deleteAccountAlertControler.applyButton.setOnAction(e -> {
            if(!deleteAccountAlertControler.passwordTextField.getText().equals("") && deleteAccountAlertControler.passwordTextField.getText().equals(oldPassword)){
                decision = true;
                window.close();
            }else {
                deleteAccountAlertControler.alertLabel.setText("Password is not correct.");
            }

        });
        Scene scene = new Scene(root, 300,200);
        window.setScene(scene);
        window.setResizable(false);
        window.showAndWait();
        return decision;
    }
}
