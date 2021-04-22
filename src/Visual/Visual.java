package Visual;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.event.ActionEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Visual extends Application{
    private Stage mainWindow;
    private Scene sceneLogin;
    private Scene sceneRegister;


    @Override
    public void start(Stage primaryStage) throws Exception  {
        mainWindow = primaryStage;

        //scene logowania
        GridPane gridLog = new GridPane();
        gridLog.setPadding(new Insets(10,10,10,10));
        gridLog.setVgap(8);
        gridLog.setHgap(8);

        Label nameLabel = new Label("Login:");
        GridPane.setConstraints(nameLabel, 0, 0);

        TextField nameInput = new TextField();
        nameInput.setPromptText("login");
        GridPane.setConstraints(nameInput, 1, 0);

        Label passwordLabel = new Label("Login:");
        GridPane.setConstraints(passwordLabel, 0, 1);

        TextField passwordInput = new TextField();
        passwordInput.setPromptText("haslo");
        GridPane.setConstraints(passwordInput, 1, 1);

        Button loginButton = new Button("Zaloguj");
        GridPane.setConstraints(loginButton, 1, 2);

        gridLog.getChildren().addAll(nameLabel, nameInput, passwordLabel, passwordInput, loginButton);

        sceneLogin = new Scene(gridLog, 800, 800);


        mainWindow.setScene(sceneLogin);
        mainWindow.setTitle("Skrzynka mailowa");
        mainWindow.show();
    }


    public static void main(String args[]){
        launch(args);
    }
}
