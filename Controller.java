package sample;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;

public class Controller {
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

        dictionaryManagement.insertFromFile();
        String findWord = text_search.getText();
        Word keyWord = dictionaryManagement.dictionaryLookup(findWord);
        for (Word e : dictionaryManagement.database) {
            if (e.word_target.contains(findWord)) {
                listWord.getItems().add(e.word_target);
            }
        }
        if (dictionaryManagement.database.contains(keyWord)) {

        } else {

        }
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
}
