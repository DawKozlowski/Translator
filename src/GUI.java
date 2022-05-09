

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GUI extends Application {
    @Override
    public void start(Stage stage) throws Exception {


        stage.setTitle("Application");
        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: rgb(255,255,255)");

        VBox center = new VBox();


        Label word = new Label("Word");
        word.setFont(new Font(20));
        word.setPadding(new Insets(10,10,10,10));
        Label languageCode = new Label("Language Code");
        languageCode.setFont(new Font(20));
        languageCode.setPadding(new Insets(10,10,10,10));
        Label port = new Label("Port");
        port.setFont(new Font(20));
        port.setPadding(new Insets(10,10,10,10));
        Label translation = new Label("Translation");
        translation.setFont(new Font(20));
        translation.setPadding(new Insets(10,10,10,10));

        TextField wordField = new TextField();
        wordField.setMaxWidth(300);
        wordField.setPadding(new Insets(10,10,10,10));
        TextField languageCodeField = new TextField();
        languageCodeField.setMaxWidth(300);
        languageCodeField.setPadding(new Insets(10,10,10,10));
        TextField portField = new TextField();
        portField.setMaxWidth(300);
        portField.setPadding(new Insets(10,10,10,10));
        TextField translationField = new TextField();
        translationField.setMaxWidth(300);
        translationField.setPadding(new Insets(10,10,10,10));


        Button button = new Button("Submit");
        button.setPadding(new Insets(10,10,10,10));
        button.setStyle("-fx-background-color: rgb(26,147,43)");
        button.setFont(new Font(20));

        button.setOnAction(a ->{
            ClientHandler  clientHandler = new ClientHandler(wordField.getText()+","+languageCodeField.getText()+","+portField.getText(), portField.getText());
            clientHandler.sendCommand();
            translationField.setText(clientHandler.translation());
        });

        center.getChildren().addAll(word, wordField, languageCode, languageCodeField, port, portField, button, translation, translationField);

        borderPane.setCenter(center);

        Scene scene = new Scene(borderPane,500,400);
        stage.setScene(scene);
        stage.show();
    }
}
