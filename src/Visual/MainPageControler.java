package Visual;

import Comunication.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainPageControler {
    public javafx.scene.layout.BorderPane BorderPane;
    public ListView ListView;
    public TextField fromTextfield;
    public TextField dateTextfield;
    public TextField toTextfield;
    public TextField titleTextfield;
    private Client client;
    public Button RefreshButton;
    public Button NewMailButton;
    public Button DeleteMailButton;
    public Button ChangePasswordButton;
    public Button LogOotButton;
    public Button DeleteAccountButton;
    public Label TitleLabel;
    public Label FromLabel;
    public Label ToLabel;
    public Label DateLabel;
    public TextArea TextAreaField;
    public VBox ListOfMailsView;


    public void setClient(Client client) {
        this.client = client;
    }

    public void RefreshButtonClick(ActionEvent actionEvent) {
        clearListView();
        loadListview();
    }

    public void NewMailButtonClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NewMailPage.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        NewMailPageControler newMailPageControler = fxmlLoader.getController();
        newMailPageControler.setClient(client);
        newMailPageControler.setFromTextField();
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 800, 500));
        window.show();
    }

    public void DeleteMailButtonClick(ActionEvent actionEvent) {
        if (ListView.getSelectionModel().getSelectedItem() != null || !ListView.getSelectionModel().isEmpty()) {
            int index = ListView.getSelectionModel().getSelectedIndex();
            int messageId = client.getAccount().getListOfMails().get(index).getMessageId();
            try {
                client.openConection();
                Message message = new DeleteMail("DeleteMail", client.getAccount().getEmailAdress(),
                        client.getAccount().getPassword(), true, messageId);
                client.send(message);
                client.closeConection();
                client.getAccount().getListOfMails().remove(index);
                clearListView();
                loadListview();
            } catch (IOException e) {
                System.out.println("Cannot connect to the server");
            }
        }
    }

    public void ChangePasswordButtonClick(ActionEvent actionEvent) throws IOException {
        ChangePasswordAlert changePasswordAlert = new ChangePasswordAlert();
        String password = changePasswordAlert.dispplay(client.getAccount().getPassword());
        if(password == null) {
        }
        else {
            //wysylasz mesege i nowe haslo to password
        }
    }

    public void LogOotButtonClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoginScene.fxml")));
        //bierzemy scene głowna
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 800, 500));
        window.show();
    }

    public void refreshLabels() {
        int i = client.getAccount().getListOfMails().size();
        if (i != 0) {
            titleTextfield.setText(client.getAccount().getListOfMails().get(i - 1).getTitle());
            fromTextfield.setText(client.getAccount().getListOfMails().get(i - 1).getReciver());
            dateTextfield.setText(client.getAccount().getListOfMails().get(i - 1).getDate());
            toTextfield.setText(client.getAccount().getListOfMails().get(i - 1).getAccount());
        } else {
            titleTextfield.setText("");
            fromTextfield.setText("");
            dateTextfield.setText("");
            toTextfield.setText("");
        }
    }

    public void DeleteAccountButtonClick(ActionEvent actionEvent) {
    }

    public void ShowMailClick(ActionEvent actionEvent) {

    }

    public void ListViewClicked(MouseEvent mouseEvent) {
        if (ListView.getSelectionModel().getSelectedItem() == null || ListView.getSelectionModel().isEmpty())
            return;
        int which = ListView.getSelectionModel().getSelectedIndex();
        titleTextfield.setText(client.getAccount().getListOfMails().get(which).getTitle());
        fromTextfield.setText(client.getAccount().getListOfMails().get(which).getReciver());
        toTextfield.setText(client.getAccount().getListOfMails().get(which).getAccount());
        dateTextfield.setText(client.getAccount().getListOfMails().get(which).getDate());
        TextAreaField.setText(client.getAccount().getListOfMails().get(which).getText());
    }

    public void loadListview() {
        for (int i = 0; i < client.getAccount().getListOfMails().size(); i++) {
            ListView.getItems().add(client.getAccount().getListOfMails().get(i).getTitle());
        }

    }

    public void clearListView() {
        ListView.getItems().clear();
    }
}
