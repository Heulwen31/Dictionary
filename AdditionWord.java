package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AdditionWord {

    private final DictionaryManagement dictionaryManagement = new DictionaryManagement();

    @FXML
    private TextArea wordMean;

    @FXML
    private TextField wordSpelling;

    @FXML
    private TextField wordAdd;

    @FXML
    private Button backHome;

    @FXML
    private TextField wordType;

    @FXML
    private Button save;

    @FXML
    void saveWord(ActionEvent event) {
        if (wordAdd != null && wordMean != null && wordSpelling != null && wordType != null) {
            dictionaryManagement.dictionaryExportToFile(wordAdd.getText(), wordMean.getText(), wordType.getText(), wordSpelling.getText());
        }
    }

    @FXML
    void backToHome(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sample.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        stage.setScene(scene);
    }
}
