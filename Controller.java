package sample;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private static final String VOICENAME = "kevin16";
    private static final VoiceManager vm = VoiceManager.getInstance();
    private static final Voice voice = vm.getVoice(VOICENAME);

    protected DictionaryManagement dictionaryManagement = new DictionaryManagement();


    @FXML
    private WebView showMean;

    @FXML
    private TextField text_search;

    @FXML
    private ListView<String> listWord;

    /**
     * function submit
     *
     * @param event click Search button
     */
    @FXML
    void Submit(ActionEvent event) {

        listWord.getItems().clear();
        String findWord = text_search.getText();
        Word keyWord = dictionaryManagement.dictionaryLookup(findWord);
        for (Word e : dictionaryManagement.database) {
            if (e.contain(findWord)) {
                listWord.getItems().add(e.word_target);
            }
        }
        if (dictionaryManagement.database.contains(keyWord)) {
            showMean.getEngine().loadContent(keyWord.word_explain, "text/html");
        } else {
            showMean.getEngine().loadContent("Sorry i can solve the problem!!");
        }
    }

    @FXML
    void submitResult(MouseEvent mouseEvent) {
        Word keyWord = dictionaryManagement.dictionaryLookup(listWord.getSelectionModel().getSelectedItem());
        if (dictionaryManagement.database.contains(keyWord)) {
            showMean.getEngine().loadContent(keyWord.word_explain, "text/html");
        } else {
            showMean.getEngine().loadContent("Sorry i can solve the problem!!");
        }
    }

    public void TalkUS(ActionEvent actionEvent) {
        voice.allocate();
        String word = text_search.getText();
        try {
            voice.speak(word);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void TalkUK(ActionEvent actionEvent) {
        voice.allocate();
        String word = text_search.getText();
        voice.allocate();
        try {
            voice.speak(word);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @FXML
    void addWord(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("additionWord.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        stage.setScene(scene);
    }

    @FXML
    void delWord(ActionEvent event) {
        String findWord = text_search.getText();
        Word keyWord = dictionaryManagement.dictionaryLookup(findWord);
        if (dictionaryManagement.database.contains(keyWord)) {
            try {
                dictionaryManagement.deleteWord(findWord);
                listWord.getItems().clear();
                showMean.getEngine().loadContent("");
                dictionaryManagement.database.remove(keyWord);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void translate(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Translate.fxml"));
        Parent translateParent = loader.load();
        Scene scene = new Scene(translateParent);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        stage.setScene(scene);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dictionaryManagement.insertFromFile();
    }

}
