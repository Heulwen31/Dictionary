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
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private static final String VOICENAME = "kevin16";
    public VoiceManager vm = VoiceManager.getInstance();
    public Voice voice = vm.getVoice(VOICENAME);

    public DictionaryManagement dictionaryManagement = new DictionaryManagement();

    @FXML
    private WebView showMean;

    @FXML
    private TextField text_search;

    @FXML
    private ListView<String> listWord;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dictionaryManagement = new DictionaryManagement();
        dictionaryManagement.insertFromFile();
    }

    @FXML
    void Submit(ActionEvent event) throws Exception {
        String findWord = text_search.getText();
        Word keyWord = dictionaryManagement.dictionaryLookup(findWord);
        if (dictionaryManagement.database.contains(keyWord)) {
            showMean.getEngine().loadContent(keyWord.word_explain, "text/html");
        } else {
            showMean.getEngine().loadContent("Sorry i can solve the problem!!");
        }
    }

    @FXML
    void submitResult(MouseEvent mouseEvent) throws Exception {
        Word keyWord = dictionaryManagement.dictionaryLookup(listWord.getSelectionModel().getSelectedItem());
        if (dictionaryManagement.database.contains(keyWord)) {
            text_search.setText(keyWord.word_target);
            showMean.getEngine().loadContent(keyWord.word_explain, "text/html");
        } else {
            showMean.getEngine().loadContent("Sorry i can solve the problem!!");
        }
    }

    @FXML
    void submitListView(KeyEvent keyEvent) throws Exception {
        listWord.getItems().clear();
        String findWord = text_search.getText();
        for (Word e : dictionaryManagement.database) {
            if (e.contain(findWord)) {
                listWord.getItems().add(e.word_target);
            }
        }
        listWord.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listWord.getSelectionModel().selectIndices(0);
    }

    public void TalkUS(ActionEvent actionEvent) {
        String word = text_search.getText();
        voice.allocate();
        try {
            voice.speak(word);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void TalkUK(ActionEvent actionEvent) {
        String word = text_search.getText();
        voice.allocate();
        try {
            voice.speak(word);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void translateScene(ActionEvent e) throws Exception {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Translate.fxml"));
        Parent translateParent = loader.load();
        Scene scene = new Scene(translateParent);
        stage.setScene(scene);
    }

    @FXML
    public void addWord(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("additionWord.fxml"));
        Parent addWordParent = loader.load();
        Scene scene = new Scene(addWordParent);
        stage.setScene(scene);
    }
    
    public void delWord() {
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

    public void alertDelete(ActionEvent e) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Word");
        alert.setHeaderText("Are you sure want to delete this word?");
        alert.setContentText(text_search.getText());
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        alert.setX(stage.getX() + 250);
        alert.setY(stage.getY() + 300);

        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == ButtonType.OK) {
            delWord();
        }
    }
}
