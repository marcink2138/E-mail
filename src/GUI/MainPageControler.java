package GUI;

import Client.Client;
import Comunication.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class MainPageControler extends Thread {
    public javafx.scene.layout.BorderPane borderPane;
    public ListView listView;
    public TextField fromTextfield;
    public TextField dateTextfield;
    public TextField toTextfield;
    public TextField titleTextfield;
    public Label showingAccountLabel;
    public Label showingTimeLabel;
    private Client client;
    public Button refreshButton;
    public Button newMailButton;
    public Button deleteMailButton;
    public Button changePasswordButton;
    public Button logOotButton;
    public Button deleteAccountButton;
    public Label titleLabel;
    public Label fromLabel;
    public Label toLabel;
    public Label dateLabel;
    public TextArea textAreaField;
    public VBox listOfMailsView;



    public void setClient(Client client) {
        this.client = client;
        Tooltip tooltip = new Tooltip("Refresh");
        refreshButton.setTooltip(tooltip);
        tooltip = new Tooltip("New Mail");
        newMailButton.setTooltip(tooltip);
        tooltip = new Tooltip("Delete Mail");
        deleteMailButton.setTooltip(tooltip);
        tooltip = new Tooltip("Change Password");
        changePasswordButton.setTooltip(tooltip);
        tooltip = new Tooltip("Log Out");
        logOotButton.setTooltip(tooltip);
        tooltip = new Tooltip("DeleteAccount");
        deleteAccountButton.setTooltip(tooltip);
        initClock();
    }

    public void refreshButtonClick(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        Message message = new Message("SendMails", client.getAccount().getEmailAdress(), client.getAccount().getPassword(), true);
        client.send(message);
        client.read();
        refreshLabels();
        clearListView();
        loadListview();
    }

    public void newMailButtonClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NewMailPage.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        NewMailPageControler newMailPageControler = fxmlLoader.getController();
        newMailPageControler.setClient(client);
        newMailPageControler.setFromTextField();
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 800, 500));
        window.show();
    }

    public void deleteMailButtonClick(ActionEvent actionEvent) throws IOException {
        if (listView.getSelectionModel().getSelectedItem() != null || !listView.getSelectionModel().isEmpty()) {
            int index = listView.getSelectionModel().getSelectedIndex();
            int messageId = client.getAccount().getListOfMails().get(index).getMessageId();
            try {
                Message message = new DeleteMail("DeleteMail", client.getAccount().getEmailAdress(),
                        client.getAccount().getPassword(), true, messageId);
                client.send(message);
                client.getAccount().getListOfMails().remove(index);
                clearListView();
                loadListview();
            } catch (IOException e) {
                new Alert().dispplay("Connection with server has been lost");
            }
        }
    }

    public void changePasswordButtonClick(ActionEvent actionEvent) throws ClassNotFoundException, IOException {
        ChangePasswordAlert changePasswordAlert = new ChangePasswordAlert();
        String newPassword = changePasswordAlert.display(client.getAccount().getPassword());
        if (newPassword != null) {
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
            } catch (IOException e) {
                new Alert().dispplay("Connection with server has been lost");
            }
        }
    }

    public void logOotButtonClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoginScene.fxml")));
        //bierzemy scene głowna
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 800, 500));
        window.show();
        client.closeConection();
    }

    public void refreshLabels() {

      /*  int i = client.getAccount().getListOfMails().size();
        if (i != 0) {
            titleTextfield.setText(client.getAccount().getListOfMails().get(0).getTitle());
            fromTextfield.setText(client.getAccount().getListOfMails().get(0).getReciver());
            dateTextfield.setText(client.getAccount().getListOfMails().get(0).getDate());
            toTextfield.setText(client.getAccount().getListOfMails().get(0).getAccount());
            textAreaField.setText(client.getAccount().getListOfMails().get(0).getText());
            showingAccountLabel.setText(client.getAccount().getEmailAdress());

        } else {*/
            titleTextfield.clear();
            fromTextfield.clear();
            dateTextfield.clear();
            toTextfield.clear();
            textAreaField.clear();
            showingAccountLabel.setText(client.getAccount().getEmailAdress());
        //}
    }

    public void deleteAccountButtonClick(ActionEvent actionEvent) throws IOException {
        DeleteAccountAlert deleteAccountAlert = new DeleteAccountAlert();
        boolean yesOrNo = deleteAccountAlert.dispplay(client.getAccount().getPassword());
        if (yesOrNo) {
            Message message = new Message("DeleteAccount", client.getAccount().getEmailAdress(),
                    client.getAccount().getPassword(), true);
            try {
                client.send(message);
                if (client.read()) {
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
    }

    public void listViewClicked(MouseEvent mouseEvent) {
        if (listView.getSelectionModel().getSelectedItem() == null || listView.getSelectionModel().isEmpty())
            return;
        int which = listView.getSelectionModel().getSelectedIndex();
        titleTextfield.setText(client.getAccount().getListOfMails().get(which).getTitle());
        fromTextfield.setText(client.getAccount().getListOfMails().get(which).getReciver());
        toTextfield.setText(client.getAccount().getListOfMails().get(which).getAccount());
        dateTextfield.setText(client.getAccount().getListOfMails().get(which).getDate());
        textAreaField.setText(client.getAccount().getListOfMails().get(which).getText());
    }

    public void loadListview() {
        for (int i = 0; i < client.getAccount().getListOfMails().size(); i++) {
            listView.getItems().add(client.getAccount().getListOfMails().get(i).getTitle());
        }

    }

    public void clearListView() {
        listView.getItems().clear();
    }

    private String getTime() {
        LocalTime time = LocalTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return time.format(dateTimeFormatter);
    }
    private void initClock() {

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            showingTimeLabel.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }
}
