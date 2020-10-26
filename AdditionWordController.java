package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdditionWordController extends Controller {

    private DictionaryManagement dictionaryManagement = new DictionaryManagement();

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
}