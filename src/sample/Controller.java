package sample;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    @FXML
    private Button search;

    @FXML
    private WebView showMean;

    @FXML
    private TextField text_search;

    @FXML
    private ListView<String> listWord;

    private static final String VOICENAME = "kevin16";

    public DictionaryManagement dictionaryManagement = new DictionaryManagement();


    @FXML
    void Submit(ActionEvent event) throws Exception {
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dictionaryManagement = new DictionaryManagement();
        dictionaryManagement.insertFromFile();
    }

    public void TalkUS(ActionEvent actionEvent) {
        Voice voice;
        VoiceManager vm = VoiceManager.getInstance();
        voice = vm.getVoice(VOICENAME);
        String word = text_search.getText();
        voice.allocate();
        try {
            voice.speak(word);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void TalkUK(ActionEvent actionEvent) {
        VoiceManager vm = VoiceManager.getInstance();
        Voice voice = vm.getVoice(VOICENAME);
        String word = text_search.getText();
        voice.allocate();
        try {
            voice.speak(word);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void changeScene(ActionEvent e) throws Exception {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Translate.fxml"));
        Parent translateParent = loader.load();
        Scene scene = new Scene(translateParent);
        stage.setScene(scene);
    }
}
