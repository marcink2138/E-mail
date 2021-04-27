package Visual;

import Comunication.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class NewMailPageControler {
    public Client client;
    public Button GoBackButton;
    public Button SendButton;
    public Button LogOutButton;
    public TextField FromTextField;
    public TextField ToTextField;
    public TextField TitleTextField;
    public TextArea TextAreaField;

    public void setClient(Client client) {
        this.client = client;
    }

    public void GoBackButtonClicked(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        MainPageControler mainPageControler = fxmlLoader.getController();
        //mainPageControler.setClient(client);
        //mainPageControler.refreshLabels();
        //mainPageControler.loadListview();
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 800, 500));
        window.show();
    }

    public void SendButtonClicked(ActionEvent actionEvent) {

    }

    public void LogOutButtonClicked(ActionEvent actionEvent) {

    }

    public void setFromTextField(){
        FromTextField.setText("Dis");
        FromTextField.setEditable(false);
        FromTextField.setDisable(true);
    }
}
