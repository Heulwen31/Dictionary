package sample;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;

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

    private DictionaryManagement dictionaryManagement;

    public VoiceManager vm = VoiceManager.getInstance();
    public Voice voice = vm.getVoice(VOICENAME);

    /**
     * function submit
     *
     * @param event click Search button
     * @throws Exception
     */
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dictionaryManagement = new DictionaryManagement();
        dictionaryManagement.insertFromFile();
    }
}
