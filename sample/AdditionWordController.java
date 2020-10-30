package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class AdditionWordController extends Controller {
    @FXML
    private TextArea wordMean;

    @FXML
    private TextField wordSpelling;

    @FXML
    private TextField wordAdd;

    @FXML
    private TextField wordType;

    @FXML
    void saveWord(ActionEvent event) {
        if (wordAdd != null && wordMean != null && wordSpelling != null && wordType != null) {

            super.dictionaryManagement.dictionaryExportToFile(wordAdd.getText(), wordMean.getText(), wordType.getText(), wordSpelling.getText());
            super.dictionaryManagement.insertFromFile();
        }
    }

    @FXML
    public void goBack(ActionEvent e) throws Exception {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sample.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        stage.setScene(scene);
    }

    @FXML
    void changeWord(ActionEvent event) throws IOException {
        if (super.dictionaryManagement.database.contains(dictionaryManagement.dictionaryLookup(wordAdd.getText()))) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Edit Word");
            alert.setHeaderText("Are you sure want to change this word?");
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            alert.setX(stage.getX() + 250);
            alert.setY(stage.getY() + 300);

            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.OK) {
                super.dictionaryManagement.ChangeWord(wordAdd.getText(),wordMean.getText(),wordType.getText(),wordSpelling.getText());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Warring");
            alert.setHeaderText("We not find the word you want to change");
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            alert.setX(stage.getX() + 250);
            alert.setY(stage.getY() + 300);
        }
    }
}