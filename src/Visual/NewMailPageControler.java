package Visual;

import Comunication.Client;
import Comunication.Mail;
import Comunication.Message;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        mainPageControler.setClient(client);
        mainPageControler.refreshLabels();
        mainPageControler.loadListview();
        //bierzemy scene głowna
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 600, 500));
        window.show();
    }

    public void SendButtonClicked(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        if (!ToTextField.getText().isEmpty() && !FromTextField.getText().equals(ToTextField.getText())) {
            String reciver = ToTextField.getText();
            String title = TitleTextField.getText();
            String date = getData();
            String text = TextAreaField.getText();
            Message message = new Mail("Mail", client.getAccount().getEmailAdress(), null, true,
                    reciver, -1, title, date, text);
            client.send(message);
            if (client.read()) {
                new Alert().dispplay("Email successfully sent!");
                refreshLabels();
            } else
                new Alert().dispplay("No account found!");
        } else {
            new Alert().dispplay("Incorrect sender!");
        }

    }

    public void LogOutButtonClicked(ActionEvent actionEvent) {
        try {
            client.closeConection();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoginScene.fxml")));
            //bierzemy scene głowna
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(new Scene(root, 800, 500));
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setFromTextField() {
        FromTextField.setText(client.getAccount().getEmailAdress());
        FromTextField.setEditable(false);
        FromTextField.setDisable(true);
    }

    public String getData() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return localDateTime.format(dateTimeFormatter);
    }

    public void refreshLabels() {
        ToTextField.setText("");
        TitleTextField.setText("");
        TextAreaField.setText("");
    }
}
