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
    public Label showingAccountLabel;
    public Label showingTimeLabel;
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
        Tooltip tooltip = new Tooltip("Refresh");
        RefreshButton.setTooltip(tooltip);
        tooltip = new Tooltip("New Mail");
        NewMailButton.setTooltip(tooltip);
        tooltip = new Tooltip("Delete Mail");
        DeleteMailButton.setTooltip(tooltip);
        tooltip = new Tooltip("Change Password");
        ChangePasswordButton.setTooltip(tooltip);
        tooltip = new Tooltip("Log Out");
        LogOotButton.setTooltip(tooltip);
        tooltip = new Tooltip("DeleteAccount");
        DeleteAccountButton.setTooltip(tooltip);
    }

    public void RefreshButtonClick(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        Message message = new Message("SendMails", client.getAccount().getEmailAdress(), client.getAccount().getPassword(), true);
        client.send(message);
        client.read();
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

    public void DeleteMailButtonClick(ActionEvent actionEvent) throws IOException {
        if (ListView.getSelectionModel().getSelectedItem() != null || !ListView.getSelectionModel().isEmpty()) {
            int index = ListView.getSelectionModel().getSelectedIndex();
            int messageId = client.getAccount().getListOfMails().get(index).getMessageId();
            try {
                Message message = new DeleteMail("DeleteMail", client.getAccount().getEmailAdress(),
                        client.getAccount().getPassword(), true, messageId);
                client.send(message);
                client.closeConection();
                client.getAccount().getListOfMails().remove(index);
                clearListView();
                loadListview();
            } catch (IOException e) {
                new Alert().dispplay("Connection with server has been lost");
            }
        }
    }

    public void ChangePasswordButtonClick(ActionEvent actionEvent) throws ClassNotFoundException, IOException {
        ChangePasswordAlert changePasswordAlert = new ChangePasswordAlert();
        String newPassword = changePasswordAlert.dispplay(client.getAccount().getPassword());
        if(newPassword != null) {
            Message m = new ChangePassword("ChangePassword", client.getAccount().getEmailAdress(),
                    client.getAccount().getPassword(), true, newPassword);
            try {
                client.send(m);
                if (client.read()) {
                    client.getAccount().setPassword(newPassword);
                    new Alert().dispplay("Password successfully changed!");
                } else {
                    new Alert().dispplay("Fatal Error! Try logOut and logIn!");
                }
            }catch (IOException e){
                new Alert().dispplay("Connection with server has been lost");
            }
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
            titleTextfield.setText(client.getAccount().getListOfMails().get(0).getTitle());
            fromTextfield.setText(client.getAccount().getListOfMails().get(0).getReciver());
            dateTextfield.setText(client.getAccount().getListOfMails().get(0).getDate());
            toTextfield.setText(client.getAccount().getListOfMails().get(0).getAccount());
            TextAreaField.setText(client.getAccount().getListOfMails().get(0).getText());
            showingAccountLabel.setText(client.getAccount().getEmailAdress());

        } else {
            titleTextfield.clear();
            fromTextfield.clear();
            dateTextfield.clear();
            toTextfield.clear();
            showingAccountLabel.setText(client.getAccount().getEmailAdress());
        }
    }

    public void DeleteAccountButtonClick(ActionEvent actionEvent) throws IOException {
        DeleteAccountAlert deleteAccountAlert = new DeleteAccountAlert();
        boolean yesOrNo = deleteAccountAlert.dispplay(client.getAccount().getPassword());
        if(yesOrNo) {
            Message message = new Message("DeleteAccount", client.getAccount().getEmailAdress(),
                    client.getAccount().getPassword(), true);
            try {
                client.send(message);
                if (client.read()){
                    client.closeConection();
                    new Alert().dispplay("Account successfully deleted!");
                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoginScene.fxml")));
                    Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    window.setScene(new Scene(root, 800, 500));
                    window.show();
                }
            } catch (ClassNotFoundException | IOException e) {
                new Alert().dispplay("Connection with the server has been lost!");
            }
        }
        else {
            new Alert().dispplay("Enter the correct password!");
        }
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
